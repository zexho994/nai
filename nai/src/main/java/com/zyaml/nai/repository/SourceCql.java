package com.zyaml.nai.repository;

import com.zyaml.nai.entry.node.Types;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @Author: 994
 * @Date: 2020-03-15 15:41
 */
public interface SourceCql extends Neo4jRepository<Types,Long> {
}
