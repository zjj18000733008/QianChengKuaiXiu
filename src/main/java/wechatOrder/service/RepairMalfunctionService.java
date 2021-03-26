package wechatOrder.service;

import org.springframework.stereotype.Service;
import wechatOrder.po.vo.RepairMalfunctionAndItemsVO;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 18:48
 */
public interface RepairMalfunctionService {

    void saveMalfunctionWithItems(RepairMalfunctionAndItemsVO repairMalfunctionAndItemsVO);


}
