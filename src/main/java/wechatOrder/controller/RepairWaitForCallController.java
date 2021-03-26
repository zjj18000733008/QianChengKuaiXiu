package wechatOrder.controller;

import com.sun.org.apache.regexp.internal.RESyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.RepairWaitforcallMapper;
import wechatOrder.po.RepairWaitforcall;
import wechatOrder.po.RepairWaitforcallExample;


import java.util.List;

/**
 * @author JJ
 * @date 2020/2/11 - 1:14
 */
@RestController
@RequestMapping("/waitforcall")
public class RepairWaitForCallController {

    @Autowired
    private RepairWaitforcallMapper repairWaitforcallMapper;

    /**
     *
     * @param waitforcall
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping("/save")
    public ResponseEntity waitforcall(RepairWaitforcall waitforcall){
        int i = repairWaitforcallMapper.insertSelective(waitforcall);
        if(i==1){
            return new ResponseEntity("OK", HttpStatus.OK);
        }else{
            return new ResponseEntity("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/queryAll")
    public ResponseEntity queryAll(@RequestParam(required = false) String state,@RequestParam(required = false) Integer merchantId){
        RepairWaitforcallExample repairWaitforcallExample = new RepairWaitforcallExample();
        RepairWaitforcallExample.Criteria criteria = repairWaitforcallExample.createCriteria();
        if(state!=null){
            criteria.andStateEqualTo(state);
        }
        if(merchantId!=null){
            criteria.andMerchantIdEqualTo(merchantId);
        }
        List<RepairWaitforcall> repairWaitforcalls = repairWaitforcallMapper.selectByExample(repairWaitforcallExample);
        return new ResponseEntity(repairWaitforcalls,HttpStatus.OK);
    }

    @RequestMapping("/queryAll/page")
    public ModelAndView queryAllToPage(ModelAndView mv,@RequestParam(required = false) String state,@RequestParam(required = false) Integer merchantId){
        RepairWaitforcallExample repairWaitforcallExample = new RepairWaitforcallExample();
        RepairWaitforcallExample.Criteria criteria = repairWaitforcallExample.createCriteria();
        if(state!=null){
            criteria.andStateEqualTo(state);
        }
        if(merchantId!=null){
            criteria.andMerchantIdEqualTo(merchantId);
        }
        List<RepairWaitforcall> repairWaitforcalls = repairWaitforcallMapper.selectByExample(repairWaitforcallExample);
        mv.addObject("repairOrders",repairWaitforcalls);
        mv.setViewName("waitforcall-list");
        return mv;
    }

}
