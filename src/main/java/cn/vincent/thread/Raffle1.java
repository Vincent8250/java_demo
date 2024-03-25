package cn.vincent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 抽奖 1
public class Raffle1 {

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
            ArrayList<Integer> boxSum = new ArrayList<>();

            while (true) {
                synchronized (lock) {
                    if (jackpot.size() > 0) {
                        Integer index = new Random().nextInt(jackpot.size());
                        boxSum.add(jackpot.get(index));
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
            try {
                Thread.sleep(new Random().nextInt(800));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "以共抽到了" + boxSum.size() + "个奖项");
            System.out.print("分别是：");
            boxSum.forEach((i) -> {
                System.out.print(i + "; ");
            });
            System.out.println("总金额是：" + boxSum.stream().mapToInt(Integer::intValue).sum());
        };

        Thread box_1 = new Thread(task);
        box_1.setName("抽奖箱一");
        Thread box_2 = new Thread(task);
        box_2.setName("抽奖箱二");

        box_1.start();
        box_2.start();

    }
}
