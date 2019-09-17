package com.xf.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xfwaaang
 * @create 2019-09-13 20:11
 * TypeInt注解容器
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeIntC {
    TypeInt[] value() default {};
}
