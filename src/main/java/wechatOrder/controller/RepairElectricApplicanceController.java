package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.RepairElectricApplianceMapper;
import wechatOrder.po.RepairElectricAppliance;
import wechatOrder.po.Staff;
import wechatOrder.service.RepairElectricApplianceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 这个类是用于操作所能修理的电器种类的
 *
 * @author JJ
 * @date 2019/12/9 - 20:59
 */
@RestController
@RequestMapping("/repair/electricAppliance")
public class RepairElectricApplicanceController {

    @Autowired
    private RepairElectricApplianceService repairElectricApplianceService;

    /**
     * 必填参数: name
     * @param repairElectricAppliance
     * @return 返回刚刚插入的数据的id
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/save")
    public ResponseEntity save(RepairElectricAppliance repairElectricAppliance, HttpServletRequest req){

        if(repairElectricAppliance==null || repairElectricAppliance.getName()==null){
            return new ResponseEntity("参数不能为空", HttpStatus.PRECONDITION_REQUIRED);
        }

        Integer id = repairElectricApplianceService.save(repairElectricAppliance);
        HashMap map = new HashMap();
        map.put("id",id);
        return new ResponseEntity(map,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/delete")
    public ResponseEntity delete(@RequestParam(required = true) Integer id, HttpServletRequest req){


        repairElectricApplianceService.deleteById(id);

        return new ResponseEntity("删除成功",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/deleteInBatches")
    public ResponseEntity deleteInBatchese(Integer[] ids){
        for (Integer id : ids) {
            if(id==null){
                continue;
            }
            repairElectricApplianceService.deleteById(id);
        }
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }

    @RequestMapping("/get")
    public ResponseEntity get(@RequestParam(required = true) Integer id,HttpServletRequest req){


        RepairElectricAppliance vo = repairElectricApplianceService.getById(id);

        return new ResponseEntity(vo,HttpStatus.OK);
    }

    @RequestMapping("/query")
    public ResponseEntity query(HttpServletRequest req){
        List<RepairElectricAppliance> repairElectricAppliance = repairElectricApplianceService.queryAll();
        return new ResponseEntity(repairElectricAppliance,HttpStatus.OK);
    }

    @RequestMapping("/query/page")
    public ModelAndView queryToPage(ModelAndView mv){
        List<RepairElectricAppliance> repairElectricAppliances = repairElectricApplianceService.queryAll();
        mv.addObject("appliances",repairElectricAppliances);
        mv.setViewName("electricalAppliance-list");
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/update")
    public ResponseEntity update(RepairElectricAppliance repairElectricAppliance,HttpServletRequest req){

        if(repairElectricAppliance==null || repairElectricAppliance.getName()==null || repairElectricAppliance.getId()==null){
            return new ResponseEntity("参数不能为空",HttpStatus.PRECONDITION_REQUIRED);
    }

        repairElectricApplianceService.updateById(repairElectricAppliance);

        return new ResponseEntity("修改成功",HttpStatus.OK);
    }


}
