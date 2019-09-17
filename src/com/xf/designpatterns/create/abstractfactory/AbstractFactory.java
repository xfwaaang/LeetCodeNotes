package com.xf.designpatterns.create.abstractfactory;

/**
 * @author xfwaaang
 * @create 2019-09-16 22:18
 * 抽象工厂模式
 */
public abstract class AbstractFactory {
    abstract public ProductA createProductA();
    abstract public ProductB createProductB();
}
