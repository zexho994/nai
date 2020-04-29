package com.zyaml.nai.service;


import com.zyaml.nai.Exception.Resp;
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

        Resp problemsByPidAndDiff = problemService.getProblemsByPidAndDiff(pid1);

        Assert.assertNotNull(problemsByPidAndDiff.getData());
    }

    @Test
    void getDiffNameTest(){
        String pid1 = "P1024";
        Resp diffName = problemService.getDiffName(pid1);
        Assert.assertEquals(DiffcultyComom.universal_normal.name,diffName.getData());
    }

    @Test
    void getNameByPid(){
        String pid1 = "P1024";

        Resp title = problemService.getTitle(pid1);

        Assert.assertNotNull(title.getData());
    }

    @Test
    void getType(){
        String pid1 = "P1024";

        Resp type = problemService.getType(pid1);

        Assert.assertNotNull(type.getData());
    }
    
    @Test
    void getAlgTest(){
        String pid1 = "P1024";

        Resp alg = problemService.getAlg(pid1);

        Object data = alg.getData();

        Assert.assertNotNull(data);
    }

    @Test
    void getRegionTest(){
        String pid1 = "P3218";

        Resp region = problemService.getRegion(pid1);

        Assert.assertNotNull(region.getData());
    }

    @Test
    void getTimeTest(){
        String pid1 = "P1328";

        Resp time = problemService.getTime(pid1);

        Assert.assertNotNull(time.getData());
    }

    @Test
    void getOriTest(){
        String pid = "P1023";
        String pid1 = "P2730";
        Resp ori = problemService.getOri(pid1);
        Assert.assertNotNull(ori);
    }



}