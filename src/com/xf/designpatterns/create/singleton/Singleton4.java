package com.xf.designpatterns.create.singleton;

/**
 * @author xfwaaang
 * @create 2019-09-16 21:42
 * 双重校验锁 - 线程安全
 * 只对实例化部分代码加锁
 * volatile可以禁止JVM的指令重排，保证在多线程的环境下也能正常运行
 */
public class Singleton4 {
    private volatile static Singleton4 instance;

    private Singleton4(){}

    public static Singleton4 getInstance(){
        if (instance == null){
            synchronized (Singleton4.class){
                if (instance == null){
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
