package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-24 13:28
 */
@SpringBootTest
class TitleServiceTest {
    @Autowired
    TitleService titleService;
    String title1 = "单词接龙";

    @Test
    void getALg() {
        Resp aLg = titleService.getALg(title1);
        Assert.assertNotNull(aLg.getData());

    }

    @Test
    void getOri() {
        Resp ori = titleService.getOri(title1);
        Assert.assertNotNull(ori.getData());
    }

    @Test
    void getTime() {
        Resp time = titleService.getTime(title1);
        Assert.assertNotNull(time.getData());

    }

    @Test
    void getProblem() {
        Resp problem = titleService.getPid(title1);
        Assert.assertNotNull(problem.getData());

    }

    @Test
    void getDiff() {

        Resp diff = titleService.getDiff(title1);
        Assert.assertNotNull(diff.getData());
    }

    @Test
    void getReg() {
        Resp reg = titleService.getReg(title1);
        Assert.assertNotNull(reg.getData());
    }

    @Test
    void getTK() {
        Resp tk = titleService.getTK(title1);
        Assert.assertNotNull(tk.getData());
    }
}