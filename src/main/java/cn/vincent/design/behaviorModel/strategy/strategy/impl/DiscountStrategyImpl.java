package cn.vincent.design.behaviorModel.strategy.strategy.impl;

import cn.vincent.design.behaviorModel.strategy.strategy.Strategy;

public class DiscountStrategyImpl implements Strategy {
    @Override
    public void algorithm() {
        System.out.println("超市打折算法");
    }
}
