package cn.vincent.thread;

import java.util.function.Consumer;

// 卖电影票
public class MovieTicket {

    public static int total = 10;

    public static Object lock = new Object();

    public static void main(String[] args) {

        Consumer<String> task = (name) -> {
            while (true) {
                synchronized (lock) {
                    if (total > 3) {
                        System.out.println(name + "售票中 售出1张");
                        total--;
                        System.out.println("剩余票数：" + total);
                    } else {
                        System.out.println(name + "售票结束 剩余：" + total + "张不在出售");
                        break;
                    }
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(() -> {
            task.accept("窗口一");
        }).start();

        new Thread(() -> {
            task.accept("窗口二");
        }).start();
    }
}
