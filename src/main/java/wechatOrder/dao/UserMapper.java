package wechatOrder.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechatOrder.po.Specification;
import wechatOrder.po.User;

@Repository
public interface UserMapper {

    User getUserByOpenid(String openid);

    void saveUser(User user);

    void updateUserByOpenid(User user);
}