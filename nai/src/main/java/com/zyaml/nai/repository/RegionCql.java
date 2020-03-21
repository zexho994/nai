package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Problem;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-21 21:09
 */
public interface RegionCql extends Neo4jRepository<Type,Long> {

    @Query("match (t:Tags{type:\"Region\",name:$reg}) - [] - (p:Problem) return p skip $page*$size limit $size")
    List<Problem> getByReg(String reg,int page, int size);

    @Query("match (t:Tags{name:$reg}) - [] - (p:Problem) with p\n" +
            "match (p:Problem)-[]-(t:Tags{type:\"Time\",name:$time}) \n" +
            "return p skip $page*$size limit $size")
    List<Problem> getByRegAndTime(String reg, String time, int page, int size);

}
