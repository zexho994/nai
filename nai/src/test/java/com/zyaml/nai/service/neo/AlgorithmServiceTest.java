package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-19 15:22
 */
@SpringBootTest
class AlgorithmServiceTest {

    @Autowired
    AlgorithmService algorithmService;

    String alg1 = "排序";
    String time1 = "2014";

    @Test
    void getByAlgTest() {
        Resp resp = algorithmService.getByAlg(alg1);
        Assert.assertNotNull(resp.getData());
    }

    @Test
    void getProByAlgAndTimeTest() {
        Resp resp = algorithmService.getProByAlgAndTime(alg1, time1);
        Assert.assertNotNull(resp.getData());
    }
}