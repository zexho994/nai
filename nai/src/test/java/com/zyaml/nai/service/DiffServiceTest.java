package com.zyaml.nai.service;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.vo.ProblemVO;
import com.zyaml.nai.repository.DiffCql;
import com.zyaml.nai.service.neo.DiffService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-07 21:50
 */
@SpringBootTest
class DiffServiceTest {

    @Autowired
    DiffCql diffCql;

    @Autowired
    DiffService diffService;

    @Test
    void getProblemsByDiffTest() {
        String dif1= "NOI/NOI+/CTSC";
        String dif2 = "入门";

    }

    @Test
    void getCountTest(){
        String difName = "入门";

        //入门题的数量
        int count = diffCql.getCount(difName);
        Assert.assertNotNull(count);
    }

    @Test
    void getDifAndSourceTest(){
        String dif1 = "普及/提高-";
        String source1 = "洛谷原创";

        Resp difAndSource = diffService.getDifAndSource(dif1, source1);

        Assert.assertNotNull(difAndSource.getData());
    }

    @Test
    void getProByDifAndRegTest(){
        String dif = "入门";
        String reg = "安徽";
        Resp proByDifAndReg = diffService.getProByDifAndReg(dif, reg);

        Assert.assertNotNull(proByDifAndReg.getData());
    }

    @Test
    void getProByDifAndTimeTest(){
        String dif = "入门";
        String time = "2006";
        Resp problems = diffService.getProByDifAndTime(dif, time);

        Assert.assertNotNull(problems.getData());

    }

    @Test
    void getProByDifAndGTTest(){
        String dif = "入门";
        String time = "2006";
        Resp problems = diffService.getProByDifAndGT(dif, time);

        Assert.assertNotNull(problems.getData());

    }

    @Test
    void getProByDifAndLTTest(){
        String dif = "入门";
        String time = "2006";
        Resp problems = diffService.getProByDifAndLT(dif, time);

        Assert.assertNotNull(problems.getData());

    }





    @Test
    void getProByDifAndOriTest(){
        String dif = "省选/NOI-";
        String ori = "IOI";
        Resp problems = diffService.getProByDifAndOri(dif, ori);

        Assert.assertNotNull(problems.getData());
    }




}