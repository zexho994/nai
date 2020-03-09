package com.zyaml.nai.entry.rel;

import com.zyaml.nai.entry.node.Answer;
import com.zyaml.nai.entry.node.Problem;
import lombok.Data;
import org.neo4j.ogm.annotation.*;
/**
 * @Author: 994
 * @Date: 2020-03-08 21:26
 */
@RelationshipEntity(type = "题解")
@Data
public class AnswerRelation {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 建立问题 -> 答案的关系
     */


    @StartNode
    private Problem problem;


    @EndNode
    private Answer answer;
}
