package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechatOrder.dao.RoleMapper;
import wechatOrder.po.Role;
import wechatOrder.po.RoleExample;

import java.util.List;

/**
 * @author JJ
 * @date 2020/2/8 - 16:05
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @RequestMapping("/findAll")
    public ResponseEntity findAll() {
        RoleExample roleExample = new RoleExample();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return new ResponseEntity(roles, HttpStatus.OK);
    }
}
