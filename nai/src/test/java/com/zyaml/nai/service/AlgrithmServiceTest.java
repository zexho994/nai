package com.zyaml.nai.service;

import com.zyaml.nai.entry.vo.ProblemVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-10 11:11
 */
@SpringBootTest
class AlgrithmServiceTest {

    @Autowired
    AlgorithmService algorithmService;

    @Test
    void getByAlg() {
        String alg1 = "字符串";
        List<ProblemVO> byAlg = algorithmService.getByAlg(alg1);
        Assert.assertNotNull(byAlg);

    }
}