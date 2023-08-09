package cn.vincent.design.creativeModel.factory.product.impl;

import cn.vincent.design.creativeModel.factory.product.IceCream;

/**
 * 巧乐兹
 */
public class Chocoz implements IceCream {
    @Override
    public String getName() {
        return "巧乐兹";
    }

    @Override
    public String taste() {
        return "巧克力味";
    }
}
