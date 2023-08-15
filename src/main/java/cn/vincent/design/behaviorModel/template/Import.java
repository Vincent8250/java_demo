package cn.vincent.design.behaviorModel.template;

/**
 * 抽象模板类 规范导入功能的规则
 */
public abstract class Import {

    abstract boolean verify();

    abstract void importExcel();

    public void excel() {
        // 校验
        boolean verify = verify();
        // 根据校验结果 判断是否导入
        if (verify)
            importExcel();
    }
}
