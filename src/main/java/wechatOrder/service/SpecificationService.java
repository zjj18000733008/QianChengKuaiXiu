package wechatOrder.service;

import wechatOrder.po.Specification;
import wechatOrder.po.CartItem;

import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/4 - 12:12
 */
public interface SpecificationService {

    List<CartItem> getCartItems(Map<Integer,Integer> map);

    Specification getSpecificationById(Integer id);

    void saveSpecification(Specification specification);

    void updateSpecificationById(Specification specification);

    List<Specification> queryByProductId(Integer id);

    void deleteSpecification(Integer id);
}
