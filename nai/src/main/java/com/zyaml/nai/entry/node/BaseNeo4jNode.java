package com.zyaml.nai.entry.node;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;

/**
 * @Author: 994
 * @Date: 2020-02-14 14:21
 */
@NodeEntity
public class BaseNeo4jNode implements Serializable{
    /**
     * 每一个节点都会有一个隐藏的自增ID作为主键
     */
    @Id
    @GeneratedValue
    private Long id;
}
