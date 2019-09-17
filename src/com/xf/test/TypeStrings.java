package com.xf.test;

import java.lang.annotation.*;

/**
 * @author xfwaaang
 * @create 2019-09-08 11:43
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TypeStringsC.class)
public @interface TypeStrings {
    String[] value() default {};
}
