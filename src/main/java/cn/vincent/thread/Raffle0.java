package cn.vincent.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// 抽奖 0
public class Raffle0 {

    public static List<Integer> jackpot = new ArrayList<Integer>() {
        {
            add(10);
            add(5);
            add(100);
            add(20);
            add(300);
            add(50);
            add(200);
            add(150);
            add(500);
            add(1000);
            add(180);
            add(60);
        }
    };
    public static Object lock = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                synchronized (lock) {
                    if (jackpot.size() > 0) {
                        Integer index = new Random().nextInt(jackpot.size());
                        System.out.println(Thread.currentThread().getName() + "抽到了" + jackpot.get(index));
                        jackpot.remove(jackpot.get(index));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        };

        Thread box_1 = new Thread(task);
        box_1.setName("抽奖箱一");
        Thread box_2 = new Thread(task);
        box_2.setName("抽奖箱二");

        box_1.start();
        box_2.start();

    }
}
