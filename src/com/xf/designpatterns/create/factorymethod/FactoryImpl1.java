package com.xf.designpatterns.create.factorymethod;

import com.xf.designpatterns.create.simplefactory.Product;
import com.xf.designpatterns.create.simplefactory.ProductImpl1;

/**
 * @author xfwaaang
 * @create 2019-09-16 22:12
 */
public class FactoryImpl1 extends AbstractFactory {
    @Override
    public Product createProduct() {
        return new ProductImpl1();
    }
}
