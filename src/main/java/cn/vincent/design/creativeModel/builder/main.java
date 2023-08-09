package cn.vincent.design.creativeModel.builder;

/**
 * 创建型 - 建造者模式
 */
public class main {
    public static void main(String[] args) {
        System.out.println("____________🚲____________");
        Car car_1 = new CarBuilderImpl().builder("永久牌");
        show(car_1);

        System.out.println("____________🚗____________");
        Car car_2 = new CarBuilderImpl().builder("奥迪牌");
        show(car_2);

        System.out.println("____________坦克____________");
        CarBuilderImpl carBuilder = new CarBuilderImpl();
        carBuilder.carType("解放牌");
        carBuilder.actuate(8);
        Car car_3 = carBuilder.builder();
        show(car_3);
    }

    private static void show(Car car) {
        System.out.println("这是：" + car.getCarType() + " 有" + car.getActuate() + "个轮子");
    }

}
