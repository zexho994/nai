package com.zyaml.nai.entry.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * @Author: 994
 * @Date: 2020-03-04 22:47
 */
@NodeEntity
@Data
public class Type{
    /**
     * 每一个节点都会有一个隐藏的自增ID作为主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 中文标签
     */
    private String tagString;

    /**
     * 英文标签
     */
    private int tagInt;

    /**
     * 该分类下的所有问题
     */
    @Relationship(type = "类型",direction = Relationship.OUTGOING)
    private Set<Problem> problemList;
}
