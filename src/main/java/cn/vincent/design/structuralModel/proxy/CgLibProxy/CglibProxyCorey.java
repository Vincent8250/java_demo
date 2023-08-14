package cn.vincent.design.structuralModel.proxy.CgLibProxy;

/**
 * 委托对象
 */
public class CglibProxyCorey implements CglibProxyRent {
    @Override
    public void renting() {
        System.out.println("Corey 有房出租");
    }
}
