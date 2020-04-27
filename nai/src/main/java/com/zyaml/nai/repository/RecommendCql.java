package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Problem;
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
     * 相同算法,更高难度的题
     *
     * TODO: cql语句待优化
     * @param title
     * @return
     */
    @Query("match (Problem{title:\"超级玛丽游戏\"})-[]-(d:Difficulty) with d " +
            "match (n:Problem)-[]-(di:Difficulty) where di.difficulty = d.difficulty+1 with n " +
            "match (Problem{title:\"超级玛丽游戏\"})-[]-(t:Tags{type:\"Algorithm\"})-[]-(n:Problem) " +
            "return n limit 3")
    List<Problem> sameALgHighDif(String title);

    /**
     * 相同难度的不同算法题
     *
     * TODO: 暂时还没去本体,待优化
     * @param title
     * @return
     */
    @Query("match (Problem{title:$title})-[]-(d:Difficulty) with d " +
            "match (n:Problem)-[]-(d:Difficulty) " +
            "return n limit 3")
    List<Problem> samDifNotAlg(String title);
}
