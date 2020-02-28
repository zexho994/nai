package com.zyaml.nai.repository.neo;

import com.zyaml.nai.entry.node.PersonNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-02-13 11:32
 */
public interface PersonCql extends CrudRepository<PersonNode,Long> {

    /**
     * 不带条件的获取所有 person 节点
     * 默认限制 50 条
     * @return
     */
    @Query(value = "match (n:Person) return n limit 50;")
    List<PersonNode> matchAll();

    /**
     * 查找单个 person 对象
     * @param name 要查询的人名
     * @return
     */
    PersonNode findPersonByName(String name);
}
