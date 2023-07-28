package cn.vincent.server;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.lang.model.element.VariableElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class LockServer {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    //region 悲观锁

    //region synchronized

    /**
     * 加锁整个方法
     *
     * @param methodName
     */
    @SneakyThrows
    public synchronized void synchronizedMethodLock(String methodName) {
        System.out.println("目前锁的持有者为：" + methodName + " 当前线程名为：" + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
        System.out.println("锁的持有者为：" + methodName + "持有即将结束");
    }

    /**
     * 加锁代码块
     *
     * @param methodName
     */
    @SneakyThrows
    public void synchronizedClssLock(String methodName) {
        synchronized (this) {
            System.out.println("目前锁的持有者为：" + methodName + " 当前线程名为：" + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
            System.out.println("锁的持有者为：" + methodName + "持有即将结束");
        }
    }

    private Object o = new Object();

    /**
     * 加锁代码块
     *
     * @param methodName
     */
    @SneakyThrows
    public void synchronizedObjLock(String methodName) {
        synchronized (o) {
            System.out.println("目前锁的持有者为：" + methodName + " 当前线程名为：" + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
            System.out.println("锁的持有者为：" + methodName + "持有即将结束");
        }
    }
    //endregion

    //region lock
    Lock l = new ReentrantLock();

    /**
     * ReentrantLock
     *
     * @param methodName
     */
    public void getReentrantLock(String methodName) {
        System.out.println("方法：" + methodName + " 开始获取锁 时间为：" + format.format(new Date()));
        try {
            boolean b = l.tryLock(3, TimeUnit.SECONDS);
            if (!b) {
                System.out.println("方法：" + methodName + " 未获取到锁 时间为：" + format.format(new Date()));
                return;
            }
            System.out.println("目前锁的持有者为：" + methodName + " 当前线程名为：" + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            System.out.println("方法：" + methodName + " 未获取到锁 时间为：" + format.format(new Date()));
        } finally {
            //始终释放锁
            System.out.println("锁的持有者为：" + methodName + "持有即将结束");
            l.unlock();
        }
    }
    //endregion

    //endregion

    //region 乐观锁

    AtomicInteger atomicInt = new AtomicInteger(0);

    @SneakyThrows
    public void getAtomic(String methodName) {
        System.out.println(methodName + "开始操作");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            atomicInt.incrementAndGet();
            System.out.println(methodName + "：" + atomicInt.get());
        }
    }
    //endregion

}
