package wechatOrder.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wechatOrder.po.Address;
import wechatOrder.po.AddressExample;

public interface AddressMapper {
    void saveAddress(Address address);

    void updateAddress(Address address);

    List<Address> queryAllAddresses(Integer userId);

    Address getAddress(Integer id);

    int countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}