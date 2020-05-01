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
     * @param diff 题目难度
     * @return
     */
    @Query("match (d:Difficulty {difficultyString:$diff} )-[r:`难度`]->(p:Problem) return p limit $size")
    List<Problem> getProByDiff(String diff,int size);

    /**
     * 获取指定难度的题目数量
     * @param diff
     * @return
     */
    @Query("MATCH(:Difficulty{difficultyString:$diff})-[]-(n:Problem) RETURN count(n)")
    int getCount(String diff);

    /**
     * 根据难度和题库找到题目
     * @param dif 题目难度
     * @param source 题库
     * @return
     */
    @Query("match (p:Problem)-[]-(d:Difficulty{difficultyString:$dif}) with p\n" +
            "match (p:Problem) -[]-(t:Types{tagString:$source})\n" +
            "return p skip $page*$size limit $size")
    List<Problem> getProByDifAndSource(String dif,String source,int page,int size);

    /**
     * 匹配和dif,alg都有关系的题目节点
     * @param dif 难度名
     * @param alg 算法名
     * @param page 起始页
     * @param size 页数量
     * @return
     */
    @Query("match (d:Difficulty{difficultyString:$dif})-[]-(p:Problem) with p\n" +
            "match (t:Tags{name:$alg})-[]-(p:Problem) \n" +
            "return p skip $page*$size limit $size")
    List<Problem> getProByDifAndAlg(String dif,String alg,int page,int size);


    /**
     * 匹配和dif,reg有关系的题目节点
     * @param dif 难度名
     * @param reg 省份
     * @param page 起始页
     * @param size 页大小
     * @return
     */
    @Query("MATCH (t:Tags{name:$reg})-[]-(p:Problem)-[]-(d:Difficulty{difficultyString:$dif}) RETURN p skip $page*$size limit $size")
    List<Problem> getProByDifAndReg(String dif,String reg,int page,int size);

    /**
     * 匹配和dif,time有关系的题目节点
     * @param dif 难度名称
     * @param time 出题时间
     * @param page 起始页
     * @param size 页大小
     * @return
     */
    @Query("match (p:Problem) -[]-(d:Difficulty{difficultyString:$dif}) with p \n" +
            "match (p:Problem) -[] -(t:Tags{type:'Time',name:$time})" +
            "return p skip $size*$page limit $size")
    List<Problem> getProByDifAndTime(String dif,String time,int page,int size);

    /**
     * 匹配和dif,ori有关系的题目节点
     * @param dif 难度名称
     * @param ori 来源名称
     * @param page 起始页
     * @param size 页大小
     * @return
     */
    @Query("match (o:Tags{type:\"Origin\",name:$ori})-[]-(p:Problem) with p \n" +
            "match (p:Problem) - [] -(d:Difficulty{difficultyString:$dif}) \n" +
            "return  p skip $page*$size limit $size")
    List<Problem> getProByDifAndOri(String dif,String ori,int page,int size);

    /**
     * 时间条件查询
     * 时间范围：大于time的范围
     */
    @Query("match (p:Problem) -[]-(t:Tags{type:\"Time\"}) where t.name > $time with p \n" +
            "match (p:Problem) -[]-(d:Difficulty{difficultyString:$dif})\n" +
            "return p skip $page*$size limit $size")
    List<Problem> getProByDifAndGT(String dif,String time,int page,int size);

    /**
     * 时间范围：下雨time的范围
     */
    @Query("match (p:Problem) -[]-(t:Tags{type:\"Time\"}) where t.name < $time with p \n" +
            "match (p:Problem) -[]-(d:Difficulty{difficultyString:$dif})\n" +
            "return p skip $page*$size limit $size")
    List<Problem> getProByDifAndLT(String dif,String time,int page,int size);

    @Query("match (d:Difficulty) return d")
    List<Difficulty> getAllDif();


}
