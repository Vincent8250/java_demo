package cn.vincent.design.behaviorModel.observer.listener.impl;

import cn.vincent.design.behaviorModel.observer.entity.AdminEvent;
import cn.vincent.design.behaviorModel.observer.entity.EventEntity;
import cn.vincent.design.behaviorModel.observer.listener.MyListener;

public class AdminListener implements MyListener {
    @Override
    public void onEvent(EventEntity msg) {
        if (msg instanceof AdminEvent)
            System.out.println("管理员上线");
    }
}
