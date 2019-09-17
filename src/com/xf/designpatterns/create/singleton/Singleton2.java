package com.xf.designpatterns.create.singleton;

/**
 * @author xfwaaang
 * @create 2019-09-16 21:34
 * 饿汉式 - 线程安全
 * 静态变量只会被实例化一次，因此线程安全
 * 但是直接实例化失去了延迟实例化带来的节约资源的好处
 */
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();

    private Singleton2(){}

    public static Singleton2 getInstance(){
        return instance;
    }
}
