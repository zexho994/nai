package com.zyaml.nai.service;


import com.zyaml.nai.comom.DiffcultyComom;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.service.neo.ProblemService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-06 14:38
 */
@SpringBootTest
class ProblemServiceTest {

    @Autowired
    ProblemService problemService;

    /**
     * 测试<方法>getProblemsByPidAndDiff</方法>
     */
    @Test
    void getProblemsByPidAndDiffTest(){
        String pid1 = "P1001";
        List<Problem> problemsByPidAndDiff = problemService.getProblemsByPidAndDiff(pid1);
        Assert.assertNotNull(problemsByPidAndDiff);
    }

    @Test
    void getDiffNameTest(){
        String pid1 = "P1024";
        String diffName = problemService.getDiffName(pid1);
        Assert.assertEquals(DiffcultyComom.universal_normal.name,diffName);
    }

    @Test
    void getNameByPid(){
        String pid1 = "P1024";

        String res1 = problemService.getTitle(pid1);
        Assert.assertNotNull(res1);
    }

    @Test
    void getType(){
        String pid1 = "P1024";
        String type = problemService.getType(pid1);
        Assert.assertNotNull(type);
    }

}