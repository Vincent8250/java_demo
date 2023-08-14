package cn.vincent.design.structuralModel.bridge;

import cn.vincent.design.structuralModel.bridge.service.Jacket;
import cn.vincent.design.structuralModel.bridge.service.Trousers;

/**
 * 抽象扩展接口 - 穿搭桥接
 */
public class MatchBridge extends Match {

    public MatchBridge(Jacket jacket, Trousers trousers) {
        super(jacket, trousers);
    }

    public void match() {
        System.out.println("当前穿搭上衣为");
        super.jacket.jacketColor();
        System.out.println("当前穿搭裤子为");
        super.trousers.trousersColor();
    }
}
