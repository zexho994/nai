package com.zyaml.nai.util;

import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;

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
    public static void tagListToMsg(List<Tags> list, StringBuilder sb){
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

    public static void diffListToMsg(List<Difficulty> difficultyList,StringBuilder sb){
        int i = 1;
        for(Difficulty difficulty : difficultyList){
            sb.append("\n").append(i++).append(".").append(difficulty.getDifficultyString());
        }
    }

    public static void typesListToMsg(List<Types> typesList,StringBuilder sb){
        int i = 1;
        for(Types type : typesList){
            sb.append("\n").append(i++).append(".").append(type.getTagString());
        }
    }

}
