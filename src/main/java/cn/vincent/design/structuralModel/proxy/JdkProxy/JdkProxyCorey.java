package cn.vincent.design.structuralModel.proxy.JdkProxy;

/**
 * 委托对象
 */
public class JdkProxyCorey implements JdkProxyRent {
    @Override
    public void renting() {
        System.out.println("Corey 有房出租！");
    }
}
