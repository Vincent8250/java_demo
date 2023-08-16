package cn.vincent.design.behaviorModel.observer.entity;

public class UserEvent implements EventEntity {
    @Override
    public String getMsg() {
        return "用户事件";
    }
}
