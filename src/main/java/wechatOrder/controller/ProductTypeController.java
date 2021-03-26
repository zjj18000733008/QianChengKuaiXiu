package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechatOrder.dao.ProducttypeMapper;
import wechatOrder.po.Producttype;

import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/5 - 18:50
 */
@RestController
@RequestMapping("/type")
public class ProductTypeController {

    @Autowired
    private ProducttypeMapper producttypeMapper;

    @RequestMapping
    public List  queryAllType(){
        List<Producttype> producttypes = producttypeMapper.queryAllType();
        return producttypes;
    }
}
