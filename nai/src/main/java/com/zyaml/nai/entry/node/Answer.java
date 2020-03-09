package com.zyaml.nai.entry.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @Author: 994
 * @Date: 2020-03-08 21:25
 */
@NodeEntity
@Data
public class Answer {
    @Id
    @GeneratedValue
    private Long id;


    /**
     * 问题的提供者
     */
    private String userName;

    /**
     * 问题的题解
     */
    private String answerString;
}
