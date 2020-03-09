package com.zyaml.nai.entry.rel;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Types;
import com.zyaml.nai.entry.node.Types;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "类型")
@Data
public class TypeRelation {
    /**
     * 每一个节点都会有一个隐藏的自增ID作为主键
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * 类型节点
     */
    @StartNode
    private Types types;

    /**
     * 问题节点
     */
    @EndNode
    private Problem problem;




}
