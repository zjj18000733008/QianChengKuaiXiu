package wechatOrder.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import wechatOrder.po.Address;
import wechatOrder.po.User;
import wechatOrder.service.AddressService;
import wechatOrder.util.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2020/1/19 - 23:38
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    private static final Logger logger= LoggerFactory.getLogger(AddressController.class);

    @RequestMapping("/save")
    public ResponseEntity save(HttpServletRequest req , @Validated Address address , BindingResult result){
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if(user==null){
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }

        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            ArrayList<String> allErrorList = new ArrayList<String>();
            for (ObjectError allError : allErrors) {
                allErrorList.add(allError.getDefaultMessage());
            }
            return new ResponseEntity(allErrorList,HttpStatus.PRECONDITION_REQUIRED);
        }
        User user = (User) SecurityUtils.getCurrentUser();
        address.setUserId(user.getId());
        Integer i = addressService.saveAddress(address);
        if(i==1){
            return new ResponseEntity("添加成功",HttpStatus.OK);
        }else{
            return new ResponseEntity("添加失败",HttpStatus.PRECONDITION_FAILED);
        }

    }

    @RequestMapping("/delete")
    public ResponseEntity delete (HttpServletRequest req,@RequestParam Integer addressId){
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if(user==null){
//            return new ResponseEntity("请先登录",HttpStatus.UNAUTHORIZED);
//        }
        User user = (User) SecurityUtils.getCurrentUser();
        boolean theAddressBelongToTheUser = addressService.isTheAddressBelongToTheUser(addressId, user.getId());
        if(theAddressBelongToTheUser){
            addressService.deleteAddress(addressId);
            return new ResponseEntity("删除成功",HttpStatus.OK);
        }else{
            logger.warn("userId为"+user.getId()+"的用户正尝试未授权操作->删除addressId为"+addressId+"的地址");
            return new ResponseEntity("未授权",HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/update")
    public ResponseEntity update(HttpServletRequest req,Address address){
//        HttpSession session = req.getSession();
//        User user = (User)session.getAttribute("user");
//        if(user==null){
//            return new ResponseEntity("请先登录",HttpStatus.UNAUTHORIZED);
//        }

        User user = (User) SecurityUtils.getCurrentUser();
        Integer addressId = address.getId();
        if(addressId==null){
            return new ResponseEntity("addressId不能为空",HttpStatus.PRECONDITION_REQUIRED);
        }

        boolean theAddressBelongToTheUser = addressService.isTheAddressBelongToTheUser(addressId, user.getId());
        if(theAddressBelongToTheUser){
            address.setUserId(user.getId());
            addressService.updateAddress(address);
            return new ResponseEntity("修改成功",HttpStatus.OK);
        }else{
            logger.warn("userId为"+user.getId()+"的用户正尝试未授权操作->修改addressId为"+addressId+"的地址");
            return new ResponseEntity("未授权",HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping("/query")
    public ResponseEntity query(HttpServletRequest req){
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if(user==null){
//            return new ResponseEntity("请先登录",HttpStatus.UNAUTHORIZED);
//        }
        User user = (User) SecurityUtils.getCurrentUser();
        List<Address> addresses = addressService.queryAllAddress(user.getId());
        return new ResponseEntity(addresses,HttpStatus.OK);
    }

    @RequestMapping("/get")
    public ResponseEntity get(HttpServletRequest req,@RequestParam Integer addressId){

        Address address = addressService.getAddress(addressId);
        return new ResponseEntity(address,HttpStatus.OK);
    }

    @RequestMapping("/getDefaultAddress")
    public ResponseEntity getDefaultAddress(HttpServletRequest req){
        User user = (User) SecurityUtils.getCurrentUser();
        Address defaultAddress = addressService.getDefaultAddress(user.getId());
        return new ResponseEntity(defaultAddress,HttpStatus.OK);
    }

    @RequestMapping("/setToDefault")
    public ResponseEntity setToDefault(HttpServletRequest req,Integer addressId){
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if(user==null){
//            return new ResponseEntity("请先登录",HttpStatus.UNAUTHORIZED);
//        }

        User user = (User) SecurityUtils.getCurrentUser();
        boolean theAddressBelongToTheUser = addressService.isTheAddressBelongToTheUser(addressId, user.getId());
        if(!theAddressBelongToTheUser){
            return new ResponseEntity("未授权",HttpStatus.UNAUTHORIZED);
        }

        Address defaultAddress = addressService.getDefaultAddress(user.getId());
        if(defaultAddress!=null){//如果默认地址不为空,代表之前已经有默认地址
            defaultAddress.setState("0");//将原来的默认地址改为非默认
            addressService.updateAddress(defaultAddress);
        }
        //将制定地址改为默认
        Address address = new Address();
        address.setId(addressId);
        address.setState("1");
        addressService.updateAddress(address);
        return new ResponseEntity("修改成功",HttpStatus.OK);
    }
}
