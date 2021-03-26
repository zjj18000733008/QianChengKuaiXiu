package wechatOrder.service;

import wechatOrder.po.RepairMalfunctionItem;
import wechatOrder.po.vo.RepairMalfunctionAndItemsVO;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 12:31
 */
public interface RepairMalfunctionItemService {

    void save(RepairMalfunctionItem repairMalfunctionItem);

    void delete(Integer id);

    void update(RepairMalfunctionItem repairMalfunctionItem);

    List<RepairMalfunctionItem> query(RepairMalfunctionItem repairMalfunctionItem);

    RepairMalfunctionItem get(Integer id);

    List<RepairMalfunctionAndItemsVO> queryMalfunctionsAndItemsByModelId(Integer modelId);

    List<RepairMalfunctionItem> queryAllMalfunctionItemByMalfunctionId(Integer malfunctionId);
}
