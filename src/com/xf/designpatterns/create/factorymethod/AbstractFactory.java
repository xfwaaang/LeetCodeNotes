package com.xf.designpatterns.create.factorymethod;

import com.xf.designpatterns.create.simplefactory.Product;

/**
 * @author xfwaaang
 * @create 2019-09-16 22:11
 * 工厂方法模式
 */
public abstract class AbstractFactory {
    abstract public Product createProduct();
}
