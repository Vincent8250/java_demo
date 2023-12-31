package cn.vincent.controller;

import cn.vincent.server.ThreadServer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    //region 线程创建
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    ThreadServer threadServer;

    /**
     * 继承 Thread 实现子线程任务
     */
    private class Thread02 extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                Thread.sleep(1000);
                System.out.println("线程02 ----- " + simpleDateFormat.format(new Date()));
            }
        }
    }

    /**
     * 不推荐使用 避免OOP单继承局限性
     *
     * @return
     */
    @GetMapping("/newThread")
    public String Thread() {
        Thread02 thread = new Thread02();
        thread.start();
        return Thread.currentThread().getName();
    }

    /**
     * 实现Runnable接口 推荐使用
     *
     * @return
     */
    @GetMapping("/runnable")
    public String runnable() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("线程 ----- " + simpleDateFormat.format(new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "vincent";
    }

    /**
     * 实现Callable接口 带返回值的Runnable
     *
     * @return
     */
    @SneakyThrows
    @GetMapping("/callable")
    public String callable() {
        //callable接口要搭配futuretask使用
        //通过FutureTask启动线程、获取返回结果
        Callable<String> callable = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.println("线程 ----- " + simpleDateFormat.format(new Date()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        return futureTask.get();
    }

    /**
     * spring异步注释 @Async
     *
     * @return
     */
    @GetMapping("/springThread")
    public String springThread() {
        threadServer.springAsyncThread();
        return "springThread";
    }

    /**
     * 线程池创建线程
     *
     * @return
     */
    @SneakyThrows
    @GetMapping("/cachedThreadPool")
    public String cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println("子线程启动______" + 1);
            }
            return "子线程结束";
        });
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("主线程启动______" + 1);
        }
        System.out.println(submit.get());
        return "cachedThreadPool";
    }

    /**
     * 线程池创建线程
     *
     * @return
     */
    @SneakyThrows
    @GetMapping("/fixedThreadPool")
    public String fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Future<String> submit = executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println("子线程启动______" + 1);
            }
            return "子线程结束";
        });
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("主线程启动______" + 1);
        }
        System.out.println(submit.get());
        return "fixedThreadPool";
    }

    @SneakyThrows
    @GetMapping("/countDownLatch")
    public void countDownLatch() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        int i = 1;
        while (i <= 5) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    System.out.println("线程" + finalI + "开始");
                    Thread.sleep(5000);
                    System.out.println("线程" + finalI + "结束");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            i++;
        }
        countDownLatch.await();
        System.out.println("结束");
    }

    //endregion
}