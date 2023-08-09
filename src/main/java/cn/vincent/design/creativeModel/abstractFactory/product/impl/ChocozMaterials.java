package cn.vincent.design.creativeModel.abstractFactory.product.impl;

import cn.vincent.design.creativeModel.abstractFactory.product.Materials;

/**
 * 巧乐兹材料
 */
public class ChocozMaterials implements Materials {

    @Override
    public String getMaterials() {
        return "巧乐兹材料";
    }

    @Override
    public String name() {
        return "伊利提供商";
    }
}
