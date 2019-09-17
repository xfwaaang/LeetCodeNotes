package com.xf.designpatterns.create.singleton;

/**
 * @author xfwaaang
 * @create 2019-09-16 21:37
 * 懒汉式 - 线程安全
 * 对方法加锁，即使已被实例化，线程也需要等待，存在性能问题
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3(){}

    public static synchronized Singleton3 getInstance(){
        if (instance == null){
            instance =  new Singleton3();
        }
        return instance;
    }
}
