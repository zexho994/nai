package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-15 15:40
 */
public interface TimeCql extends Neo4jRepository<Tags,Long> {

    @Query("MATCH (n:Tags{type:\"Time\"}) RETURN n")
    List<Tags> getTimeTag();


    @Query("match (t:Tags{type:\"Time\"})-[]-(p:Problem) where t.name = $time return p skip $page*$size limit $size")
    List<Problem> findByTime(String time,int page,int size);

}
