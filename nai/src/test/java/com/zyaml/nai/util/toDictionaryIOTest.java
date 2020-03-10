package com.zyaml.nai.util;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.repository.AlgorithmCql;
import com.zyaml.nai.repository.ProblemCql;
import com.zyaml.nai.service.AlgorithmService;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-10 20:47
 */
@Log4j2
@SpringBootTest
class toDictionaryIOTest {

    private String algorithmPath = "/algorithm.txt";
    private String problemName_Path = "/problem.txt";

    @Autowired
    AlgorithmService algorithmService;

    @Autowired
    AlgorithmCql algorithmCql;

    @Autowired
    ProblemCql problemCql;


    @Test
    void outAlgorithm() {

        List<String> list = new ArrayList<>(250);

        toDictionaryIO<String> dic = new toDictionaryIO<>();

        List<Tags> all = algorithmCql.getAll();
        Assert.assertNotNull(all);

        for(Tags tages:all){
            list.add(tages.getName());
        }



        try {
            dic.outFile(list,"algorithm.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug("file : Algorithm out File 写入完成!");
    }

    @Test
    void outProblem(){
        List list = problemCql.getAll();

        toDictionaryIO dic = new toDictionaryIO();

        try {
            dic.outFile(list,"problemName.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug("file : Algorithm out File 写入完成!");
    }
}