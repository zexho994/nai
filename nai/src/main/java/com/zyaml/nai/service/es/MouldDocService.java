package com.zyaml.nai.service.es;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.entry.from.MouldFrom;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 994
 * @Date: 2020-03-07 23:45
 */
@Service
public class MouldDocService {

    @Autowired
    ElasticSearchService elasticSearchService;

    /**
     * 添加mould
     * @param mouldFrom
     * @return
     * @throws IOException
     */
    public RestStatus add(MouldFrom mouldFrom) throws IOException {
        Map<String,Object> map = new HashMap<>(2);
        map.put("num:",mouldFrom.getNum());
        map.put("format",mouldFrom.getFormat());
        RestStatus status = elasticSearchService.add("mould", map);

        return status;
    }

    /**
     * 搜索mould索引的文档
     * @param page 起始页
     * @param size 页大小
     * @return
     * @throws IOException
     */
    public Map search(int page,int size) throws IOException {
        Map moulds = elasticSearchService.searchAll("mould", page, size);
        return moulds;
    }

    /**
     * 根据format获取num
     * @param format
     */
    public int getNumByFormat(String format){
        Map<String,Object> stringObjectMap;
        try {
            stringObjectMap = elasticSearchService.matchParse("mould", "format", format);
        } catch (IOException e) {
            throw new RestException(ErrorCode.ELASTIC_ERROR);
        }
        int num = (int) stringObjectMap.get("num");
        return num;
    }

}
