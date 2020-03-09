package com.zyaml.nai.service;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.repository.DiffCql;
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

    @Test
    void getProblemsByDiff() {
        String dif1= "NOI/NOI+/CTSC";

        List<Problem> problemsByDiff = diffCql.getProblemsByDiff(dif1);
        Assert.assertNotNull(problemsByDiff);

    }
}