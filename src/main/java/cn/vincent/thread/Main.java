package cn.vincent.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        //region 生产者 消费者
        Thread coke = new Thread(() -> {
            while (true) {
                synchronized (Desk.lock) {
                    if (Desk.maxCount > 0) {
                        if (Desk.flag == 0) {
                            Desk.flag = 1;
                            System.out.println("正在做");
                            Desk.lock.notifyAll();
                        } else if (Desk.flag == 1) {
                            try {
                                Desk.lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        });

        Thread foodie = new Thread(() -> {
            while (true) {
                synchronized (Desk.lock) {
                    if (Desk.maxCount > 0) {
                        if (Desk.flag == 0) {
                            try {
                                Desk.lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (Desk.flag == 1) {
                            Desk.maxCount--;
                            Desk.flag = 0;
                            System.out.println("正在吃 剩下能吃：" + Desk.maxCount);
                            Desk.lock.notifyAll();
                        }
                    } else {
                        break;
                    }
                }
            }
        });

        coke.start();
        foodie.start();
        //endregion

        System.out.println("-----------------------------------------------------------------");

        ArrayBlockingQueue<Consumer<String>> r = new ArrayBlockingQueue<>(10);

        Thread coke_1 = new Thread(() -> {
            int i = 0;
            while (i < 20) {
                i++;
                try {
                    int finalI = i;
                    r.put((s) -> {
                        System.out.println("正在做" + s);
                        System.out.println("这是第" + finalI + "份");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread foodie_1 = new Thread(() -> {
            while (true) {
                if (r.size() > 0) {
                    try {
                        Consumer<String> take = r.take();
                        take.accept("面");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        coke_1.start();
        foodie_1.start();

    }
}
