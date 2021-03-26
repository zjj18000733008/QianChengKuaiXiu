package wechatOrder.service.impl;

import org.springframework.stereotype.Service;
import wechatOrder.dao.UserMapper;
import wechatOrder.po.User;
import wechatOrder.service.UserService;

import javax.annotation.Resource;

/**
 * @author JJ
 * @date 2019/12/1 - 14:13
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource(name="userMapper")
    UserMapper userMapper;

    @Override
    public User getUserByOpenid(String openid) {
        return userMapper.getUserByOpenid(openid);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void updateUserByOpenid(User user) {
        userMapper.updateUserByOpenid(user);
    }
}
