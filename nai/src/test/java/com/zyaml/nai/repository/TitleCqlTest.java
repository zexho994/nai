package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Problem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-04-30 14:49
 */
@SpringBootTest
class TitleCqlTest {

    @Autowired
    TitleCql titleCql;

    @Test
    public void getProblem(){
        String str = "硬币翻转";

        Problem problem = titleCql.findProblem(str);

        Assert.assertNotNull(problem);
    }

}