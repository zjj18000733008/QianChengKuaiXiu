package wechatOrder.service;

import wechatOrder.po.RepairBrand;
import wechatOrder.po.vo.RepairBrandsVO;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 10:10
 */
public interface RepairBrandService {

    void batchSave(RepairBrandsVO repairBrands) throws Exception;

    void delete(Integer id);

    void update(RepairBrand repairBrand);

    List<RepairBrand> query(Integer electricApplianceId);

    RepairBrand get(Integer id);
}
