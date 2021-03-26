package wechatOrder.service;

import wechatOrder.po.RepairModel;
import wechatOrder.po.vo.RepairModelsVO;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 11:08
 */
public interface RepairModelService {

    void batchSave(RepairModelsVO repairModels) throws Exception;

    void delete(Integer id);

    void update(RepairModel repairModel);

    List<RepairModel> query(Integer brandId);

    RepairModel get(Integer id);


}
