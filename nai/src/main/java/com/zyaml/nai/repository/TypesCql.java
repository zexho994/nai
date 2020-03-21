package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * 以题库为主语的CQL
 *
 * @Author: 994
 * @Date: 2020-03-15 15:39
 */
public interface TypesCql extends Neo4jRepository<Types,Long> {

    /**
     * 根据题库获取相关题目节点
     * @param tk
     * @param page
     * @param size
     * @return
     */
    @Query("MATCH (t:Types{tagString:$tk}) - [] - (p:Problem) RETURN p skip $page*$size limit $size")
    List<Problem> getByType(String tk,int page,int size);

    /**
     * 根据题库和来源获取相关节点
     * @param tk
     * @param ori
     * @param page
     * @param size
     * @return
     */
    @Query("match (t:Types{tagString:$tk}) - [] - (p:Problem) with p\n" +
            "match (p:Problem)-[]-(t:Tags{type:\"Origin\",name:$ori}) \n" +
            "return p skip $page*$size limit $size")
    List<Problem> getByTypeAndOri(String tk,String ori,int page,int size);

    /**
     * 根据题库和身份获取相关节点
     * @param tk
     * @param loc
     * @param page
     * @param size
     * @return
     */
    @Query("match (t:Types{tagString:$tk}) - [] - (p:Problem) with p\n" +
            "match (p:Problem)-[]-(t:Tags{type:\"Region\",name:$loc}) \n" +
            "return p skip $page*$size limit $size")
    List<Problem> getByTypeAndLoc(String tk,String loc,int page,int size);

    /**
     * 根据题库和时间获取相关节点
     * @param tk
     * @param time
     * @param page
     * @param size
     * @return
     */
    @Query("match (t:Types{tagString:$tk}) - [] - (p:Problem) with p\n" +
            "match (p:Problem)-[]-(t:Tags{type:\"Time\",name:$time}) \n" +
            "return p skip $page*$size limit $size")
    List<Problem> getByTypeAndTime(String tk,String time,int page,int size);


}
