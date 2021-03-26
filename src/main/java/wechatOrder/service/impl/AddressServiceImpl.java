package wechatOrder.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import wechatOrder.dao.AddressMapper;
import wechatOrder.exception.UnAuthorizedException;
import wechatOrder.po.Address;
import wechatOrder.po.AddressExample;
import wechatOrder.service.AddressService;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 在进行相应的操作时,都应当查询被操作的数据是否属于当前用户
 *
 * @author JJ
 * @date 2019/12/1 - 14:56
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource(name = "addressMapper")
    AddressMapper addressMapper;

    @Override
    public boolean isTheAddressBelongToTheUser(Integer addressId, Integer userId) {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        if(!address.getUserId().equals(userId)){
            return false;
        }
        return true;
    }

    @Override
    public Integer saveAddress(Address address) {
        Integer userId = address.getUserId();
        Address defaultAddress = this.getDefaultAddress(userId);
        if(defaultAddress==null){//如果之前没有默认地址
            address.setState("1");
        }else{
            address.setState("0");
        }
        return addressMapper.insertSelective(address);
    }

    /**
     * @param address 要更改的地址的信息
     */
    @Override
    public void updateAddress(Address address) {

        addressMapper.updateByPrimaryKeySelective(address);
    }

    /**
     * @param userId 从session中取出的当前用户的id
     * @return
     */
    @Override
    public List<Address> queryAllAddress(Integer userId) {
        AddressExample addressExample = new AddressExample();
        ArrayList<String> stateNotIn = new ArrayList<String>();
        stateNotIn.add("-1");//不查询出状态为已被删除的订单
        addressExample.createCriteria().andUserIdEqualTo(userId).andStateNotIn(stateNotIn);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }



    @Override
    public Address getDefaultAddress(Integer userId) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(userId).andStateEqualTo("1");
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        if(addresses.size()==0){
            return null;
        }else{
            return addresses.get(0);
        }
    }

    @Override
    public Address getAddress(Integer addressId) {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        return address;
    }

    /**
     * @param id     要删除的地址的id
     */
    @Override
    public void deleteAddress(Integer id) {

        Address address = new Address();
        //删除地址其实就是把地址的state改为-1
        address.setState("-1");
        //要注意不要忘记传入地址的id
        address.setId(id);

        addressMapper.updateByPrimaryKeySelective(address);
    }

    /**
     * 用于判断被操作的数据是否属于当前用户
     *
     * @param addressId 被操作的的地址的id
     * @param userId    从session中取出的当前用户的id
     */
    @Deprecated
    private void isAuthorized(Integer addressId, Integer userId) {
        if (addressMapper.getAddress(addressId).getUserId() != userId) {//如果从数据库中查出的该地址的userid不是当前用户的id
            throw new UnAuthorizedException();
        }
    }
}
