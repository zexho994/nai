package com.zyaml.nai.entry.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 994
 * @Date: 2020-03-05 20:24
 */
@Data
public class Lexicalfrom {
    /**
     * 待词法分析的语句
     */
    @NotBlank
    private String msg;
}
