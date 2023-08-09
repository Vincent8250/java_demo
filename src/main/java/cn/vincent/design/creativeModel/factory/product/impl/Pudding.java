package cn.vincent.design.creativeModel.factory.product.impl;

import cn.vincent.design.creativeModel.factory.product.IceCream;

/**
 * 小布丁
 */
public class Pudding implements IceCream {
    @Override
    public String getName() {
        return "小布丁";
    }

    @Override
    public String taste() {
        return "牛奶味";
    }
}
