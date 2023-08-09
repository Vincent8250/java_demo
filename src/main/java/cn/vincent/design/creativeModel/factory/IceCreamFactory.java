package cn.vincent.design.creativeModel.factory;

import cn.vincent.design.creativeModel.factory.product.IceCream;
import cn.vincent.design.creativeModel.factory.product.impl.Chocoz;
import cn.vincent.design.creativeModel.factory.product.impl.Pudding;

public class IceCreamFactory {

    private IceCream ice;

    public IceCreamFactory(String name) {
        switch (name) {
            case "小布丁":
                ice = new Pudding();
                break;
            case "巧乐兹":
                ice = new Chocoz();
                break;
            default:
                ice = null;
                break;
        }
    }

    public IceCream getIce() {
        if (ice == null) {
            ice = new Pudding();
        }
        return ice;
    }
}
