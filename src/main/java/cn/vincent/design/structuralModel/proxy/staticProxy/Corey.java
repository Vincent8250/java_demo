package cn.vincent.design.structuralModel.proxy.staticProxy;

/**
 * 委托对象
 */
public class Corey implements Rent{

    @Override
    public void renting() {
        System.out.println("Corey有房出租");
    }
}
