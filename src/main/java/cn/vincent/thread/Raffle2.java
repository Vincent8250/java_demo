package cn.vincent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 抽奖 2
public class Raffle2 {

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
            add(1001);
            add(180);
            add(60);
        }
    };
    public static Object lock = new Object();

    public static void main(String[] args) {
        Callable<ArrayList<Integer>> task = () -> {
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
            return boxSum;
        };
        FutureTask<ArrayList<Integer>> future_1 = new FutureTask(task);
        FutureTask<ArrayList<Integer>> future_2 = new FutureTask(task);

        Thread box_1 = new Thread(future_1);
        box_1.setName("抽奖箱一");
        Thread box_2 = new Thread(future_2);
        box_2.setName("抽奖箱二");

        box_1.start();
        box_2.start();

        try {
            ArrayList<Integer> boxSum_1 = future_1.get();
            int box_1max = boxSum_1.stream().mapToInt(Integer::intValue).max().getAsInt();
            ArrayList<Integer> boxSum_2 = future_2.get();
            int box_2max = boxSum_2.stream().mapToInt(Integer::intValue).max().getAsInt();

            System.out.println(
                    box_1max > box_2max ?
                            "最大奖项是：" + box_1max + " 出自抽奖箱一" :
                            "最大奖项是：" + box_2max + " 出自抽奖箱二");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
