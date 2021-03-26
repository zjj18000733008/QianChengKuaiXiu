package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechatOrder.dao.RepairModelMapper;
import wechatOrder.po.RepairModel;
import wechatOrder.po.RepairModelExample;
import wechatOrder.po.vo.RepairModelsVO;
import wechatOrder.service.RepairModelService;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 11:08
 */
@Service
public class RepairModelServiceImpl implements RepairModelService {

    @Autowired
    private RepairModelMapper repairModelMapper;

    @Override
    public void batchSave(RepairModelsVO repairModelsVO) throws Exception {
        List<RepairModel> repairModels = repairModelsVO.getRepairModels();
        for (RepairModel repairModel : repairModels) {
            if (repairModel.getName() == null || repairModel.getBrandId() == null) {
                throw new Exception("参数不能为空");
            }
            repairModelMapper.insertSelective(repairModel);
        }
    }

    @Override
    public void delete(Integer id) {
        repairModelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(RepairModel repairModel) {

        repairModelMapper.updateByPrimaryKeySelective(repairModel);
    }

    @Override
    public List<RepairModel> query(Integer brandId) {
        RepairModelExample example = new RepairModelExample();
        example.setOrderByClause("weight desc");
        RepairModelExample.Criteria criteria = example.createCriteria();
        criteria.andBrandIdEqualTo(brandId);

        return repairModelMapper.selectByExample(example);
    }

    @Override
    public RepairModel get(Integer id) {
        return repairModelMapper.selectByPrimaryKey(id);
    }
}
