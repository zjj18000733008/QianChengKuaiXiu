package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wechatOrder.dao.RepairBrandMapper;
import wechatOrder.exception.SomeParamIsNullException;
import wechatOrder.po.RepairBrand;
import wechatOrder.po.RepairBrandExample;
import wechatOrder.po.vo.RepairBrandsVO;
import wechatOrder.service.RepairBrandService;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 10:12
 */
@Service
public class RepairBrandServiceImpl implements RepairBrandService {

    @Autowired
    private RepairBrandMapper repairBrandMapper;

    /**
     * 此方法目前不能维护主外键关系,即插入的electricApplianceId可以是电器表中不存在的数据
     * @param repairBrandVO
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchSave(RepairBrandsVO repairBrandVO) throws Exception {
        List<RepairBrand> repairBrands = repairBrandVO.getRepairBrands();
        if(repairBrands==null){
            throw new SomeParamIsNullException();
        }
        for (RepairBrand repairBrand : repairBrands) {
            if(repairBrand.getName()==null || repairBrand.getElectricApplianceId()==null || repairBrand.getWeight()==null){
                throw new Exception("参数为空");
            }
            if(repairBrand.getId()!=null){
                repairBrand.setId(null);
            }
            repairBrandMapper.insertSelective(repairBrand);
        }
    }

    @Override
    public void delete(Integer id) {
        repairBrandMapper.deleteByPrimaryKey(id);
    }


    /**
     * 要保证传来的id不为空
     * @param repairBrand
     */
    @Override
    public void update(RepairBrand repairBrand) {
        repairBrandMapper.updateByPrimaryKeySelective(repairBrand);
    }

    @Override
    public List<RepairBrand> query(Integer electricApplianceId) {
        RepairBrandExample example = new RepairBrandExample();
        example.setOrderByClause("weight desc");
        RepairBrandExample.Criteria criteria = example.createCriteria();
        criteria.andElectricApplianceIdEqualTo(electricApplianceId);
        List<RepairBrand> repairBrands = repairBrandMapper.selectByExample(example);
        return repairBrands;
    }

    @Override
    public RepairBrand get(Integer id) {
        RepairBrand repairBrand = repairBrandMapper.selectByPrimaryKey(id);
        return repairBrand;
    }
}
