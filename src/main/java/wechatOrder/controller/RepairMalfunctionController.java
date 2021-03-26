package wechatOrder.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wechatOrder.dao.RepairMalfunctionMapper;
import wechatOrder.po.RepairMalfunction;
import wechatOrder.po.RepairMalfunctionExample;
import wechatOrder.po.vo.RepairMalfunctionAndItemsVO;
import wechatOrder.po.vo.RepairMalfunctionVO;
import wechatOrder.service.RepairMalfunctionItemService;
import wechatOrder.service.RepairMalfunctionService;
import wechatOrder.util.FatherToChildUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 8:56
 */
@RestController
@RequestMapping("/repair/malfunction")
public class RepairMalfunctionController {

    @Autowired
    private RepairMalfunctionMapper repairMalFunctionMapper;
    @Autowired
    private RepairMalfunctionItemService repairMalfunctionItemService;
    @Autowired
    private RepairMalfunctionService repairMalfunctionService;

//    /**
//     * 此接口用于给某一电器预指定一些大体的故障类型(如:电池故障,显示屏故障...)
//     * 必填参数:
//     *      electric_appliance_id 故障所属的电器的id(如:手机这种电器的id,平板这种电器的id)
//     *      repairMalfunctions[?].name 故障的大体类型(如:电池故障,显示屏故障...)
//     * @param repairMalfunctions
//     * @return
//     */
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @RequestMapping("/save")
//    public ResponseEntity save(Integer electric_appliance_id, RepairMalfunctionVO repairMalfunctions){
//
//        if(repairMalfunctions==null || electric_appliance_id==null ){
//            return new ResponseEntity("参数为空", HttpStatus.PRECONDITION_REQUIRED);
//        }
//        List<RepairMalfunction> repairMalfunctionList = repairMalfunctions.getRepairMalfunctions();
//        if(repairMalfunctionList==null){
//            return new ResponseEntity("参数为空",HttpStatus.PRECONDITION_REQUIRED);
//        }
//        for (RepairMalfunction repairMalFunction : repairMalfunctionList) {
//            repairMalFunction.setElectricApplianceId(electric_appliance_id);
//            repairMalFunctionMapper.insertSelective(repairMalFunction);
//        }
//
//        return new ResponseEntity("添加成功",HttpStatus.OK);
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/save")
    public ResponseEntity save(RepairMalfunctionVO repairMalfunctionVO) {
        List<RepairMalfunction> repairMalfunctions = repairMalfunctionVO.getRepairMalfunctions();
        for (RepairMalfunction repairMalfunction : repairMalfunctions) {
            repairMalFunctionMapper.insertSelective(repairMalfunction);
        }
        return new ResponseEntity("添加成功", HttpStatus.OK);
    }

    /**
     * 存储malfunction和malfunctionItem
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/saveWithItems")
    public ResponseEntity saveWithItems(
            RepairMalfunctionAndItemsVO repairMalfunctionAndItemsVO, BindingResult result) {

        repairMalfunctionService.saveMalfunctionWithItems(repairMalfunctionAndItemsVO);

        return new ResponseEntity("添加成功",HttpStatus.OK);
    }

    /**
     * 调用此接口以删除某个故障类型
     * 必填参数:
     * id 故障类型id
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/delete")
    public ResponseEntity delete(@RequestParam(required = true) Integer id) {
        repairMalFunctionMapper.deleteByPrimaryKey(id);

        return new ResponseEntity("删除成功", HttpStatus.OK);
    }

    /**
     * 调用此接口以更改某种故障类型的名称
     * 必填参数:
     * id 故障类型id
     * name 故障类型名称
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/update")
    public ResponseEntity update(RepairMalfunction repairMalFunction) {
        if (repairMalFunction == null || repairMalFunction.getName() == null) {
            return new ResponseEntity("参数为空", HttpStatus.PRECONDITION_REQUIRED);
        }
        repairMalFunctionMapper.updateByPrimaryKeySelective(repairMalFunction);

        return new ResponseEntity("修改成功", HttpStatus.OK);
    }

    /**
     * 批量更新
     *
     * @param repairMalFunctionVO
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/updateInBatch")
    public ResponseEntity updateInBatch(RepairMalfunctionVO repairMalFunctionVO) {

        List<RepairMalfunction> repairMalfunctions = repairMalFunctionVO.getRepairMalfunctions();
        for (RepairMalfunction repairMalfunction : repairMalfunctions) {
            if (repairMalfunction.getId() == null) {
                return new ResponseEntity("malfunctionId不能为空", HttpStatus.PRECONDITION_REQUIRED);
            }
        }

        for (RepairMalfunction repairMalfunction : repairMalfunctions) {
            repairMalFunctionMapper.updateByPrimaryKeySelective(repairMalfunction);
        }

        return new ResponseEntity("修改成功", HttpStatus.OK);
    }

//    /**
//     * 查询出指定电器的所有故障类型
//     * 必填参数:
//     *      applianceId 电器id
//     * @return
//     */
//    @RequestMapping("/query")
//    public ResponseEntity query(@RequestParam(required = true) Integer applianceId){
//        RepairMalfunctionExample example = new RepairMalfunctionExample();
//        RepairMalfunctionExample.Criteria criteria = example.createCriteria();
//        criteria.andElectricApplianceIdEqualTo(applianceId);
//
//        List<RepairMalfunction> repairMalFunctions = repairMalFunctionMapper.selectByExample(example);
//        return new ResponseEntity(repairMalFunctions,HttpStatus.OK);
//    }

    @RequestMapping("/query")
    public ResponseEntity query(@RequestParam Integer modelId) {
        RepairMalfunctionExample example = new RepairMalfunctionExample();
        example.createCriteria().andModelIdEqualTo(modelId);
        List<RepairMalfunction> repairMalfunctions = repairMalFunctionMapper.selectByExample(example);
        return new ResponseEntity(repairMalfunctions, HttpStatus.OK);
    }

    @RequestMapping("/queryWithItems")
    public ResponseEntity queryWithItems(@RequestParam Integer modelId) throws Exception {
        ArrayList<RepairMalfunctionAndItemsVO> repairMalfunctionAndItemsVOS = new ArrayList<RepairMalfunctionAndItemsVO>();
        //根据modelId查询出所有的malfunction
        RepairMalfunctionExample example = new RepairMalfunctionExample();
        example.createCriteria().andModelIdEqualTo(modelId);
        List<RepairMalfunction> repairMalfunctions = repairMalFunctionMapper.selectByExample(example);
        for (RepairMalfunction repairMalfunction : repairMalfunctions) {
            RepairMalfunctionAndItemsVO repairMalfunctionAndItemsVO = new RepairMalfunctionAndItemsVO();
            repairMalfunctionAndItemsVO.setId(repairMalfunction.getId());
            repairMalfunctionAndItemsVO.setName(repairMalfunction.getName());
            repairMalfunctionAndItemsVO.setModelId(repairMalfunction.getModelId());
            //根据malfunctionId查询出所有的malfunctionItem
            repairMalfunctionAndItemsVO.setRepairMalfunctionItems(repairMalfunctionItemService.queryAllMalfunctionItemByMalfunctionId(repairMalfunction.getId()));
            repairMalfunctionAndItemsVOS.add(repairMalfunctionAndItemsVO);
        }
        return new ResponseEntity(repairMalfunctionAndItemsVOS, HttpStatus.OK);

    }

    @RequestMapping("/get")
    public ResponseEntity get(@RequestParam Integer id) {
        RepairMalfunction repairMalfunction = repairMalFunctionMapper.selectByPrimaryKey(id);
        return new ResponseEntity(repairMalfunction, HttpStatus.OK);
    }
}
