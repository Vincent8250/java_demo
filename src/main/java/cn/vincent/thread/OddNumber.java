package cn.vincent.thread;

public class OddNumber {

    public static int number = 1;

    public static Object lock = new Object();

    public static void main(String[] args) {

        Runnable task = () -> {
            while (true) {
                synchronized (lock) {
                    if (number < 101) {
                        if (isOdd(number)) {
                            System.out.println(Thread.currentThread().getName() + number + "是奇数");
                        }
                        number++;
                    } else {
                        break;
                    }
                }
            }
        };

        Thread thread_1 = new Thread(task);
        thread_1.setName("线程一");
        Thread thread_2 = new Thread(task);
        thread_2.setName("线程二");

        thread_1.start();
        thread_2.start();

    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
