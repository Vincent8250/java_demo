package cn.vincent.design.behaviorModel.chainOfResponsibility.chain;

import cn.hutool.core.util.ObjectUtil;

import java.util.Random;

public class First extends Checkpoint {

    @Override
    public void handler(int hp) {
        System.out.println("——————————————————进入第一关——————————————————");
        int i = new Random().nextInt(20);
        hp = (hp - i) > 0 ? hp - i : 0;
        System.out.println("怪物出现 =》造成 " + i + "点伤害 剩余hp =》" + hp);
        if (hp > 0 && ObjectUtil.isNotEmpty(this.next)) {
            this.next.handler(hp);
        } else
            System.out.println("结束挑战 剩余hp =》" + hp);
    }
}
