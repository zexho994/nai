package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-21 21:17
 */
@SpringBootTest
class OrigionServiceTest {
    @Autowired
    RegionService regionService;

    String region1 = "湖南";
    String region2 = "四川";
    String time1 = "2010";

    @Test
    void getByOriTest() {
        Resp res = regionService.getByOri(region1);
        Assert.assertNotNull(res.getData());
    }

    @Test
    void getByOriAndTime() {
        Resp res = regionService.getByOriAndTime(region1,time1);
        Assert.assertNotNull(region1,time1);
    }
}