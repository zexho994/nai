package com.zyaml.nai.entry.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 994
 * @Date: 2020-03-06 14:43
 */
@Data
public class ProblemFrom {

    @NotBlank
    String diff;
}
