package cn.vincent.design.structuralModel.bridge;

import cn.vincent.design.structuralModel.bridge.service.impl.AdidasTrousers;
import cn.vincent.design.structuralModel.bridge.service.impl.NikeJacket;

/**
 * 结构型 - 桥接模式
 */
public class main {

    public static void main(String[] args) {
        MatchBridge matchBridge = new MatchBridge(new NikeJacket(), new AdidasTrousers());
        matchBridge.match();
    }
}
