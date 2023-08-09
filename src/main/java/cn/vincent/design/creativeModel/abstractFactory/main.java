package cn.vincent.design.creativeModel.abstractFactory;

import cn.vincent.design.creativeModel.abstractFactory.factory.PurchaseFactory;
import cn.vincent.design.creativeModel.abstractFactory.factory.impl.ChocozFactory;
import cn.vincent.design.creativeModel.abstractFactory.factory.impl.PuddingFactory;

/**
 * 创建型 - 抽象工厂模式
 */
public class main {

    /*
     * 总结：
     * 抽象工厂 将生产和采购解耦 在更换产品线的时候实现了一键更换
     * 这种模式适用于项目中依赖的第三方
     * 在项目中可能替换的第三方 可以抽象出一层抽象工厂 后期替换时直接实现抽象工厂就可以了
     * */

    public static void main(String[] args) {
        System.out.println("---------雪糕工厂 准备生产（小布丁） 开始采购---------");
        production(new PuddingFactory());
        System.out.println("---------小布丁生产结束---------");

        System.out.println("---------雪糕工厂 准备生产（巧乐兹） 开始采购---------");
        production(new ChocozFactory());
        System.out.println("---------巧乐兹生产结束---------");
    }

    public static void production(PurchaseFactory purchase) {
        System.out.println("获取到" + purchase.getMaterials().getMaterials() + "开始生产");
        System.out.println("获取到" + purchase.getPackage().getPackages() + "开始包装");
    }
}
