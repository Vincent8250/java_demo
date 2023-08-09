package cn.vincent.design.creativeModel.factory;

import cn.vincent.design.creativeModel.factory.product.IceCream;

/**
 * 创建型 - 工厂模式
 */
public class main {

    public static void main(String[] args) {
        System.out.println("--------雪糕生产--------");
        IceCream ice = new IceCreamFactory("小布丁").getIce();
        System.out.println("当前在生产的雪糕产品为：" + ice.getName());
        System.out.println("当前在生产的雪糕口味为：" + ice.taste());

        System.out.println("--------更换产品--------");
        IceCream ice_1 = new IceCreamFactory("巧乐兹").getIce();
        System.out.println("当前在生产的雪糕产品为：" + ice_1.getName());
        System.out.println("当前在生产的雪糕口味为：" + ice_1.taste());
    }
}
