package cn.vincent.design.structuralModel.bridge.service.impl;

import cn.vincent.design.structuralModel.bridge.service.Jacket;

public class NikeJacket implements Jacket {
    @Override
    public void jacketColor() {
        System.out.println("nike上衣 白色");
    }
}
