package com.xf.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xfwaaang
 * @create 2019-09-13 19:39
 * 注解容器
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeIntsC {
    TypeInts[] value() default {};
}
