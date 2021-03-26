package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechatOrder.dao.RepairMalfunctionItemMapper;
import wechatOrder.dao.RepairMalfunctionMapper;
import wechatOrder.po.RepairMalfunctionItem;
import wechatOrder.po.vo.RepairMalfunctionAndItemsVO;
import wechatOrder.service.RepairMalfunctionService;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 18:48
 */
@Service
public class RepairMalfunctionServiceImpl implements RepairMalfunctionService {

    @Autowired
    private RepairMalfunctionMapper repairMalfunctionMapper;
    @Autowired
    private RepairMalfunctionItemMapper repairMalfunctionItemMapper;

    @Override
    public void saveMalfunctionWithItems(RepairMalfunctionAndItemsVO repairMalfunctionAndItemsVO) {
        //存malfunction
        repairMalfunctionMapper.saveMalfunction(repairMalfunctionAndItemsVO);
        //存malfunctionItem
        List<RepairMalfunctionItem> repairMalfunctionItems = repairMalfunctionAndItemsVO.getRepairMalfunctionItems();
        for (RepairMalfunctionItem repairMalfunctionItem : repairMalfunctionItems) {
            repairMalfunctionItem.setMalfunctionId(repairMalfunctionAndItemsVO.getId());
            repairMalfunctionItemMapper.insertSelective(repairMalfunctionItem);
        }
    }
}
