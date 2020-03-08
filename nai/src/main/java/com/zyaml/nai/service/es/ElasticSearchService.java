package com.zyaml.nai.service.es;

import org.apache.http.HttpHost;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 提供最基础的ES API
 * @Author: 994
 * @Date: 2020-02-28 18:07
 */
@Service
public class ElasticSearchService {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9200;
    private static String SCHEME = "http";

    private final SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

    /**
     * 提供search的类
     */
    private final SearchRequest searchRequest = new SearchRequest();

    /**
     * 创建一个rest高级客户端
     */
    public static final RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(HOST,PORT,SCHEME)));

    /**
     * 判断某个index是否存在
     * @param index index名
     * @return boolean
     */
    public boolean indexExist(String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * Parse搜索
     * Parse执行严格匹配模式
     * @param key 字段名
     * @param val 字段值
     */
    public Map<String, Object> matchParse(String index, String key, String val) throws IOException {
        //设置builder参数
        sourceBuilder.query(QueryBuilders.matchPhraseQuery(key, val));
        sourceBuilder.from(0);
        sourceBuilder.size(1);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //设计request参数
        searchRequest.indices(index);
        searchRequest.source(sourceBuilder);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHit at = search.getHits().getAt(0);

        Map<String, Object> sourceAsMap = at.getSourceAsMap();

        return sourceAsMap;
    }

    /**
     * 添加新的文档到索引中
     * @param index 要存储的索引
     * @param doc 存储的文档
     * return 操作的结果
     * @throws IOException
     */
    public RestStatus add(String index, Map<String,Object> doc) throws IOException {
        IndexRequest request = new IndexRequest(index).source(doc);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return response.status();
    }

    /**
     * 获取某一个节点下所有的文档
     * @param index 索引名
     * @param page 起始位置
     * @param size 页大小
     * @return
     * @throws IOException
     */
    public Map<Integer, Map<String, Object>> searchAll(String index, int page, int size) throws IOException {

        searchRequest.indices(index);
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        //设置分页
        sourceBuilder.from(page);
        sourceBuilder.size(size);
        searchRequest.source(sourceBuilder);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        Map<Integer,Map<String,Object>> map = new HashMap<>(16);

        Integer i = 0;

        for(SearchHit hit : hits1){
            map.put(i++,hit.getSourceAsMap());
        }

        return map;
    }
}
