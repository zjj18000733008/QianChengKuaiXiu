package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.MerchantMapper;
import wechatOrder.group.Insert;
import wechatOrder.group.Update;
import wechatOrder.po.Merchant;
import wechatOrder.po.MerchantExample;


import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2020/4/6 - 1:46
 */
@RequestMapping("/merchant")
@RestController
public class MerchantController {

    @Autowired
    private MerchantMapper merchantMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/add")
    public ResponseEntity add(@Validated({Insert.class}) Merchant merchant, BindingResult result){

        if (result.hasErrors()) {
            ArrayList<String> errList = new ArrayList<String>();
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                errList.add(allError.getDefaultMessage());
            }
            return new ResponseEntity(errList, HttpStatus.PRECONDITION_REQUIRED);
        }

        int i = merchantMapper.insert(merchant);

        if(i==1){
            return new ResponseEntity("OK",HttpStatus.OK);
        }else{
            return new ResponseEntity("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/remove")
    public ResponseEntity remove(Integer id){
        int i = merchantMapper.deleteByPrimaryKey(id);
        if(i==1){
            return new ResponseEntity("OK",HttpStatus.OK);
        }else{
            return new ResponseEntity("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/modify")
    public ResponseEntity modify(@Validated({Update.class}) Merchant merchant , BindingResult result){
        if (result.hasErrors()) {
            ArrayList<String> errList = new ArrayList<String>();
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                errList.add(allError.getDefaultMessage());
            }
            return new ResponseEntity(errList, HttpStatus.PRECONDITION_REQUIRED);
        }
        int i = merchantMapper.updateByPrimaryKey(merchant);
        if(i==1){
            return new ResponseEntity("OK",HttpStatus.OK);
        }else{
            return new ResponseEntity("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/deleteInBatch")
    public ResponseEntity deleteInBatch(Integer[] ids){

        for (Integer id : ids) {
            merchantMapper.deleteByPrimaryKey(id);
        }
        return new ResponseEntity("OK",HttpStatus.OK);
    }

    @RequestMapping("/get")
    public ResponseEntity get(Integer id){
        Merchant merchant = merchantMapper.selectByPrimaryKey(id);
        if(merchant==null){
            return new ResponseEntity("id["+id+"] dosen't exist!!",HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity(merchant,HttpStatus.OK);
    }

    @RequestMapping("/queryAll")
    public ResponseEntity queryAll(){
        List<Merchant> merchants = merchantMapper.selectByExample(new MerchantExample());
        return new ResponseEntity(merchants,HttpStatus.OK);
    }

    @RequestMapping("/queryAll/page")
    public ModelAndView queryAllToPage(ModelAndView mv){

        List<Merchant> merchants = merchantMapper.selectByExample(new MerchantExample());
        mv.addObject("merchants",merchants);
        mv.setViewName("merchant-list");
        return mv;
    }

}
