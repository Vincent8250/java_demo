package cn.vincent.design.structuralModel.adapter;

/**
 * 适配器对象 - 充电器实体
 */
public class CNRecharger implements Recharger {

    private PowerSource powerSource;

    @Override
    public int charging() {
        powerSource = new PowerSource();
        return this.powerSource.getPowerSource() / 44;
    }
}
