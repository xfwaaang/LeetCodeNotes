package com.xf.test;

import java.lang.annotation.*;

/**
 * @author xfwaaang
 * @create 2019-09-08 11:42
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TypeStringC.class)
public @interface TypeString {
    String value() default "";
}
