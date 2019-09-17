package com.xf.designpatterns.create.singleton;

/**
 * @author xfwaaang
 * @create 2019-09-16 21:45
 * 静态内部类实现
 * 只有调用方法getInstance触发InstanceHolder.instance时InstanceHolder才会被加载
 * 不仅具有延迟初始化的好处，而且由JVM提供对线程安全的支持
 */
public class Singleton5 {
    private Singleton5(){}

    private static class InstanceHolder{
        private static final Singleton5 instance = new Singleton5();
    }

    public static Singleton5 getInstance(){
        return InstanceHolder.instance;
    }
}
