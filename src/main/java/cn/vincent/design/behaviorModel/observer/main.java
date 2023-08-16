package cn.vincent.design.behaviorModel.observer;

import cn.vincent.design.behaviorModel.observer.entity.AdminEvent;
import cn.vincent.design.behaviorModel.observer.entity.UserEvent;
import cn.vincent.design.behaviorModel.observer.listener.impl.AdminListener;
import cn.vincent.design.behaviorModel.observer.listener.impl.UserListener;
import cn.vincent.design.behaviorModel.observer.service.impl.DefaultObserver;
import cn.vincent.design.behaviorModel.observer.service.impl.QueueObserver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 行为型 - 观察者模式
 */
public class main {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            while (true) {
                System.out.println(simpleDateFormat.format(new Date()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        //region 阻塞式的发布订阅
        DefaultObserver defaultObserver = new DefaultObserver();
        defaultObserver.registerListener(new UserListener());
        defaultObserver.registerListener(new AdminListener());

        System.out.println("--------------用户事件触发--------------");
        UserEvent userEvent = new UserEvent();
        defaultObserver.notifyListener(userEvent);

        System.out.println("--------------管理员事件触发--------------");
        AdminEvent adminEvent = new AdminEvent();
        defaultObserver.notifyListener(adminEvent);
        //endregion

        //region 非阻塞式的发布订阅
        QueueObserver queueObserver = new QueueObserver();
        queueObserver.registerListener(new UserListener());
        queueObserver.registerListener(new AdminListener());

        System.out.println("--------------用户事件触发--------------");
        queueObserver.notifyListener(userEvent);

        System.out.println("--------------管理员事件触发--------------");
        queueObserver.notifyListener(adminEvent);
        //endregion
    }
}
