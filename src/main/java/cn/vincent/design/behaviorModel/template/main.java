package cn.vincent.design.behaviorModel.template;

/**
 * 行为型 - 模板模式
 */
public class main {
    public static void main(String[] args) {
        Import userImport = new UserImport();
        userImport.excel();
        RoleImport roleImport = new RoleImport();
        roleImport.excel();
    }
}
