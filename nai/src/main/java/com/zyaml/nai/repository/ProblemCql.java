package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 *
 * @Author: 994
 * @Date: 2020-03-05 23:57
 */
public interface ProblemCql extends Neo4jRepository<Problem,Long> {

    /**
     * 根据题号获取题目信息
     * @param pid
     * @return
     */
    Problem getProblemByPid(String pid);

    /**
     * 获取和题号pid一样难度的其他题目
     * @param pid
     * @return
     */
    @Query("match(n:Problem{pid:$pid})-[]-(d:Difficulty)-[]-(m:Problem) return m")
    List<Problem> getProblemsByPidAndDiff(String pid);

    /**
     * 根据题号pid的难度
     * @param pid
     * @return
     */
    @Query("match (p:Problem{pid:$pid})-[]-(d:Difficulty) return d")
    Difficulty getDifByPid(String pid);

    /**
     * 获取pid的名称
     * @param pid
     * @return
     */
    @Query("match (p:Problem{pid:$pid}) return p")
    Problem getTitle(String pid);

    /**
     * 获取题库
     * @param pid
     * @return
     */
    @Query("MATCH(n:Problem{pid:$pid}) -[r]- (t:Types) RETURN t")
    Types getType(String pid);

    @Query("match (p:Problem) return p.title")
    List<String> getAll();

    /**
     * 根据 pid 获取题目算法标签
     * @param pid
     * @return
     */
    @Query("match (p:Problem{pid:$pid})-[]-(t:Tags{type:\"Algorithm\"}) return t ")
    List<Tags> getAlgByPid(String pid);

    /**
     * 匹配pid节点的省份节点
     * @param pid
     * @return
     */
    @Query("MATCH (p:Problem{pid:$pid})-[]-(t:Tags{type:\"Region\"}) RETURN t")
    Tags getRegin(String pid);

    /**
     * 匹配pid节点的时间节点
     * @param pid
     * @return
     */
    @Query("MATCH (p:Problem{pid:$pid})-[]-(t:Tags{type:\"Time\"}) RETURN t")
    Tags getTime(String pid);

    /**
     * 匹配pid节点的来源节点
     * @param pid
     * @return
     */
    @Query("MATCH (p:Problem{pid:$pid})-[]-(o:Tags{type:\"Origin\"}) RETURN o")
    List<Tags> getOri(String pid);

}
