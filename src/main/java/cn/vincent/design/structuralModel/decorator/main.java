package cn.vincent.design.structuralModel.decorator;

import cn.vincent.design.structuralModel.decorator.product.decorator.impl.CarDecoratorImpl;
import cn.vincent.design.structuralModel.decorator.product.impl.AudiCar;
import cn.vincent.design.structuralModel.decorator.product.impl.BenzCar;

/**
 * 结构型 - 装饰器模式
 */
public class main {

    public static void main(String[] args) {
        AudiCar audiCar = new AudiCar("沪A 123456");
        CarDecoratorImpl audiCarDecorator = new CarDecoratorImpl(audiCar);
        audiCar.drive();
        System.out.println(audiCarDecorator.getCarName());
        System.out.println(audiCarDecorator.getCarNum());

        System.out.println("-------------------------换车了-------------------------");

        BenzCar benzCar = new BenzCar("鄂A 123456");
        CarDecoratorImpl benzCarDecorator = new CarDecoratorImpl(benzCar);
        benzCar.drive();
        System.out.println(benzCarDecorator.getCarName());
        System.out.println(benzCarDecorator.getCarNum());

    }
}
