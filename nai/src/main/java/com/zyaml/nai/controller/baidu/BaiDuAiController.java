package com.zyaml.nai.controller.baidu;

import com.zyaml.nai.service.BaiDuAiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 994
 * @Date: 2020-03-04 21:23
 */
@RestController
@RequestMapping("/api/ai")
@Log4j2
public class BaiDuAiController {

    @Autowired
    private BaiDuAiService baiDuAiService;

    /**
     * 分词接口
     * @param msg 待分词的句子
     * @return
     */
    @GetMapping("/tokenizer/{msg}")
    public String tokenizer(@PathVariable String msg){
        log.info("=====> Tokenizer api : " + msg);
        if (msg==null || msg.length()<1){
            return null;
        }
        return baiDuAiService.tokenizer(msg);
    }

    /**
     * 词法解析接口
     * @param msg 待解析的句子
     * @return
     */
    @GetMapping("/lexicalAnalysis/{msg}")
    public String lexicalAnalysis(@PathVariable String msg){
        log.info("=====> Lexical Analysis api : " + msg);
        if(msg == null || msg.length() < 1){
            return "";
        }
        return baiDuAiService.lexicAlanalysis(msg);
    }

    /**
     * 句法依存分析接口
     * @param msg 待分析的句子
     * @return
     */
    @GetMapping("/syntactic/{msg}")
    public String syntacticStructure(@PathVariable String msg){
        log.info("=====> Syntactic Structure api : " + msg);
        if(msg == null || msg.length() < 1){
            return "";
        }
        return baiDuAiService.syntacticStructure(msg);
    }

    /**
     * 评论观点获取
     * @param msg 语句信息
     * @return
     */
    @GetMapping("/opinion_review/{msg}")
    public String opinionReview(@PathVariable String msg){
        log.info("=====> Opinion Review api : " + msg);
        if(msg == null || msg.length() < 1){
            return "";
        }
        return baiDuAiService.opinionReview(msg);
    }



}
