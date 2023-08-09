package cn.vincent.design.creativeModel.builder;

public class CarBuilder {

    public static Car builder(String brand) {
        Car car = new Car();
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
                car.setCarType("");
                car.setActuate(0);
                break;
        }
        return car;
    }
}
