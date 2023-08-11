package cn.vincent.design.structuralModel.decorator.product.decorator.impl;

import cn.vincent.design.structuralModel.decorator.product.Car;
import cn.vincent.design.structuralModel.decorator.product.decorator.CarDecorator;

/**
 * 具体装饰类
 */
public class CarDecoratorImpl extends CarDecorator {

    public CarDecoratorImpl(Car car) {
        super(car);
    }

    @Override
    public String getCarName() {
        return "这是一台：" + car.getCarName();
    }

    @Override
    public String getCarNum() {
        return "这台车的号码是：" + car.getCarNum();
    }
}
