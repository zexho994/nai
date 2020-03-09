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
    List<Problem> getProblemsByDiff(String diff);
}
