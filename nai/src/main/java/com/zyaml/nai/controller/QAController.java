package com.zyaml.nai.controller;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.from.QAFrom;
import com.zyaml.nai.service.QAService;
import com.zyaml.nai.util.DtoUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问答接口
 * @Author: 994
 * @Date: 2020-03-08 14:36
 */
@RequestMapping("/api/qa")
@RestController
@Log4j2
public class QAController {

    @Autowired
    QAService qaService;

    @PostMapping
    public Resp question(@RequestBody QAFrom qaFrom){
        log.info("=====> Question : " + qaFrom.getMsg());
        Object qustion = qaService.qustion(qaFrom.getMsg());

        //没有查询结果
        if(qustion==null|| "".equals(qustion)){
            log.info("=====> quest 没有结果");
            return new Resp(ErrorCode.NULL,"输入的内容无法解析");
        }

        //存在查询结果
        return new Resp(ErrorCode.SUCCESS,qustion.toString());
    }

}
