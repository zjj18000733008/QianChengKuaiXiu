package wechatOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wechatOrder.dao.SpecificationMapper;
import wechatOrder.po.Specification;
import wechatOrder.po.CartItem;
import wechatOrder.service.SpecificationService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JJ
 * @date 2019/12/4 - 12:12
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public void saveSpecification(Specification specification) {
        specificationMapper.saveSpecification(specification);
    }

    /**
     *
     * @param cart cart是一个Map<Integer,Integer> ,其中key是specificationId,value是该规格商品的数量
     * @return 一个装有CartItem的list,其中CartItem的每一个属性都设置好了值
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<CartItem> getCartItems(Map<Integer,Integer> cart) {

        List<CartItem> list = new ArrayList();

        Iterator<Integer> iterator = cart.keySet().iterator();
        int size = cart.size();//获得购物车中商品种类数
        Integer[] specificationIds=new Integer[size];//根据种类数创建数组

        int j=0;
        while (iterator.hasNext()){//遍历key
            specificationIds[j]=iterator.next();//将key存入数组
            j++;
        }

        for (int i = 0; i < specificationIds.length; i++) {
            //根据商品规格id查出相应数据
            CartItem goods = specificationMapper.getSpecificationToCartById(specificationIds[i]);
            Double unitPrice = goods.getUnitPrice();//得到该规格商品的单价
            Integer num = cart.get(specificationIds[i]);//得到该规格商品在购物车中的数量
            goods.setNum(num);//设置数量
            goods.setTotalPrice(num*unitPrice);//设置总价
            goods.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            list.add(goods);
        }

        return list;
    }


    @Override
    public Specification getSpecificationById(Integer id) {
        return specificationMapper.getSpecificationById(id);
    }

    @Override
    public void updateSpecificationById(Specification specification) {
        specificationMapper.updateSpecificationById(specification);
    }

    @Override
    public List<Specification> queryByProductId(Integer id) {

        return specificationMapper.queryByProductId(id);
    }

    @Override
    public void deleteSpecification(Integer id) {
        specificationMapper.deleteSpecification(id);
    }
}
