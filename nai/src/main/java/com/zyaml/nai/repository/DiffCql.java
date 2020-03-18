package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-07 21:34
 */

public interface DiffCql extends Neo4jRepository<Difficulty,Long> {

    /**
     * 根据题目难度获取搜索Problem
     * @param diff
     * @return
     */
    @Query("match (d:Difficulty {difficultyString:$diff} )-[r:`难度`]->(p:Problem) return p limit 10")
    List<Problem> getProByDiff(String diff);

    /**
     * 获取指定难度的题目数量
     * @param diff
     * @return
     */
    @Query("MATCH(:Difficulty{difficultyString:$diff})-[]-(n:Problem) RETURN count(n)")
    int getCount(String diff);

    /**
     * 根据难度和题库找到题目
     * @param dif
     * @param source
     * @return
     */
    @Query("match(dif:Difficulty{difficultyString:$dif})-[]-(p:Problem) return p limit 10\n" +
            "union\n" +
            "match(t:Types{tagString:$source})-[]-(p:Problem) return p limit 10")
    List<Problem> getProByDifAndSource(String dif,String source);

    /**
     * 查找和dif,alg都有关系的题目
     * @param dif 难度名
     * @param alg 算法名
     * @param page 起始页
     * @param size 页数量
     * @return
     */
    @Query("" +
            "match (d:Difficulty{difficultyString:$dif})-[]-(p:Problem) with p\n" +
            "match (t:Tags{name:$alg})-[]-(p:Problem) \n" +
            "return p skip $page limit $size" +
            "")
    List<Problem> getProByDifAndAlg(String dif,String alg,int page,int size);


}
