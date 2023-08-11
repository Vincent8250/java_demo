package cn.vincent.design.structuralModel.decorator.product.impl;

import cn.vincent.design.structuralModel.decorator.product.Car;
import org.springframework.core.ReactiveTypeDescriptor;

public class AudiCar implements Car {
    public String carName;
    public String carNumber;

    public AudiCar(String number) {
        this.carName = "Audi";
        this.carNumber = number;
    }

    @Override
    public void drive() {
        System.out.println("正在行驶的是 AudiCar");
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
