package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-24 13:07
 */
public interface TitleCql extends Neo4jRepository<Problem,Long> {

    @Query("MATCH (p:Problem{title:$title}) - [] - (t:Tags{type:\"Algorithm\"}) RETURN t")
    List<Tags> findAlg(String title);

    @Query("MATCH (p:Problem{title:$title}) - [] - (t:Tags{type:\"Origin\"}) RETURN t")
    List<Tags> findOri(String title);

    @Query("MATCH (p:Problem{title:$title}) - [] - (t:Tags{type:\"Time\"}) RETURN t limit 1")
    Tags findTime(String title);

    @Query("MATCH (p:Problem{title:$title}) RETURN p limit 1")
    Problem findProblem(String title);

    @Query("match (:Problem{title:$title}) - [] - (d:Difficulty) return d limit 1")
    Difficulty findDiff(String title);

    @Query("MATCH (:Problem{title:$title}) - [] - (t:Tags{type:\"Region\"}) RETURN t limit 1")
    Tags findReg(String title);

    @Query("MATCH (:Problem{title:$title}) - [] - (t:Types) RETURN t limit 1")
    Types findTK(String title);

    @Query("match (p:Problem{title:$title})-[]-(a:Answer) return a limit 1")
    Answer findAnswer(String title);

}
