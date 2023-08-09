package cn.vincent.design.creativeModel.abstractFactory.product.impl;

import cn.vincent.design.creativeModel.abstractFactory.product.Materials;
import cn.vincent.design.creativeModel.abstractFactory.product.Packages;

/**
 * 小布丁材料
 */
public class PuddingPackage implements Packages {

    @Override
    public String getPackages() {
        return "小布丁包装";
    }

    @Override
    public String name() {
        return "蒙牛提供商";
    }
}
