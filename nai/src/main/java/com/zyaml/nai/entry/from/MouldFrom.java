package com.zyaml.nai.entry.from;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @Author: 994
 * @Date: 2020-03-08 16:25
 */
@Data
public class MouldFrom {

    /**
     * 模板的编号
     * 格式为 Integer：xxxx
     */
    @Min(value = 1001)
    @Max(value = 4999)
    private Integer num;

    /**
     * 模板的格式
     * 例如："PID+DIF+TIME+"
     */
    @NotBlank
    private String format;

}
