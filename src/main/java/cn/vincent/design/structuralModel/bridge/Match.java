package cn.vincent.design.structuralModel.bridge;

import cn.vincent.design.structuralModel.bridge.service.Jacket;
import cn.vincent.design.structuralModel.bridge.service.Trousers;

/**
 * 抽象接口
 */
public abstract class Match {

    Jacket jacket;

    Trousers trousers;

    public Match(Jacket jacket, Trousers trousers){
        this.jacket = jacket;
        this.trousers = trousers;
    }
}
