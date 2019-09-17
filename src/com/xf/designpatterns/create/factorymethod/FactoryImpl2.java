package com.xf.designpatterns.create.factorymethod;

import com.xf.designpatterns.create.simplefactory.Product;
import com.xf.designpatterns.create.simplefactory.ProductImpl2;

/**
 * @author xfwaaang
 * @create 2019-09-16 22:13
 */
public class FactoryImpl2 extends AbstractFactory {
    @Override
    public Product createProduct() {
        return new ProductImpl2();
    }
}
