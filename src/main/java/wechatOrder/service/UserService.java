package wechatOrder.service;

import wechatOrder.po.User;

/**
 * @author JJ
 * @date 2019/12/1 - 14:13
 */
public interface UserService {

    User getUserByOpenid(String openid);

    void saveUser(User user);

    void updateUserByOpenid(User user);
}
