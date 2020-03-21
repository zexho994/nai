package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-21 20:39
 */
@SpringBootTest
class TypeServiceTest {

    @Autowired
    TypeService typeService;

    String type1 = "洛谷题库";
    String ori1 = "IOI";
    String loc1 = "湖南";
    String time1 = "2016";

    @Test
    void getByTypeTest() {
        Resp res = typeService.getByType(type1);
        Assert.assertNotNull(res.getData());
    }

    @Test
    void getByTypeAndOriTest() {
        Resp res = typeService.getByTypeAndOri(type1,ori1);
        Assert.assertNotNull(res.getData());
    }

    @Test
    void getByTypeAndLocTest() {
        Resp res = typeService.getByTypeAndLoc(type1,loc1);
        Assert.assertNotNull(res.getData());
    }

    @Test
    void getByTypeAndTimeTest() {
        Resp res = typeService.getByTypeAndTime(type1,time1);
        Assert.assertNotNull(res.getData());
    }
}