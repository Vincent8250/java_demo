package cn.vincent.design.behaviorModel.observer.service;

import cn.vincent.design.behaviorModel.observer.entity.EventEntity;
import cn.vincent.design.behaviorModel.observer.listener.MyListener;

/**
 * 观察者接口
 * @param <T>
 */
public interface Observer<T extends MyListener> {
    //注册监听者
    public void registerListener(T t);

    //移除监听者
    public void removeListener(T t);

    //通知监听者
    public void notifyListener(EventEntity msg);
}
