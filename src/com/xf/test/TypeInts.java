package com.xf.test;

import java.lang.annotation.*;

/**
 * @author xfwaaang
 * @create 2019-09-08 11:42
 * 可重复注解需要定义一个注解容器，如：TypeIntsC.class
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TypeIntsC.class)
public @interface TypeInts {
    int[] value() default {};
}
