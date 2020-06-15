package com.zyaml.nai.entry.dto;

import com.zyaml.nai.comom.ContionsDictionary;
import lombok.Data;

import java.util.*;

/**
 * 单词串
 *
 * <功能 1>为了方便单词的存储于获取</功能 1>
 * <功能 2>提供format格式获取，用来匹配语义</功能 2>
 * @Author: 994
 * @Date: 2020-03-09 16:50
 */
@Data
public class Words<K,V> {

    private List<Word> list;
    private StringBuilder format = new StringBuilder();
    private final String con = "CON";
    private int size = 0;

    @Data
    class Word{
        private K ne;
        private V item;

        public Word(K n,V i){
            this.ne = n;
            this.item = i;
        }
    }

    /**
     * 添加单词
     * @param k 单词的词性 ne表示
     * @param v 词内容 item表示
     */
    public void add(K k,V v){
        if(k == null || "".equals(k)){
            return;
        }
        //list 初始化
        synchronized (this){
            if(list == null ){
                list = new ArrayList<>();
            }
        }
        if(con.equals(k)){
            k = (K) ContionsDictionary.match((String) v);
        }else if("YEAR".equals(k)){
            String s = (String) v;
            v = (V) s.replace("年","");
        }

        list.add(new Word(k,v));
        format.append(k).append("+");
        size++;
    }

    /**
     * 获取单词串的格式
     * @return
     */
    public String getFormat(){
        return format.toString();
    }

    /**
     * 获取多个值
     * @param k
     * @return
     */
    public List<V> getAll(K k){
        Iterator<Word> iterator = list.iterator();
        List l = new ArrayList();
        while (iterator.hasNext()){
            Word next = iterator.next();
            if(next.getNe().equals(k)){
                l.add(next.getItem());
            }
        }
        return l;
    }

    /**
     * 获取单个值
     * @param k
     * @return
     */
    public V get(K k){
        Iterator<Word> iterator = list.iterator();
        while (iterator.hasNext()){
            Word next = iterator.next();
            if(next.getNe().equals(k)){
                return next.getItem();
            }
        }
        return null;
    }
}
