package cn.vincent.design.behaviorModel.observer.listener.impl;

import cn.vincent.design.behaviorModel.observer.entity.EventEntity;
import cn.vincent.design.behaviorModel.observer.entity.UserEvent;
import cn.vincent.design.behaviorModel.observer.listener.MyListener;
import lombok.SneakyThrows;

public class UserListener implements MyListener {
    @SneakyThrows
    @Override
    public void onEvent(EventEntity msg) {
        if (msg instanceof UserEvent) {
            System.out.println("用户上线");
            Thread.sleep(3000);
        }
    }
}
