package com.zyaml.nai.entry.from;

import lombok.Data;

import javax.validation.constraints.Min;

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
    @Min(0)
    Integer page;

    /**
     * 页大小,默认为10
     */
    @Min(0)
    Integer size;

}
