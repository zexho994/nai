package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * 相似度,推荐模块
 * @Author: 994
 * @Date: 2020-04-27 14:21
 */
public interface RecommendCql extends Neo4jRepository<ProblemCql,Long> {

    /**
     * 相同算法相同难度的题
     *
     * TODO: cql语句待改进
     * @param title
     * @return
     */
    @Query("match (Problem{title:$title})-[]-(d:Difficulty)-[]-(p:Problem) with p " +
            "match (Problem{title:$title})-[]-(t:Tags{type:\"Algorithm\"})-[]-(p:Problem) " +
            "return p limit 3")
    List<Problem> sameDifSameAlg(String title);

    /**
     * 相同算法,难度的题
     *
     * TODO: cql语句待优化
     * @param title
     * @return
     */
    @Query("match (Problem{title:$title})-[]-(t:Tags{type:\"Algorithm\"})-[]- "+
            "(p:Problem)-[]-(d:Difficulty{difficulty:$dif})"+
            "return p limit 3")
    List<Problem> sameALgHighDif(String title,int dif);

    /**
     * 相同难度的不同算法题
     *
     * TODO: 暂时还没去本体,待优化
     * @return
     */
    @Query("MATCH (Difficulty{difficulty:$dif})-[]-(p:Problem)-[]-(a:Tags{type:\"Algorithm\"})"+
            "WHERE NOT a.name IN $alg " +
            "return p LIMIT 3 ")
    List<Problem> samDifNotAlg(List<String> alg,int dif);
}
