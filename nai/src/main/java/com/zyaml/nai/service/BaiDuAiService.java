package com.zyaml.nai.service;

import com.zyaml.nai.util.BaiduAiUtil;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Author: 994
 * @Date: 2020-03-04 21:30
 */
@Service
@Log4j2
public class BaiDuAiService {

    /**
     * 分词接口
     * @param msg
     * @return
     */
    public String tokenizer(String msg){
        JSONObject res = BaiduAiUtil.tokenizer(msg);
        if(res == null){
            return null;
        }
        return res.toString();
    }

    /**
     * 词法分析接口
     * @return
     */
    public String lexicAlanalysis(String msg){
        JSONObject jsonObject = BaiduAiUtil.lexicalAnalysis(msg);
        if(jsonObject == null){
            return "";
        }
        return jsonObject.toString();
    }

    /**
     * 句法依存分析
     * @param msg
     * @return
     */
    public String syntacticStructure(String msg){
        JSONObject jsonObject = BaiduAiUtil.syntacticStructure(msg);
        if(jsonObject == null){
            return "";
        }
        return jsonObject.toString();

    }

    /**
     * 获取观点
     * @param msg 语句信息
     * @return
     */
    public String opinionReview(String msg){
        JSONObject jsonObject = BaiduAiUtil.opinionReview(msg);
        if(jsonObject == null){
            return "";
        }
        return jsonObject.toString();
    }

}
