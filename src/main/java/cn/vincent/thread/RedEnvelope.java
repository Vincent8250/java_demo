package cn.vincent.thread;

import java.util.Random;

// 抢红包
public class RedEnvelope {

    // 总金额
    public static int money = 100;

    // 抢夺次数
    public static int count = 3;

    public static Object lock = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            synchronized (lock) {
                if (count > 1) {
                    int money_snatch = new Random().nextInt(((money / count) + 1));
                    money = money - money_snatch;
                    System.out.println(Thread.currentThread().getName() + "抢到了" + money_snatch + "元");
                } else if (count == 1) {
                    System.out.println(Thread.currentThread().getName() + "抢到了" + money + "元");
                } else {
                    System.out.println(Thread.currentThread().getName() + "没抢到");
                }
                count--;
            }
        };

        Thread thread_1 = new Thread(task);
        thread_1.setName("1");
        Thread thread_2 = new Thread(task);
        thread_2.setName("2");
        Thread thread_3 = new Thread(task);
        thread_3.setName("3");
        Thread thread_4 = new Thread(task);
        thread_4.setName("4");
        Thread thread_5 = new Thread(task);
        thread_5.setName("5");

        thread_1.start();
        thread_2.start();
        thread_3.start();
        thread_4.start();
        thread_5.start();
    }
}
