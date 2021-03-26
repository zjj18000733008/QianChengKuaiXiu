package wechatOrder.po.vo;

import org.bouncycastle.jce.provider.BrokenJCEBlockCipher;
import wechatOrder.po.RepairBrand;

/**
 * @author JJ
 * @date 2020/2/10 - 16:03
 */
public class RepairBrandsWithApplianceNameVO extends RepairBrand {

    private String applianceName;

    @Override
    public String toString() {
        return "RepairBrandsWithApplianceNameVO{" +
                "applianceName='" + applianceName + '\'' +
                "} " + super.toString();
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }
}
