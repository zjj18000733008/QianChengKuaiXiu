package wechatOrder.po.vo;

import wechatOrder.po.RepairBrand;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 16:37
 */
public class RepairBrandsVO {
    private List<RepairBrand> repairBrands;

    @Override
    public String toString() {
        return "RepairBrandsVO{" +
                "repairBrands=" + repairBrands +
                '}';
    }

    public List<RepairBrand> getRepairBrands() {
        return repairBrands;
    }

    public void setRepairBrands(List<RepairBrand> repairBrands) {
        this.repairBrands = repairBrands;
    }
}
