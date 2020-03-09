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

    String format() default "";

}
