package com.zyaml.nai.controller;

import com.zyaml.nai.entry.node.PersonNode;
import com.zyaml.nai.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-02-14 14:06
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping()
    public List<PersonNode> findAll(){
        return personService.matchAll();
    }
}
