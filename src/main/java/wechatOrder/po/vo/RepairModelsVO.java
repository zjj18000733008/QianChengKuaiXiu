package wechatOrder.po.vo;

import wechatOrder.po.RepairModel;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 17:07
 */
public class RepairModelsVO {
    private List<RepairModel> repairModels;

    @Override
    public String toString() {
        return "RepairModelsVO{" +
                "repairModels=" + repairModels +
                '}';
    }

    public List<RepairModel> getRepairModels() {
        return repairModels;
    }

    public void setRepairModels(List<RepairModel> repairModels) {
        this.repairModels = repairModels;
    }
}
