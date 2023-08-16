package cn.vincent.design.behaviorModel.observer.service.impl;

import cn.vincent.design.behaviorModel.observer.entity.EventEntity;
import cn.vincent.design.behaviorModel.observer.listener.MyListener;
import cn.vincent.design.behaviorModel.observer.service.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 阻塞式的观察者
 */
public class DefaultObserver implements Observer {
    List<MyListener> listeners = new ArrayList();

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
        for (MyListener listener : listeners) {
            listener.onEvent(msg);
        }
    }
}
