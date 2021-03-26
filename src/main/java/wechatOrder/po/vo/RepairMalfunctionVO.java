package wechatOrder.po.vo;

import wechatOrder.po.RepairMalfunction;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 18:07
 */
public class RepairMalfunctionVO {
    private List<RepairMalfunction> repairMalfunctions;

    @Override
    public String toString() {
        return "RepairMalfunctionVO{" +
                "repairMalfunctions=" + repairMalfunctions +
                '}';
    }

    public List<RepairMalfunction> getRepairMalfunctions() {
        return repairMalfunctions;
    }

    public void setRepairMalfunctions(List<RepairMalfunction> repairMalfunctions) {
        this.repairMalfunctions = repairMalfunctions;
    }
}
