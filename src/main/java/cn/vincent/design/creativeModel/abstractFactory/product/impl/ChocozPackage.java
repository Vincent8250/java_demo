package cn.vincent.design.creativeModel.abstractFactory.product.impl;

import cn.vincent.design.creativeModel.abstractFactory.product.Materials;
import cn.vincent.design.creativeModel.abstractFactory.product.Packages;

/**
 * 巧乐兹材料
 */
public class ChocozPackage implements Packages {

    @Override
    public String getPackages() {
        return "巧乐兹包装";
    }

    @Override
    public String name() {
        return "伊利提供商";
    }
}
