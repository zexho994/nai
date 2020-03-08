package com.zyaml.nai.util;

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
public final class JsonUtil {

    /**
     * 解析JsonObject
     * 专有词存储map中
     * 获取<item>单词<item/>和<ne>词性<ne/>
     * ne为key，item为value
     * @param jsonObject
     */
    public final static Map getItemAndNe(JSONObject jsonObject){
        JSONArray items = (JSONArray) jsonObject.get("items");
        Iterator<Object> iterator = items.iterator();


        HashMap<String,String> map = new HashMap<>(8);
        JSONObject object;
        while(iterator.hasNext()){
            object = (JSONObject) iterator.next();
            if(object.get("ne")!=null&&!object.get("ne").equals("")){
                map.put((String)object.get("ne"),(String)object.get("item"));
            }
        }
        return map;
    }
}
