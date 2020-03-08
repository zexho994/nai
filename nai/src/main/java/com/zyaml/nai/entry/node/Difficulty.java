package com.zyaml.nai.entry.node;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * @Author: 994
 * @Date: 2020-03-04 22:46
 */
@NodeEntity
@Data
public class Difficulty {
    /**
     * 每一个节点都会有一个隐藏的自增ID作为主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 难度英文表示
     */
    private int difficulty;

    /**
     * 难度中文
     */
    private String difficultyString;


    /**
     * 该难度下的所有问题
     */
    @Relationship(type = "难度",direction = Relationship.OUTGOING)
    private Set<Problem> problemList;
}
