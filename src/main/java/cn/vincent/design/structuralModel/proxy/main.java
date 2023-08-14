package cn.vincent.design.structuralModel.proxy;

import cn.vincent.design.structuralModel.proxy.CgLibProxy.CglibProxyBeanFactory;
import cn.vincent.design.structuralModel.proxy.CgLibProxy.CglibProxyCorey;
import cn.vincent.design.structuralModel.proxy.CgLibProxy.CglibProxyRent;
import cn.vincent.design.structuralModel.proxy.JdkProxy.JdkProxyCorey;
import cn.vincent.design.structuralModel.proxy.JdkProxy.JdkProxyFactory;
import cn.vincent.design.structuralModel.proxy.JdkProxy.JdkProxyRent;
import cn.vincent.design.structuralModel.proxy.staticProxy.Corey;
import cn.vincent.design.structuralModel.proxy.staticProxy.Rent;
import cn.vincent.design.structuralModel.proxy.staticProxy.StaticProxyRent;

/**
 * 结构型 - 代理模式
 */
public class main {

    public static void main(String[] args) {
        System.out.println("---------------静态代理---------------");
        Rent rent = new Corey();
        StaticProxyRent staticProxyRent = new StaticProxyRent(rent);
        staticProxyRent.renting();

        System.out.println("---------------JDK-动态代理---------------");
        JdkProxyRent jdkProxyRent = new JdkProxyCorey();
        JdkProxyRent jdkProxyRent1 = (JdkProxyRent) JdkProxyFactory.getProxyBean(jdkProxyRent);
        jdkProxyRent1.renting();

        System.out.println("---------------CGLIB-动态代理---------------");
        CglibProxyRent cglibRent = new CglibProxyCorey();
        CglibProxyRent cglibRent1 = (CglibProxyRent) CglibProxyBeanFactory.getProxyBean(cglibRent);
        cglibRent1.renting();
    }
}
