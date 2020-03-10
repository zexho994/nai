package com.zyaml.nai.service;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.comom.ContionsDictionary;
import com.zyaml.nai.entry.dto.Words;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.vo.ProblemVO;
import com.zyaml.nai.service.es.MouldDocService;
import com.zyaml.nai.util.JsonUtil;
import com.zyaml.nai.util.Mould;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;
import java.util.List;
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
    MethodCall methodCall;

    /**
     * 问答系统的方法集合处
     * @param msg
     * @return
     */
    public Object qustion(String msg){
        //分词结果
        JSONObject jsonObject = baiDuAiService.lexicalAnalysisCustom(msg);

        //获取单词串
        Words<String,String> words = JsonUtil.getItemAndNe(jsonObject);

        //找到适配方法
        Object call = methodCall.methodCall(words);

        return call;
    }

}
