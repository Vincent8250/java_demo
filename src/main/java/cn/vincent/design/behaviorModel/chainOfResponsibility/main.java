package cn.vincent.design.behaviorModel.chainOfResponsibility;

import cn.vincent.design.behaviorModel.chainOfResponsibility.chain.First;
import cn.vincent.design.behaviorModel.chainOfResponsibility.chain.Four;
import cn.vincent.design.behaviorModel.chainOfResponsibility.chain.Second;
import cn.vincent.design.behaviorModel.chainOfResponsibility.chain.Third;

/**
 * 行为型 - 责任链模式
 */
public class main {
    public static void main(String[] args) {
        First first = new First();
        Second second = new Second();
        Third third = new Third();
        Four four = new Four();
        first.setNext(second);
        second.setNext(third);
        third.setNext(four);

        first.handler(40);
    }
}