package com.xf.test;

import java.lang.annotation.*;

/**
 * @author xfwaaang
 * @create 2019-09-08 11:41
 * 定义注解不要忘了加元注解
 * 可重复注解需要定义一个注解容器，如：TypeIntC.class
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TypeIntC.class)
public @interface TypeInt {
    int value() default -1;     //属性命名为value时，参数赋值时可以省略value，即 value="" 和 "" 均可以
}
