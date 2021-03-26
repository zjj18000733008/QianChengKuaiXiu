package wechatOrder.service;

import wechatOrder.dao.RepairElectricApplianceMapper;
import wechatOrder.po.RepairElectricAppliance;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/9 - 21:23
 */
public interface RepairElectricApplianceService {

    Integer save(RepairElectricAppliance repairElectricAppliance);

    void deleteById(Integer id);

    void updateById(RepairElectricAppliance repairElectricAppliance);

    RepairElectricAppliance getById(Integer id);

    List<RepairElectricAppliance> queryAll();
}
