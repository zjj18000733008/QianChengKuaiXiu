package wechatOrder.po.vo;

import wechatOrder.po.RepairModel;

/**
 * @author JJ
 * @date 2020/2/13 - 17:23
 */
public class RepairModelWithBrandAndApplianceVO extends RepairModel {

    private String brandName;

    private String applianceName;

    @Override
    public String toString() {
        return "RepairModelWithBrandAndApplianceVO{" +
                "brandName='" + brandName + '\'' +
                ", applianceName='" + applianceName + '\'' +
                "} " + super.toString();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }
}
