package com.zyaml.nai.comom;

/**
 * @Author: 994
 * @Date: 2020-03-04 22:59
 */
public enum DiffcultyComom {
    /**
     * 对应洛谷的难度
     */
    simple(1,"入门"),
    universal_low(2,"普及-"),
    universal_normal(3,"普及/提高-"),
    universal_high(4,"普及+/提高"),
    improve_low(5,"提高+/省选-"),
    imporve_normal(6,"省选/NOI-"),
    imporve_high(7,"NOI/NOI+/CTSC")

    ;

    public int id;

    public String name;

    DiffcultyComom(int id,String name){
        this.id = id;
        this.name = name;
    }

}
