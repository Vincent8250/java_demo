package cn.vincent.design.behaviorModel.template;

public class RoleImport extends Import {
    @Override
    boolean verify() {
        return true;
    }

    @Override
    void importExcel() {
        System.out.println("导入角色信息");
    }
}
