package cn.vincent.design.behaviorModel.strategy.strategy;

/**
 * 环境类 - 超市优惠类
 */
public class Favorable {

    private Strategy strategy;

    public Favorable(Strategy strategy) {
        this.strategy = strategy;
    }

    public void currentStrategy(){
        strategy.algorithm();
    }

}
