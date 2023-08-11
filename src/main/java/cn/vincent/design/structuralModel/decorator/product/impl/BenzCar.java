package cn.vincent.design.structuralModel.decorator.product.impl;

import cn.vincent.design.structuralModel.decorator.product.Car;

public class BenzCar implements Car {
    public String carName;
    public String carNumber;

    public BenzCar(String number) {
        this.carName = "Benz";
        this.carNumber = number;
    }

    @Override
    public void drive() {
        System.out.println("正在行驶的是 BenzCar");
    }

    @Override
    public String getCarName() {
        return this.carName;
    }

    @Override
    public String getCarNum() {
        return this.carNumber;
    }
}
