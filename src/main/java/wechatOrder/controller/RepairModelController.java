package wechatOrder.controller;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.RepairBrandMapper;
import wechatOrder.dao.RepairElectricApplianceMapper;
import wechatOrder.dao.RepairModelMapper;
import wechatOrder.po.*;
import wechatOrder.po.vo.RepairModelWithBrandAndApplianceVO;
import wechatOrder.po.vo.RepairModelsVO;
import wechatOrder.service.RepairBrandService;
import wechatOrder.service.RepairModelService;
import wechatOrder.service.impl.RepairBrandServiceImpl;
import wechatOrder.util.FatherToChildUtils;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 11:04
 */
@RestController
@RequestMapping("/repair/model")
public class RepairModelController {

    @Autowired
    private RepairModelService repairModelService;
    @Autowired
    private RepairModelMapper repairModelMapper;
    @Autowired
    private RepairElectricApplianceMapper repairElectricApplianceMapper;
    @Autowired
    private RepairBrandMapper repairBrandMapper;
    @Autowired
    private RepairBrandService repairBrandService;

    /**
     * 批量添加机型
     * 必填参数:
     *      repairModels[?].name 机型名称
     *      repairModels[?].brandId 机型所属品牌id
     *      repairModels[?].weight 机型权重
     * @param repairModels
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/save")
    public ResponseEntity save(RepairModelsVO repairModels){
        if(repairModels==null){
            return new ResponseEntity("参数不能为空", HttpStatus.PRECONDITION_REQUIRED);
        }
        try {
            repairModelService.batchSave(repairModels);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("参数不能为空",HttpStatus.PRECONDITION_REQUIRED);
        }
        return new ResponseEntity("添加成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/delete")
    public ResponseEntity delete(@RequestParam Integer id){
        repairModelService.delete(id);
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }

    @RequestMapping("/deleteInBatch")
    public ResponseEntity deleteInBatch(Integer[] ids){
        for (Integer id : ids) {
            repairModelMapper.deleteByPrimaryKey(id);
        }
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }

    /**
     * 修改机型名称
     * 必填参数:
     *      id 机型的id
     * 可选参数:
     *      name 机型的名称
     *      weight 权重
     * @param repairModel
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/update")
    public ResponseEntity update(RepairModel repairModel){
        if(repairModel==null || repairModel.getId()==null){
            return new ResponseEntity("参数不能为空",HttpStatus.PRECONDITION_REQUIRED);
        }
        repairModelService.update(repairModel);
        return new ResponseEntity("修改成功",HttpStatus.OK);
    }

    /**
     * 查询某一品牌的所有机型
     * 必填参数:
     *      brandId 品牌id
     * @param brandId
     * @return
     */
    @RequestMapping("/query")
    public ResponseEntity query(Integer brandId){
        if(brandId==null){
            return new ResponseEntity("brandId不能为空",HttpStatus.PRECONDITION_REQUIRED) ;
        }
        List<RepairModel> repairModels = repairModelService.query(brandId);
        return new ResponseEntity(repairModels,HttpStatus.OK);
    }

    @RequestMapping("/query/page")
    public ModelAndView queryToPage(ModelAndView mv){
        RepairModelExample example = new RepairModelExample();
        example.setOrderByClause("brand_id desc,weight desc");
        List<RepairModel> repairModels = repairModelMapper.selectByExample(example);
        List<RepairBrand> repairBrands = repairBrandMapper.selectByExample(new RepairBrandExample());
        List<RepairElectricAppliance> repairElectricAppliances = repairElectricApplianceMapper.queryAll();
        mv.addObject("models",repairModels);
        mv.addObject("brands",repairBrands);
        mv.addObject("brandservice",repairBrandService);
        mv.addObject("appliances",repairElectricAppliances);
        mv.setViewName("model-list");
        return mv;
    }

    /**
     * 根据机型id查询机型信息
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public ResponseEntity get(Integer id){
        RepairModel repairModel = repairModelService.get(id);
        return new ResponseEntity(repairModel,HttpStatus.OK);
    }

    /**
     * 用于故障详情页malfunction.jsp
     * @param id modelId
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping("/get/page")
    public ModelAndView getToPage(Integer id,ModelAndView mv) throws Exception {
        RepairModelWithBrandAndApplianceVO modelVO = new RepairModelWithBrandAndApplianceVO();

        RepairModel repairModel = repairModelService.get(id);
        RepairBrandExample example = new RepairBrandExample();
        RepairBrand repairBrand = repairBrandMapper.selectByPrimaryKey(repairModel.getBrandId());
        RepairElectricAppliance appliance = repairElectricApplianceMapper.getById(repairBrand.getElectricApplianceId());
        FatherToChildUtils.fatherToChild(repairModel,modelVO);
        modelVO.setBrandName(repairBrand.getName());
        modelVO.setApplianceName(appliance.getName());
        mv.addObject("model",modelVO);
        mv.setViewName("malfunction");
        return mv;

    }
}
