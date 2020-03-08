package com.zyaml.nai.comom;

import java.util.HashMap;

/**
 * 存储条件标签类型的词典
 * @Author: 994
 * @Date: 2020-03-06 22:17
 */
public final class ContionsDictionary {
    private static final String great = "大于";
    private static final String less = "小于";
    private static final String eq  = "等于";
    private static final String same1 = "一样";
    private static final String same2 = "相同";
    private static final String name1 = "名字";
    private static final String name2 = "名称";
    private static final String diff ="难度";
    private static final String alg = "算法";
    private static final String time1 = "时间";
    private static final String time2 = "时候";
    private static final String source1 =  "题库";
    private static final String source2 =  "来源";


    /**
     * 更具字符串匹配对于的条件类型
     * @param name
     * @return
     */
    public static String match(String name){
        switch (name){
            case great:  return "great";
            case less:   return "less";
            case eq:     return "eq";
            case same1:  return "same";
            case same2:  return "same";
            case diff:   return "dif";
            case name1:  return "name";
            case name2:  return "name";
            case alg:    return "time";
            case time1:  return "time";
            case time2:  return "time";
            case source1:return "source";
            case source2:return "source";
            default:     return "";
        }
    }
}
