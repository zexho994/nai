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
    String ori = "COCI";
    String alg2 = "计算几何";
    String ori2 = "福建省历届夏令营";
    String source1 = "洛谷题库";
    String alg3 = "线段树";
    String reg1 = "四川";

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

    @Test
    void getProByAlgAndGTTest(){
        Resp resp = algorithmService.getProByAlgAndGT(alg1,time1);
        Assert.assertNotNull(resp.getData());
    }

    @Test
    void getProByAlgAndLTTest(){
        Resp resp = algorithmService.getProByAlgAngLT(alg1,time1);
        Assert.assertNotNull(resp.getData());
    }

    @Test
    void getProByAlgAndOriTest(){
        Resp res = algorithmService.getProByAlgAndOri(alg2, ori2);
        Assert.assertNotNull(res.getData());
    }

    @Test
    void getProByAlgAndSourceTest(){
        Resp resp = algorithmService.getProByAlgAndSource(alg3, source1);
        Assert.assertNotNull(resp.getData());
    }

    @Test
    void getProByAlgAndRegTest(){
        Resp resp = algorithmService.getProByAlgAndReg(alg3, reg1);
        Assert.assertNotNull(resp.getData());
    }
}