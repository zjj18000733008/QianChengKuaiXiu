package wechatOrder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.Order;
import wechatOrder.po.Orderitem;
import wechatOrder.po.OrderitemExample;
import wechatOrder.po.vo.QueryMyOrdersVo;

public interface OrderitemMapper {

    void saveOrderItem(Orderitem orderitem);

    Order getOrderById(Integer id);

    /**
     * userId
     * index
     * pageSize
     * @param map
     * @return
     */
    QueryMyOrdersVo queryAllOrderItemsByUserIdGroupByOrderIdForUser(Map map);

    void updateOrderById(Order order);

    List<Orderitem> queryOrderItemsByOrderId(String orderid);

    int countByExample(OrderitemExample example);

    int deleteByExample(OrderitemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    List<Orderitem> selectByExample(OrderitemExample example);

    Orderitem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByExample(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);
}