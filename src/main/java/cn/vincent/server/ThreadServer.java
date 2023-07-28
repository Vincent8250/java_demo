package cn.vincent.server;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ThreadServer {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * spring异步注释 @Async
     */
    @Async
    public void springAsyncThread(){
        //@Async注释方法和调用者不能在同一个类中
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("线程 ----- " + simpleDateFormat.format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
