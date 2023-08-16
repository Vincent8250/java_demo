package cn.vincent.design.behaviorModel.observer.service.impl;

import cn.vincent.design.behaviorModel.observer.entity.EventEntity;
import cn.vincent.design.behaviorModel.observer.listener.MyListener;
import cn.vincent.design.behaviorModel.observer.service.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 非阻塞式的观察者
 */
public class QueueObserver implements Observer {
    List<MyListener> listeners = new ArrayList();
    BlockingQueue<EventEntity> eventEntities = new ArrayBlockingQueue<>(10);
    ExecutorService executorService = Executors.newFixedThreadPool(20);

    public QueueObserver() {
        // 观察者初始化时 创建线程循环事件队列
        executorService.submit(() -> {
            while (true) {
                try {
                    // 从事件队列获取事件 没有事件会阻塞住线程
                    EventEntity dataMsg = eventEntities.take();
                    // 拿到线程后 向监听器广播
                    for (MyListener listener : listeners)
                        listener.onEvent(dataMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void registerListener(MyListener myListener) {
        listeners.add(myListener);
    }

    @Override
    public void removeListener(MyListener myListener) {
        listeners.remove(myListener);
    }

    @Override
    public void notifyListener(EventEntity msg) {
        // 投放消息到阻塞队列中
        eventEntities.offer(msg);
    }


}
