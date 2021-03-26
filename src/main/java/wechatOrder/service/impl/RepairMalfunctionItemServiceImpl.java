package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechatOrder.controller.RepairMalfunctionItemController;
import wechatOrder.dao.RepairMalfunctionItemMapper;
import wechatOrder.dao.RepairMalfunctionMapper;
import wechatOrder.po.RepairMalfunction;
import wechatOrder.po.RepairMalfunctionItem;
import wechatOrder.po.RepairMalfunctionItemExample;
import wechatOrder.po.vo.RepairMalfunctionAndItemsVO;
import wechatOrder.service.RepairMalfunctionItemService;

import javax.security.auth.login.CredentialException;
import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 12:32
 */
@Service
public class RepairMalfunctionItemServiceImpl implements RepairMalfunctionItemService {

    @Autowired
    private RepairMalfunctionItemMapper repairMalfunctionItemMapper;
    @Autowired
    private RepairMalfunctionMapper repairMalfunctionMapper;

    /**
     * 要保证传进来的参数除了id之外都不为空
     * @param repairMalfunctionItem
     */
    @Override
    public void save(RepairMalfunctionItem repairMalfunctionItem) {
        repairMalfunctionItemMapper.insertSelective(repairMalfunctionItem);
    }

    @Override
    public void delete(Integer id) {
        repairMalfunctionItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 要保证id不为空
     * @param repairMalfunctionItem
     */
    @Override
    public void update(RepairMalfunctionItem repairMalfunctionItem) {
        repairMalfunctionItemMapper.updateByPrimaryKeySelective(repairMalfunctionItem);
    }

    @Override
    public List<RepairMalfunctionItem> query(RepairMalfunctionItem repairMalfunctionItem) {
        RepairMalfunctionItemExample example = new RepairMalfunctionItemExample();
        RepairMalfunctionItemExample.Criteria criteria = example.createCriteria();
        criteria.andMalfunctionIdEqualTo(repairMalfunctionItem.getMalfunctionId());
//        criteria.andModelIdEqualTo(repairMalfunctionItem.getModelId());
        List<RepairMalfunctionItem> repairMalfunctionItems = repairMalfunctionItemMapper.selectByExample(example);
        return repairMalfunctionItems;
    }

    @Override
    public RepairMalfunctionItem get(Integer id) {
        RepairMalfunctionItem repairMalfunctionItem = repairMalfunctionItemMapper.selectByPrimaryKey(id);
        return repairMalfunctionItem;
    }

    @Override
    public List<RepairMalfunctionAndItemsVO> queryMalfunctionsAndItemsByModelId(Integer modelId) {

////        List<RepairMalfunctionAndItemsVO> list = repairMalfunctionItemMapper.selectMalfunctionsDistinct(modelId);
//
//        for (RepairMalfunctionAndItemsVO repairMalfunctionItem : list) {
//            RepairMalfunctionItemExample example = new RepairMalfunctionItemExample();
//            RepairMalfunctionItemExample.Criteria criteria = example.createCriteria();
//
//            criteria.andMalfunctionIdEqualTo(repairMalfunctionItem.getId());
//            List<RepairMalfunctionItem> repairMalfunctionItems = repairMalfunctionItemMapper.selectByExample(example);
//            repairMalfunctionItem.setRepairMalfunctionItems(repairMalfunctionItems);
//
//            RepairMalfunction repairMalfunction = repairMalfunctionMapper.selectByPrimaryKey(repairMalfunctionItem.getId());
////            repairMalfunctionItem.setElectricApplianceId(repairMalfunction.getElectricApplianceId());
//            repairMalfunctionItem.setName(repairMalfunction.getName());
//        }
//
//
//        for (RepairMalfunctionAndItemsVO repairMalfunctionAndItemsVO : list) {
//            System.out.println(">>>"+repairMalfunctionAndItemsVO);
//        }
//
//        return list;
        return null;
    }

    @Override
    public List<RepairMalfunctionItem> queryAllMalfunctionItemByMalfunctionId(Integer malfunctionId) {
        RepairMalfunctionItemExample example = new RepairMalfunctionItemExample();
        example.createCriteria().andMalfunctionIdEqualTo(malfunctionId);
        List<RepairMalfunctionItem> repairMalfunctionItemList = repairMalfunctionItemMapper.selectByExample(example);
        return repairMalfunctionItemList;
    }
}
