package cn.vincent.design.structuralModel.proxy.JdkProxy;

import java.lang.reflect.Proxy;

/**
 * 代理工厂 - 动态生成代理
 */
public class JdkProxyFactory {

    //动态生成代理对象
    public static Object getProxyBean(Object target){
        Class clazz = target.getClass();
        MyAspect myAspect = new MyAspect();
        //在JDK中动态生成代理对象的方法
        //动态生成代理对象的方法
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), (proxy, method, args) -> {
            myAspect.before();
            Object obj =  method.invoke(target,args);
            myAspect.after();
            return obj;
        });
    }
}
