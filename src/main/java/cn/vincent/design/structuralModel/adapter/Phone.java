package cn.vincent.design.structuralModel.adapter;

/**
 * 手机实体 - 适配目标
 */
public class Phone {
    public void charging() {
        Recharger recharger = new CNRecharger();
        System.out.println("当前供应电流为：" + recharger.charging() + "V");
    }
}