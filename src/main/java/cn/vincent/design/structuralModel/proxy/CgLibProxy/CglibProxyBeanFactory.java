package cn.vincent.design.structuralModel.proxy.CgLibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 代理工厂 - 动态生成代理
 */
public class CglibProxyBeanFactory {
    public static Object getProxyBean(CglibProxyRent rent){
        CglibMyAspect myAspect = new CglibMyAspect();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rent.getClass());
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            myAspect.before();
            Object obj = method.invoke(rent,objects);
            myAspect.after();
            return obj;
        });
        return enhancer.create();
    }
}
