package com.xf.designpatterns.create.abstractfactory;

/**
 * @author xfwaaang
 * @create 2019-09-16 22:20
 */
public class FactoryImpl2 extends AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductAImpl2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductBImpl2();
    }
}
