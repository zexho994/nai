package com.zyaml.nai.entry.rel;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * @Author: 994
 * @Date: 2020-03-08 21:27
 */
@RelationshipEntity(type = "标签")
@Data
public class TagRelation {
    @Id
    @GeneratedValue
    private Long id;


    /**
     * 建立 标签 -> 问题 的关系连接
     */

    /**
     * 类型节点
     */
    @StartNode
    private Tags tags;

    /**
     * 问题节点
     */
    @EndNode
    private Problem problem;
}
