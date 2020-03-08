package com.zyaml.nai.entry.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * QA 问答系统的入参形式
 * @Author: 994
 * @Date: 2020-03-08 14:40
 */
@Data
public class QAFrom {

    @NotBlank
    private String msg;

}
