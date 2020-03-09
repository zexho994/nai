package com.zyaml.nai.util;

import com.zyaml.nai.comom.ContionsDictionary;
import com.zyaml.nai.entry.dto.Words;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 解析json工具
 * @Author: 994
 * @Date: 2020-03-05 15:11
 */
@Log4j2
public final class JsonUtil {
    /**
     * 解析JsonObject
     * 专有词存储map中
     * 获取<item>单词<item/>和<ne>词性<ne/>
     * ne为key，item为value
     * @param jsonObject
     */
    public final static Words getItemAndNe(JSONObject jsonObject){
        Words words = new Words();
        JSONArray items = (JSONArray) jsonObject.get("items");
        Iterator<Object> iterator = items.iterator();

        StringBuilder sb = new StringBuilder();

        HashMap<String,String> map = new HashMap<>(8);
        JSONObject object;
        while(iterator.hasNext()){
            object = (JSONObject) iterator.next();
            words.add(object.get("ne"),object.get("item"));
        }
        map.put("format",sb.toString());
        log.info("=====> combinationString ："+sb.toString());

        return words;
    }
}
