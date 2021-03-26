package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechatOrder.dao.SecondTypeMapper;
import wechatOrder.po.SecondType;
import wechatOrder.po.SecondTypeExample;


import java.util.List;

/**
 * @author JJ
 * @date 2020/2/12 - 17:39
 */
@RestController
@RequestMapping("/secondType")
public class SecondTypeController {

    @Autowired
    private SecondTypeMapper secondTypeMapper;

    @RequestMapping
    public ResponseEntity queryAll(){
        SecondTypeExample secondTypeExample = new SecondTypeExample();
        List<SecondType> secondTypes = secondTypeMapper.selectByExample(secondTypeExample);
        return new ResponseEntity(secondTypes, HttpStatus.OK);
    }
}
