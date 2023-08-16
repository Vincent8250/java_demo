package cn.vincent.design.behaviorModel.observer.listener;

import cn.vincent.design.behaviorModel.observer.entity.EventEntity;

/**
 * 监听器接口
 */
public interface MyListener {

    /**
     * 事件
     */
    public void onEvent(EventEntity msg);

}
