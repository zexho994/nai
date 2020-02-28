package com.zyaml.nai.entry.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: 994
 * @Date: 2020-02-13 11:12
 */
@NodeEntity
public class PersonNode extends BaseNeo4jNode{

    private String name;

    @Relationship(type = "Friendship", direction = Relationship.UNDIRECTED)
    public Set<PersonNode> friendship;

    public PersonNode(){}

    public PersonNode(String name){
        this.name = name;
    }

    public void addFriendship(PersonNode personNode) {
        if (friendship == null) {
            friendship = new HashSet<>();
        }
        friendship.add(personNode);
    }

    @Override
    public String toString() {
        return "PersonNode:{"+this.name + "'s teammates => "
                + Optional.ofNullable(this.friendship).orElse(
                Collections.emptySet()).stream()
                .map(PersonNode::getName)
                .collect(Collectors.toList())+
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
