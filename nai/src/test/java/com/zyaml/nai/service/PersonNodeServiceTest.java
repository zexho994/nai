package com.zyaml.nai.service;

import com.zyaml.nai.entry.node.PersonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-02-14 14:29
 */
@SpringBootTest
class PersonNodeServiceTest {
    @Autowired
    PersonService personService;


    @Test
    void findAllPersonTest() {
        List<PersonNode> allPersonNodes = personService.matchAll();
        Assert.notEmpty(allPersonNodes,"tested fail");
        for (PersonNode p : allPersonNodes){
            System.out.println(p.toString());
        }
    }
}