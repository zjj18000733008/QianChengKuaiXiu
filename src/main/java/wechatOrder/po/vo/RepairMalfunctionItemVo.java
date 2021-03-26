package wechatOrder.po.vo;

import wechatOrder.po.RepairMalfunctionItem;

import java.util.List;

/**
 * @author JJ
 * @date 2020/2/14 - 3:44
 */
public class RepairMalfunctionItemVo {

    private List<RepairMalfunctionItem> repairMalfunctionItems;

    @Override
    public String toString() {
        return "RepairMalfunctionItemVo{" +
                "repairMalfunctionItems=" + repairMalfunctionItems +
                '}';
    }

    public List<RepairMalfunctionItem> getRepairMalfunctionItems() {
        return repairMalfunctionItems;
    }

    public void setRepairMalfunctionItems(List<RepairMalfunctionItem> repairMalfunctionItems) {
        this.repairMalfunctionItems = repairMalfunctionItems;
    }
}
