package cn.vincent.design.structuralModel.proxy.JdkProxy;

/**
 * 切面对象
 */
public class MyAspect {
    public void before(){
        System.out.println("带领房客看房...签租房协议");
    }
    public void after(){
        System.out.println("售后服务");
    }
}
