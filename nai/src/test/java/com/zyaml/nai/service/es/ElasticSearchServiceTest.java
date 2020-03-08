package com.zyaml.nai.service.es;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-08 13:39
 */
@SpringBootTest
class ElasticSearchServiceTest {
    @Autowired
    ElasticSearchService service;

    @Test
    void indexExist() {
    }

    @Test
    void matchParse() throws IOException {
        Map<String, Object> result = service.matchParse("mould", "format", "PID");
        Assert.assertEquals(1001,result.get("num"));

        Map<String, Object> result1 = service.matchParse("mould", "format", "PID+DIF");
        Assert.assertEquals(1002,result1.get("num"));
    }

    @Test
    void add() throws IOException {
        Map<String,Object> json = new HashMap<>(8);
        json.put("num",1002);
        json.put("format","PID+DIF");
        service.add("mould",json);
    }

    @Test
    void searchAll() throws IOException {
        Map<Integer, Map<String, Object>> mould = service.searchAll("mould", 0, 10);
        Map<Integer,Map> map = new HashMap<>();
        Integer i = 0;

    }

}