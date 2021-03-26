package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wechatOrder.dao.RepairMalfunctionItemMapper;
import wechatOrder.po.RepairMalfunction;
import wechatOrder.po.RepairMalfunctionItem;
import wechatOrder.po.vo.RepairMalfunctionAndItemsVO;
import wechatOrder.po.vo.RepairMalfunctionItemVo;
import wechatOrder.service.RepairMalfunctionItemService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2019/12/10 - 12:30
 */
@RestController
@RequestMapping("/repair/malfunctionItem")
public class RepairMalfunctionItemController {

    @Autowired
    private RepairMalfunctionItemService repairMalfunctionItemService;
    @Autowired
    private RepairMalfunctionItemMapper repairMalfunctionItemMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/save")
    public ResponseEntity save(@Validated RepairMalfunctionItem repairMalfunctionItem, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            ArrayList<String> errorList = new ArrayList<String>();
            for (ObjectError allError : allErrors) {
                errorList.add(allError.getDefaultMessage());
            }
            return new ResponseEntity(errorList, HttpStatus.PRECONDITION_REQUIRED);
        }
        repairMalfunctionItemService.save(repairMalfunctionItem);
        return new ResponseEntity("添加成功", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/saveInBatch")
    public ResponseEntity saveInBatch(RepairMalfunctionItemVo repairMalfunctionItemVo){
        List<RepairMalfunctionItem> repairMalfunctionItems = repairMalfunctionItemVo.getRepairMalfunctionItems();
        for (RepairMalfunctionItem repairMalfunctionItem : repairMalfunctionItems) {
            if(repairMalfunctionItem.getMalfunctionId()==null){
                return new ResponseEntity("malfunctionId不能为空",HttpStatus.PRECONDITION_REQUIRED);
            }
        }

        for (RepairMalfunctionItem repairMalfunctionItem : repairMalfunctionItems) {
            repairMalfunctionItemMapper.insertSelective(repairMalfunctionItem);
        }

        return new ResponseEntity("添加成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/delete")
    public ResponseEntity delete(@RequestParam Integer id) {
        repairMalfunctionItemService.delete(id);
        return new ResponseEntity("删除成功", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/update")
    public ResponseEntity update(RepairMalfunctionItem repairMalfunctionItem) {
        if (repairMalfunctionItem == null || repairMalfunctionItem.getId() == null) {
            return new ResponseEntity("参数不能为空", HttpStatus.PRECONDITION_REQUIRED);
        }
        repairMalfunctionItemService.update(repairMalfunctionItem);
        return new ResponseEntity("更新成功", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/updateInBatch")
    public ResponseEntity updateInBatch(RepairMalfunctionItemVo repairMalfunctionItemVo) {

        List<RepairMalfunctionItem> repairMalfunctionItems = repairMalfunctionItemVo.getRepairMalfunctionItems();
        for (RepairMalfunctionItem repairMalfunctionItem : repairMalfunctionItems) {
            if(repairMalfunctionItem.getId()==null){
                return new ResponseEntity("malfuncitonItemId不能为空",HttpStatus.PRECONDITION_REQUIRED);
            }
        }

        for (RepairMalfunctionItem repairMalfunctionItem : repairMalfunctionItems) {
            repairMalfunctionItemMapper.updateByPrimaryKeySelective(repairMalfunctionItem);
        }
        return new ResponseEntity("更新成功", HttpStatus.OK);
    }

//    /**
//     * 必填参数:
//     * malfunctin_id: 故障id
//     * model_id: 机型id
//     *
//     * @param repairMalfunctionItem
//     * @return
//     */
//    @RequestMapping("/query")
//    public ResponseEntity query(RepairMalfunctionItem repairMalfunctionItem) {
//        if (repairMalfunctionItem == null  || repairMalfunctionItem.getMalfunctionId() == null) {
//            return new ResponseEntity("参数不能为空", HttpStatus.PRECONDITION_REQUIRED);
//        }
//        List<RepairMalfunctionItem> repairMalfunctionItems = repairMalfunctionItemService.query(repairMalfunctionItem);
//        return new ResponseEntity(repairMalfunctionItems, HttpStatus.OK);
//    }

    @RequestMapping("/get")
    public ResponseEntity get(@RequestParam Integer id) {
        RepairMalfunctionItem repairMalfunctionItem = repairMalfunctionItemService.get(id);
        return new ResponseEntity(repairMalfunctionItem, HttpStatus.OK);
    }



//    @RequestMapping("/queryWithItems")
//    public ResponseEntity queryWithItems(@RequestParam Integer modelId){
//        List<RepairMalfunctionAndItemsVO> list = repairMalfunctionItemService.queryMalfunctionsAndItemsByModelId(modelId);
//        return new ResponseEntity(list,HttpStatus.OK);
//    }

}
