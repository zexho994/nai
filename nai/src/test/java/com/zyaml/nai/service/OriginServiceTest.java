package com.zyaml.nai.service;

import com.zyaml.nai.Exception.Resp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-04-29 21:06
 */
@SpringBootTest
class OriginServiceTest {

    @Autowired
    private OriginService originService;

    @Test
    void getOriginByName() {
        Resp originByName = originService.getOriginByName("[USACO3.2]魔板 Magic Squares");
        Assert.assertNotNull(originByName);
    }
}