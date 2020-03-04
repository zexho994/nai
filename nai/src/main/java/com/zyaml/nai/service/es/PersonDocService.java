package com.zyaml.nai.service.es;

import com.zyaml.nai.entry.doc.PersonDoc;


import com.zyaml.nai.util.ElasticSearchUtil;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ElasticSearch的基础配置类
 * 提供最基本的查询、新增、删除等功能
 * @Author: 994
 * @Date: 2020-02-28 17:57
 */
@Service
public class PersonDocService {
    final SearchSourceBuilder builder = new SearchSourceBuilder();
    static final List<PersonDoc> nullList = new ArrayList<>(2);
    static final PersonDoc nullPersonDoc= new PersonDoc();

    /**
     * 根据姓名获取一个数据
     * @param name
     * @return
     */
    public PersonDoc getByName(String name) {
        builder.query(new TermQueryBuilder("name", name));
        List<PersonDoc> res = ElasticSearchUtil.search("person", builder, PersonDoc.class);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return nullPersonDoc;
        }
    }

    /**
     * 根据姓名获取所有的数据
     * @param name
     * @return
     */
    public List<PersonDoc> getAllByName(String name){
        builder.query(new TermQueryBuilder("name", name));
        List<PersonDoc> res = ElasticSearchUtil.search("person", builder, PersonDoc.class);
        if(res == null){
            return nullList;
        }
        return res;
    }

}
