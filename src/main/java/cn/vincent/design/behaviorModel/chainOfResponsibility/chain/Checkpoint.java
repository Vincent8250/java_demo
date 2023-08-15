package cn.vincent.design.behaviorModel.chainOfResponsibility.chain;

/**
 * 抽象记录层 - 抽象关卡类
 */
public abstract class Checkpoint {

    protected Checkpoint next;

    public void setNext(Checkpoint checkpoint) {
        this.next = checkpoint;
    }

    public abstract void handler(int hp);
}
