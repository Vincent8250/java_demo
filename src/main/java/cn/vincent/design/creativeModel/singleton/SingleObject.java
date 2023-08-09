package cn.vincent.design.creativeModel.singleton;

public class SingleObject {
    private static SingleObject singleObject = null;

    private SingleObject() {
    }

    // 加 synchronized 保证线程安全
    public static synchronized SingleObject getSingleObject() {
        if (singleObject == null)
            singleObject = new SingleObject();
        return singleObject;
    }

    public String hello() {
        return "hello!";
    }
}
