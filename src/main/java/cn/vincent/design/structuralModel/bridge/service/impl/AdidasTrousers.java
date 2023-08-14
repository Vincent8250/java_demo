package cn.vincent.design.structuralModel.bridge.service.impl;

import cn.vincent.design.structuralModel.bridge.service.Trousers;

public class AdidasTrousers implements Trousers {
    @Override
    public void trousersColor() {
        System.out.println("adidas裤子 绿色");
    }
}
