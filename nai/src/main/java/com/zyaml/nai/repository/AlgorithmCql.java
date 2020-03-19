package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-10 11:00
 */

public interface AlgorithmCql extends Neo4jRepository<Types,Long> {

    /**
     * 根据算法获取题目
     * @param alg 算法名
     * @return
     */
    @Query("MATCH(t:Tags{name:$alg})-[]-(p:Problem) RETURN p LIMIT 10")
    List<Problem> getByAlg(String alg);

    /**
     * 获取所有算法标签
     * @return
     */
    @Query("match (t:Tags{type:'Algorithm'}) return t")
    List<Tags> getAll();

    /**
     * 匹配和算法和时间都有关系的节点
     * @param alg  算法名称
     * @param time 时间
     * @param page 起始页
     * @param size 页大小
     * @return
     */
    @Query("match (p:Problem) - [] - (t:Tags{type:\"Algorithm\",name:\"分治\"}) with p \n" +
            "match (p:Problem) - [] - (t:Tags{type:\"Time\",name:\"2014\"}) " +
            "return p skip $page*$size limit $size")
    List<Problem> getProByAlgAndTime(String alg,String time,int page,int size);




}
