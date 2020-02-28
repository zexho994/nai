package com.zyaml.nai.service;

import com.zyaml.nai.entry.node.PersonNode;
import com.zyaml.nai.repository.neo.PersonCql;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-02-14 14:07
 */
@Service
public class PersonService {
    @Autowired
    private PersonCql personCql;

    /**
     * 获取所有的person节点
     * @return
     */
    public List<PersonNode> matchAll(){
        List<PersonNode> allPersonNodes = personCql.matchAll();
        return allPersonNodes;
    }

    /**
     * 根据 name 获取对应的节点
     * @param name
     * @return
     */
    public PersonNode matchByName(String name){
        return personCql.findPersonByName(name);
    }
}
