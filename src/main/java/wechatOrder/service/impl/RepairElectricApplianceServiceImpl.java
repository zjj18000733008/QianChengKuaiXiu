package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechatOrder.dao.RepairElectricApplianceMapper;
import wechatOrder.po.RepairElectricAppliance;
import wechatOrder.service.RepairElectricApplianceService;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/9 - 21:25
 */
@Service
public class RepairElectricApplianceServiceImpl implements RepairElectricApplianceService {

    @Autowired
    private RepairElectricApplianceMapper repairElectricApplianceMapper;

    @Override
    public Integer save(RepairElectricAppliance repairElectricAppliance) {
        repairElectricApplianceMapper.save(repairElectricAppliance);
        return repairElectricAppliance.getId();
    }

    @Override
    public void deleteById(Integer id) {
        repairElectricApplianceMapper.deleteById(id);
    }

    @Override
    public void updateById(RepairElectricAppliance repairElectricAppliance) {
        repairElectricApplianceMapper.updateById(repairElectricAppliance);
    }

    @Override
    public RepairElectricAppliance getById(Integer id) {
        return repairElectricApplianceMapper.getById(id);
    }

    @Override
    public List<RepairElectricAppliance> queryAll() {
        return repairElectricApplianceMapper.queryAll();
    }
}
