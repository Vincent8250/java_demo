package cn.vincent.design.creativeModel.abstractFactory.factory;

import cn.vincent.design.creativeModel.abstractFactory.product.Materials;
import cn.vincent.design.creativeModel.abstractFactory.product.Packages;

/**
 * 抽象工厂
 */
public interface PurchaseFactory {

    Materials getMaterials();

    Packages getPackage();
}
