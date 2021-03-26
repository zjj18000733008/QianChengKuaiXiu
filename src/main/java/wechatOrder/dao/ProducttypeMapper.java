package wechatOrder.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wechatOrder.po.Producttype;

public interface ProducttypeMapper {

    List<Producttype> queryAllType();
}