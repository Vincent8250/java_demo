package cn.vincent.design.creativeModel.builder;

public class CarBuilderImpl implements CarBuilder {
    // Car 属性默认值
    private String CAR_TYPE = "人力车";
    private int ACTUATE = 2;

    private Car car = new Car();
    private String carType = CAR_TYPE;
    private int actuate = ACTUATE;

    public CarBuilderImpl carType(String carType) {
        this.carType = carType;
        return this;
    }

    public CarBuilderImpl actuate(int actuate) {
        this.actuate = actuate;
        return this;
    }

    public Car builder() {
        car.setCarType(carType);
        car.setActuate(actuate);
        return car;
    }

    public Car builder(String brand) {
        switch (brand) {
            case "永久牌":
                car.setCarType("人力车");
                car.setActuate(2);
                break;
            case "奥迪牌":
                car.setCarType("汽车");
                car.setActuate(4);
                break;
            default:
                car.setCarType(CAR_TYPE);
                car.setActuate(ACTUATE);
                break;
        }
        return car;
    }
}
