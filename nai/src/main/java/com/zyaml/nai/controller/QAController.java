package com.zyaml.nai.controller;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.entry.from.QAFrom;
import com.zyaml.nai.service.QAService;
import com.zyaml.nai.util.DtoUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Resp question(QAFrom qaFrom){
        if(qaFrom.getMsg()==null || "".equals(qaFrom.getMsg())){
            return new Resp(200,"请输入查询语句");
        }
        log.info("=====> 提问内容 : " + qaFrom.getMsg());

        Resp res = qaService.qustion(qaFrom.getMsg());


        log.debug("=====> 问答完成");
        //存在查询结果
        return res;
    }

}
