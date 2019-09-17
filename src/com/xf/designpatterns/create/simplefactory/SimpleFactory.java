package com.xf.designpatterns.create.simplefactory;

/**
 * @author xfwaaang
 * @create 2019-09-16 22:06
 * 简单工厂模式
 */
public class SimpleFactory {
    public Product createProduct(int type){
        if (type == 1){
            return new ProductImpl1();
        }else if (type == 2){
            return new ProductImpl2();
        }

        return null;
    }
}
