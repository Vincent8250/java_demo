package cn.vincent.design.structuralModel.decorator.product.decorator;

import cn.vincent.design.structuralModel.decorator.product.Car;

/**
 * 抽象装饰类
 */
public abstract class CarDecorator {

    public Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    public abstract String getCarName();

    public abstract String getCarNum();
}