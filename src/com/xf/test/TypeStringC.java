package com.xf.test;

import java.lang.annotation.*;

/**
 * @author xfwaaang
 * @create 2019-09-13 20:13\
 * TypeString注解容器
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeStringC {
    TypeString[] value() default {};
}
