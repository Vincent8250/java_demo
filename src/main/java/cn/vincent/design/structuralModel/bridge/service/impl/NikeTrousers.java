package cn.vincent.design.structuralModel.bridge.service.impl;

import cn.vincent.design.structuralModel.bridge.service.Trousers;

public class NikeTrousers implements Trousers {
    @Override
    public void trousersColor() {
        System.out.println("nike裤子 蓝色");
    }
}
