package cn.vincent.design.behaviorModel.observer.entity;

public class AdminEvent implements EventEntity {
    @Override
    public String getMsg() {
        return "管理员事件";
    }
}
