package wechatOrder.po.vo;

/**
 * @author JJ
 * @date 2020/2/8 - 18:18
 */
public class StaffRole {

    private Integer staffId;

    private Integer roleId;

    public StaffRole(){

    }

    public StaffRole(Integer staffId,Integer roleId){
        this.staffId=staffId;
        this.roleId=roleId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
