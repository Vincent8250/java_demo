package cn.vincent.thread;

public class Desk {

    // 桌子上的状态 0：没有食物 1：有食物
    public static int flag = 0;

    // 最大数量
    public static int maxCount = 10;

    // 锁对象
    public static Object lock = new Object();
}
