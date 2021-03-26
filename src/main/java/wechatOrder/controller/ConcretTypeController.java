package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechatOrder.dao.ConcretTypeMapper;
import wechatOrder.po.ConcretType;
import wechatOrder.po.ConcretTypeExample;

import java.util.List;

/**
 * @author JJ
 * @date 2020/2/2 - 12:16
 */
@RestController
@RequestMapping("/concretType")
public class ConcretTypeController {

    @Autowired
    private ConcretTypeMapper concretTypeMapper;

    @RequestMapping
    public ResponseEntity queryAll(){
        ConcretTypeExample concretTypeExample = new ConcretTypeExample();

        List<ConcretType> concretTypes = concretTypeMapper.selectByExample(concretTypeExample);
        return new ResponseEntity(concretTypes, HttpStatus.OK);
    }
}
