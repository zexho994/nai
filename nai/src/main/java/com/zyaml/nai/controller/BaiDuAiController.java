package com.zyaml.nai.controller;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.entry.from.Lexicalfrom;
import com.zyaml.nai.service.BaiDuAiService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param lexicalfrom 里的msg是待分词的句子
     * @return
     */
    @PostMapping("/tokenizer")
    public String tokenizer(@RequestBody Lexicalfrom lexicalfrom){
        log.info("=====> Tokenizer api : " + lexicalfrom.getMsg());
        if (lexicalfrom.getMsg() == null || lexicalfrom.getMsg().length()<1){
            return null;
        }
        return baiDuAiService.tokenizer(lexicalfrom.getMsg());
    }

    /**
     * 词法分析接口
     * <fet>定制版</fet>
     * @param lexicalfrom 分词对象
     * @return
     */
    @PostMapping("/lexicalAnalysis/custom")
    public Resp lexicalAnalysisCustom(@RequestBody Lexicalfrom lexicalfrom){
        if(lexicalfrom==null || lexicalfrom.getMsg().equals("")||lexicalfrom.getMsg().length()<1){
            log.debug("=====> Tokenizer Custom api : the msg is null !");
            throw new RestException(ErrorCode.PARAM_INVALID,"msg 不符合要求!");
        }
        log.info("=====> Tokenizer Custom api : " + lexicalfrom.getMsg());

        JSONObject jsonObject = baiDuAiService.lexicalAnalysisCustom(lexicalfrom.getMsg());

        return new Resp(jsonObject.toString());
    }

    /**
     * 词法解析接口
     * @param lexicalfrom
     * @return
     */
    @PostMapping("/lexicalAnalysis")
    public String lexicalAnalysis(@RequestBody Lexicalfrom lexicalfrom){
        log.info("=====> Lexical Analysis api : " + lexicalfrom.getMsg());
        if(lexicalfrom.getMsg() == null || lexicalfrom.getMsg().length() < 1){
            return "";
        }
        return baiDuAiService.lexicAlanalysis(lexicalfrom.getMsg());
    }

    /**
     * 句法依存分析接口
     * @param lexicalfrom
     * @return
     */
    @PostMapping("/syntactic")
    public String syntacticStructure(@RequestBody Lexicalfrom lexicalfrom){
        log.info("=====> Syntactic Structure api : " + lexicalfrom.getMsg());
        if(lexicalfrom.getMsg() == null || lexicalfrom.getMsg().length() < 1){
            return "";
        }
        return baiDuAiService.syntacticStructure(lexicalfrom.getMsg());
    }

    /**
     * 评论观点获取
     * @param lexicalfrom
     * @return
     */
    @PostMapping("/opinion_review")
    public String opinionReview(@RequestBody Lexicalfrom lexicalfrom){
        log.info("=====> Opinion Review api : " + lexicalfrom.getMsg());
        if(lexicalfrom.getMsg() == null || lexicalfrom.getMsg().length() < 1){
            return "";
        }
        return baiDuAiService.opinionReview(lexicalfrom.getMsg());
    }

}
