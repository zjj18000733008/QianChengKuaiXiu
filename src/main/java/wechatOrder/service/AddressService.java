package wechatOrder.service;

import org.springframework.stereotype.Service;
import wechatOrder.po.Address;

import java.util.List;

/**
 * @author JJ
 * @date 2019/12/1 - 14:51
 */
@Service
public interface AddressService {
    Integer saveAddress(Address address);

    void updateAddress(Address address);

    boolean isTheAddressBelongToTheUser(Integer addressId,Integer userId);

    List<Address> queryAllAddress(Integer userId);

    Address getAddress(Integer addressId);

    Address getDefaultAddress(Integer userId);

    /**
     * 删除地址时,从session中取出user的id,判断要删除的地址的userId是否等于user的id
     * @param id
     */
    void deleteAddress(Integer id);
}
