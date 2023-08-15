package cn.vincent.design.behaviorModel.strategy.strategy.impl;

import cn.vincent.design.behaviorModel.strategy.strategy.Strategy;

public class PointsStrategyImpl implements Strategy {
    @Override
    public void algorithm() {
        System.out.println("超市积分算法");
    }
}
