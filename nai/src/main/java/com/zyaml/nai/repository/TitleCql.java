package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
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

    @Query("MATCH (:Problem{title:$title}) - [] - (t:Tags{type:\"Origin\"}) RETURN t")
    Tags findOri(String title);

    @Query("MATCH (:Problem{title:$title}) - [] - (t:Tags{type:\"Time\"}) RETURN t")
    Tags findTime(String title);

    @Query("MATCH (p:Problem{title:$title}) RETURN p")
    Problem findProblem(String title);

    @Query("match (:Problem{title:$title}) - [] - (d:Difficulty) return d")
    Difficulty findDiff(String title);

    @Query("MATCH (:Problem{title:$title}) - [] - (t:Tags{type:\"Region\"}) RETURN t")
    Tags findReg(String title);

    @Query("MATCH (:Problem{title:$title}) - [] - (t:Types) RETURN t")
    Types findTK(String title);

}
