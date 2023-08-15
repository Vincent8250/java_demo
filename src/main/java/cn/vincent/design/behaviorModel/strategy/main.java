package cn.vincent.design.behaviorModel.strategy;

import cn.vincent.design.behaviorModel.strategy.strategy.Favorable;
import cn.vincent.design.behaviorModel.strategy.strategy.impl.DiscountStrategyImpl;
import cn.vincent.design.behaviorModel.strategy.strategy.impl.PointsStrategyImpl;

/**
 * 行为型 - 策略模式
 */
public class main {

    public static void main(String[] args) {
        System.out.println("-------------当前超市施行打折优惠--------------");
        Favorable favorableDiscount = new Favorable(new DiscountStrategyImpl());
        favorableDiscount.currentStrategy();

        System.out.println("-------------当前超市施行积分优惠--------------");
        Favorable favorablePoints = new Favorable(new PointsStrategyImpl());
        favorablePoints.currentStrategy();
    }
}
