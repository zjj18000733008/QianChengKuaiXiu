package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.RepairBrandMapper;
import wechatOrder.dao.RepairElectricApplianceMapper;
import wechatOrder.po.RepairBrand;
import wechatOrder.po.RepairBrandExample;
import wechatOrder.po.RepairElectricAppliance;
import wechatOrder.po.vo.RepairBrandsVO;
import wechatOrder.po.vo.RepairBrandsWithApplianceNameVO;
import wechatOrder.service.RepairBrandService;
import wechatOrder.util.FatherToChildUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 10:04
 */
@RestController
@RequestMapping("/repair/brand")
public class RepairBrandController {


    @Autowired
    private RepairBrandService repairBrandService;
    @Autowired
    private RepairBrandMapper repairBrandMapper;
    @Autowired
    private RepairElectricApplianceMapper repairElectricApplianceMapper;
    /**
     * 必填参数
     *      name 品牌名称
     *      electricApplianceId 关联的电器的id
     *      weight 权重默认为5
     * @param repairBrands
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/save")
    public ResponseEntity save(RepairBrandsVO repairBrands){

        if(repairBrands==null){
            return new ResponseEntity("参数为空",HttpStatus.PRECONDITION_REQUIRED);
        }

        try {
            repairBrandService.batchSave(repairBrands);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("添加失败,参数不能为空,请检查参数",HttpStatus.PRECONDITION_REQUIRED);
        }

        return new ResponseEntity("添加成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/delete")
    public ResponseEntity delete(@RequestParam Integer id){
        repairBrandService.delete(id);
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/deleteInBatch")
    public ResponseEntity deleteInBatch(Integer[] ids){
        for (Integer id : ids) {
            repairBrandService.delete(id);
        }
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }

    /**
     * 必传参数:
     *      id
     * 可选参数:
     *      name
     *      weight
     * @param repairBrand
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/update")
    public ResponseEntity update(RepairBrand repairBrand){
        if(repairBrand==null || repairBrand.getId()==null){
            return new ResponseEntity("参数不能为空",HttpStatus.PRECONDITION_REQUIRED);
        }
        repairBrandService.update(repairBrand);
        return new ResponseEntity("修改成功",HttpStatus.OK);
    }

    @RequestMapping("/query")
    public ResponseEntity query(@RequestParam Integer electricApplianceId){
        List<RepairBrand> repairBrands = repairBrandService.query(electricApplianceId);
        return new ResponseEntity(repairBrands,HttpStatus.OK);
    }

    /**
     * 此接口用于在添加机型前,查询出所有的品牌和所属的电器
     * @return
     */
    @RequestMapping("/queryAllWithAppliance")
    public ResponseEntity queryAllWithAppliance() throws Exception {
        ArrayList<RepairBrandsWithApplianceNameVO> repairBrandsWithApplianceNameList = new ArrayList<RepairBrandsWithApplianceNameVO>();
        RepairBrandExample example = new RepairBrandExample();
        example.setOrderByClause("electric_appliance_id desc");
        List<RepairBrand> repairBrands = repairBrandMapper.selectByExample(example);
        for (RepairBrand repairBrand : repairBrands) {
            RepairBrandsWithApplianceNameVO brandsWithApplianceNameVO = new RepairBrandsWithApplianceNameVO();
//            FatherToChildUtils.fatherToChild(repairBrand,brandsWithApplianceNameVO);
            brandsWithApplianceNameVO.setId(repairBrand.getId());
            brandsWithApplianceNameVO.setElectricApplianceId(repairBrand.getElectricApplianceId());
            brandsWithApplianceNameVO.setWeight(repairBrand.getWeight());
            brandsWithApplianceNameVO.setName(repairBrand.getName());
            Integer electricApplianceId = repairBrand.getElectricApplianceId();
            RepairElectricAppliance appliance = repairElectricApplianceMapper.getById(electricApplianceId);
            brandsWithApplianceNameVO.setApplianceName(appliance.getName());
            repairBrandsWithApplianceNameList.add(brandsWithApplianceNameVO);
        }
        return new ResponseEntity(repairBrandsWithApplianceNameList,HttpStatus.OK);
    }

    @RequestMapping("/query/page")
    public ModelAndView queryToPage(ModelAndView mv) throws Exception {
        RepairBrandExample example = new RepairBrandExample();
        example.setOrderByClause("electric_appliance_id desc");
        List<RepairBrand> repairBrands = repairBrandMapper.selectByExample(example);
        List<RepairElectricAppliance> repairElectricAppliances = repairElectricApplianceMapper.queryAll();
        mv.addObject("brands",repairBrands);
        mv.addObject("appliances",repairElectricAppliances);
        mv.setViewName("brand-list");
        return mv;
    }

    @RequestMapping("/get")
    public ResponseEntity get(@RequestParam Integer id){
        RepairBrand repairBrand = repairBrandService.get(id);
        return new ResponseEntity(repairBrand,HttpStatus.OK);
    }


}
