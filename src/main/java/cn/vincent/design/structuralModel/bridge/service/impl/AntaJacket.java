package cn.vincent.design.structuralModel.bridge.service.impl;

import cn.vincent.design.structuralModel.bridge.service.Jacket;

public class AntaJacket implements Jacket {
    @Override
    public void jacketColor() {
        System.out.println("anta上衣 黑色");
    }
}
