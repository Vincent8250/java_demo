package cn.vincent.design.creativeModel.abstractFactory.product.impl;

import cn.vincent.design.creativeModel.abstractFactory.product.Materials;

/**
 * 小布丁材料
 */
public class PuddingMaterials implements Materials {

    @Override
    public String getMaterials() {
        return "小布丁材料";
    }

    @Override
    public String name() {
        return "蒙牛提供商";
    }
}
