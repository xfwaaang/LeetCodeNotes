package com.xf.algorithm.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xfwaaang
 * @create 2019-09-08 11:41
 * 定义注解不要忘了加元注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeInt {
    int value() default -1;     //属性命名为value时，赋值时可以省略value，即 value="" 和 "" 均可以
}
