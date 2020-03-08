package com.zyaml.nai.util;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.nlp.ESimnetType;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: 994
 * @Date: 2020-03-04 21:17
 */
@Component
public class BaiduAiUtil{
    private static final String APP_ID = "18669561";
    private static final String API_KEY = "mGSP9oYipCnany8dGYOAd6ck";
    private static final String SECRET_KEY = "6wVpBgN3N3uRfFhbyXAIowZxLCg7Bxf6";

    private static final int CONNECT_TIMEOUT = 2000;
    private static final int SOCKET_TIMEOUT = 60000;

    private static AipNlp client = new AipNlp(APP_ID,API_KEY,SECRET_KEY);

    static {
        client.setConnectionTimeoutInMillis(CONNECT_TIMEOUT);
        client.setSocketTimeoutInMillis(SOCKET_TIMEOUT);
    }

    /**
     * 分词接口
     */
    public static JSONObject tokenizer(String msg) {

        JSONObject res = client.lexer(msg,null);

        return res;
    }

    /**
     * 定制版的词法分析
     * @param msg
     */
    public static JSONObject lexicalAnalysisCustom(String msg) {
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<>();

        // 词法分析（定制版）
        JSONObject res = client.lexerCustom(msg, options);

        return res;
    }

    /**
     * 词法分析接口
     * @param msg
     * @return
     */
    public static JSONObject lexicalAnalysis(String msg) {
        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap(16);

        // 词法分析
        JSONObject res = client.lexer(msg, options);
        return res;

    }

    /**
     * 句法依存分析
     * @param msg 要处理的接口
     * @return
     */
    public static JSONObject syntacticStructure(String msg) {

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("mode", 1);

        // 依存句法分析
        return client.depParser(msg, options);

    }

    /**
     * 教育行业-观点评论
     * @param msg 语句信息
     * @return
     */
    public static JSONObject opinionReview(String msg){
        HashMap<String, Object> options = new HashMap<>(8);
        //获取教育类的观点
        JSONObject response = client.commentTag(msg, ESimnetType.EDU,options);
        System.out.println(response);
        return response;
    }

}
