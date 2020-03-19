package com.zyaml.nai.util;

import java.lang.annotation.*;

/**
 * @Author: 994
 * @Date: 2020-03-08 22:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mould {

    /**
     * 存储模板的格式信息
     * 例如 "PID+num+"
     *
     * 如果有多个符合的格式,格式为 @Mould(format={"format1","format2"})
     * @return
     */
    String[] format();

}
