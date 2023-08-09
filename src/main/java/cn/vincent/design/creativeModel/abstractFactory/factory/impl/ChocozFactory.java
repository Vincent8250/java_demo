package cn.vincent.design.creativeModel.abstractFactory.factory.impl;

import cn.vincent.design.creativeModel.abstractFactory.factory.PurchaseFactory;
import cn.vincent.design.creativeModel.abstractFactory.product.Materials;
import cn.vincent.design.creativeModel.abstractFactory.product.Packages;
import cn.vincent.design.creativeModel.abstractFactory.product.impl.ChocozMaterials;
import cn.vincent.design.creativeModel.abstractFactory.product.impl.ChocozPackage;

public class ChocozFactory implements PurchaseFactory {
    @Override
    public Materials getMaterials() {
        return new ChocozMaterials();
    }

    @Override
    public Packages getPackage() {
        return new ChocozPackage();
    }
}
