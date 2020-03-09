package com.zyaml.nai.service;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.comom.ContionsDictionary;
import com.zyaml.nai.entry.dto.Words;
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

        Words words = JsonUtil.getItemAndNe(jsonObject);

        Object o = requestDispenser(words.getFormat(), words);

        return o;
    }

    /**
     * 找到模板对应的方法
     * @param format
     * @param words
     * @return
     */
    private Object requestDispenser(String format,Words words){
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
                        log.debug("=====> RequestDispenser get format :"+methodAnno.format());
                        Object invoke = declaredMethod.invoke(this,words);
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
    String pidDif(Words words){
        return problemService.getDiffName((String) words.get("PID"));
    }

    @Mould(format = "PID+name+")
    String pidName(Words words){
        return problemService.getTitle((String) words.get("PID"));
    }

    @Mould(format = "PID+source+")
    String pidSource(Words words){
        return problemService.getType((String) words.get("PID"));
    }


}
