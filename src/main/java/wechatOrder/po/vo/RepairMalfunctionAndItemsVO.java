package wechatOrder.po.vo;

import wechatOrder.po.RepairMalfunction;
import wechatOrder.po.RepairMalfunctionItem;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 18:51
 */
public class RepairMalfunctionAndItemsVO extends RepairMalfunction {

    List<RepairMalfunctionItem> repairMalfunctionItems;

    @Override
    public String toString() {
        return "RepairMalfunctionAndItemsVO{" +
                "repairMalfunctionItems=" + repairMalfunctionItems +
                "} " + super.toString();
    }

    public List<RepairMalfunctionItem> getRepairMalfunctionItems() {
        return repairMalfunctionItems;
    }

    public void setRepairMalfunctionItems(List<RepairMalfunctionItem> repairMalfunctionItems) {
        this.repairMalfunctionItems = repairMalfunctionItems;
    }
}
