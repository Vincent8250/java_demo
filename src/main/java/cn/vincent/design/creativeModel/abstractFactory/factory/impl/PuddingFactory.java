package cn.vincent.design.creativeModel.abstractFactory.factory.impl;

import cn.vincent.design.creativeModel.abstractFactory.factory.PurchaseFactory;
import cn.vincent.design.creativeModel.abstractFactory.product.Materials;
import cn.vincent.design.creativeModel.abstractFactory.product.Packages;
import cn.vincent.design.creativeModel.abstractFactory.product.impl.PuddingMaterials;
import cn.vincent.design.creativeModel.abstractFactory.product.impl.PuddingPackage;

public class PuddingFactory implements PurchaseFactory {

    @Override
    public Materials getMaterials() {
        return new PuddingMaterials();
    }

    @Override
    public Packages getPackage() {
        return new PuddingPackage();
    }
}
