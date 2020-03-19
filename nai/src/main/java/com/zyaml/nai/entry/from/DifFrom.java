package com.zyaml.nai.entry.from;

import lombok.Data;

/**
 * @Author: 994
 * @Date: 2020-03-18 11:02
 */
@Data
public class DifFrom {
    /**
     * 难度名
     */
    String dif;

    /**
     * 题目名
     */
    String alg;

    /**
     * 起始页,默认为0
     */
    Integer page;

    /**
     * 页大小,默认为10
     */
    Integer size;

}
