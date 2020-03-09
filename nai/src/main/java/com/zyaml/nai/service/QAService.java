package com.zyaml.nai.service;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.comom.ContionsDictionary;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.service.es.MouldDocService;
import com.zyaml.nai.util.JsonUtil;
import com.zyaml.nai.util.Mould;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: 994
 * @Date: 2020-03-08 14:37
 */
@Service
@Log4j2
public class QAService {

    @Autowired
    private BaiDuAiService baiDuAiService;
    @Autowired
    private ProblemService problemService;

    @Autowired
    MouldDocService mouldDocService;

    public Object qustion(String msg){
        if(msg == null || msg.length() < 1){
            throw new RestException(ErrorCode.PARAM_INVALID,"提问语句不符合要求");
        }
        //分词结果
        JSONObject jsonObject = baiDuAiService.lexicalAnalysisCustom(msg);

        //解析
        Map<String,String> itemAndNe = JsonUtil.getItemAndNe(jsonObject);

        //获取解析的结果
        String format = combinationString(itemAndNe);


        Object o = requestDispenser(format, itemAndNe);

        return o;
    }

    /**
     * 转化字符串
     * @param map
     * @return
     */
    private String combinationString(Map<String,String> map){
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals("CON")){
                String match = ContionsDictionary.match(entry.getValue());
                sb.append(match+"+");
            }
            else{
                sb.append(entry.getKey()+"+");
            }
        }
        log.debug("=====> combinationString ："+sb.toString());
        return sb.toString();
    }

    /**
     * 找到模板对应的方法
     * @param format
     * @param map
     * @return
     */
    private Object requestDispenser(String format,Map<String,String> map){
        try {
            //获取类名的包名地址
            Class<?> printClass = Class.forName("com.zyaml.nai.service.QAService");
            //java反射机制获取所有方法名
            Method[] declaredMethods = printClass.getDeclaredMethods();
            //遍历循环方法并获取对应的注解名称
            for (Method declaredMethod : declaredMethods) {
                // 判断是否方法上存在注解  MethodInterface
                if(declaredMethod.isAnnotationPresent(Mould.class)){
                    // 获取自定义注解对象
                    Mould methodAnno = declaredMethod.getAnnotation(Mould.class);
                    if(methodAnno.format().equals(format)){
                        log.info("=====> RequestDispenser get format :"+methodAnno.format());
                        Object invoke = declaredMethod.invoke(this,map);
                        return invoke;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Mould(format = "PID+dif+")
    String piddif(Map<String,String> map){
        String nd = problemService.getDiffName(map.get("PID"));
        return nd;
    }


}
