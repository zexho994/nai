package com.zyaml.nai.entry.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: 994
 * @Date: 2020-03-06 00:09
 */
@Data
public class ProblemVO {

    @JsonProperty("pid")
    private String pid;

    @JsonProperty("title")
    private String title;

}
