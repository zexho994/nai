package com.zyaml.nai.controller.es;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.service.es.ElasticSearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供基础类型的es接口
 * @Author: 994
 * @Date: 2020-03-08 00:00
 */
@Log4j2
@RequestMapping("/api/es/common")
@RestController
public class BaseSearchApi {

    @Autowired
    ElasticSearchService searchService;

    /**
     * 判断index是否存在
     * @param index 节点名字
     * @return
     */
    @GetMapping("/exist/{index}")
    public boolean exist(@PathVariable String index){
        try {
            return searchService.indexExist(index);
        } catch (Exception e) {
            throw new RestException(ErrorCode.ELASTIC_ERROR);
        }
    }




}
