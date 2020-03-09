package com.zyaml.nai.entry.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * @Author: 994
 * @Date: 2020-03-08 21:26
 */
@NodeEntity
@Data
public class Tags{

    /**
     * name : 模拟
     * type : Algorithm
     * color : red
     * tagInt : 1
     */

    @Id
    @GeneratedValue
    private Long id;

    /**
     * tag中文名称
     */
    private String name;

    /**
     * tag 的类型
     */
    private String type;

    /**
     * tag颜色，给前端用的
     */
    private String color;

    /**
     * tag的id
     */
    private int tagInt;


    /**
     * 该标签下的所有问题
     */
    @Relationship(type = "标签",direction = Relationship.OUTGOING)
    private Set<Problem> problemList;
}
