package cn.vincent.design.creativeModel.singleton;

/**
 * 创建型 - 单例模式
 */
public class main {

    public static void main(String[] args) {
        SingleObject singleObject = SingleObject.getSingleObject();
        System.out.println(singleObject.hello());
    }
}
