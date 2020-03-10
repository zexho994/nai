package com.zyaml.nai.entry.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @Author: 994
 * @Date: 2020-03-04 22:47
 */
@NodeEntity
@Data
public class Problem{
    /**
     * 每一个节点都会有一个隐藏的自增ID作为主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 题目pid
     */
    private String pid;

    /**
     * 题目名称
     */
    private String title;

}
