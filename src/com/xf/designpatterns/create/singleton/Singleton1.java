package com.xf.designpatterns.create.singleton;

/**
 * @author xfwaaang
 * @create 2019-09-16 21:30
 * 懒汉式 - 线程不安全
 * 私有静态变量被延迟实例化，没有用到该类就不会实例化，从而节约资源
 */
public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1(){}

    public static Singleton1 getInstance(){
        if (instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}
