package cn.vincent.design.behaviorModel.template;

public class UserImport extends Import {
    @Override
    boolean verify() {
        return true;
    }

    @Override
    void importExcel() {
        System.out.println("导入用户信息");
    }
}
