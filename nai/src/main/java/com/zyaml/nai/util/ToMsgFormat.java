package com.zyaml.nai.util;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-24 14:08
 */
public final class ToMsgFormat {

    /**
     * 追加算法标签内容
     *
     * 在sb后面追加list里面的算法名称并标序
     * @param list
     * @param sb
     * @return
     */
    public static void algListToMsg(List<Tags> list, StringBuilder sb){
        int i = 0;
        for(Tags t : list){
            sb.append(++i+".").append(t.getName()).append("\n");
        }
    }

    public static void titleList(List<Problem> list,StringBuilder sb){
        int i = 0;
        for(Problem p : list){
            sb.append(++i+".").append(p.getTitle()).append("\n");
        }
    }
}
