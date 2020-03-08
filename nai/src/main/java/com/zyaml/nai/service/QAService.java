package com.zyaml.nai.service;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.comom.ContionsDictionary;
import com.zyaml.nai.util.JsonUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: 994
 * @Date: 2020-03-08 14:37
 */
@Service
public class QAService {

    @Autowired
    private BaiDuAiService baiDuAiService;

    public String qustion(String msg){
        if(msg == null || msg.length() < 1){
            throw new RestException(ErrorCode.PARAM_INVALID,"提问语句不符合要求");
        }
        //分词结果
        JSONObject jsonObject = baiDuAiService.lexicalAnalysisCustom(msg);

        //解析
        Map<String,String> itemAndNe = JsonUtil.getItemAndNe(jsonObject);

        //获取解析的结果
        String format = combinationString(itemAndNe);

        return format;
    }

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
        return sb.toString();
    }

}
