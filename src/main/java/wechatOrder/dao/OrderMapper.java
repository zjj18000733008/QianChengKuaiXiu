package wechatOrder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import wechatOrder.po.Order;
import wechatOrder.po.OrderExample;
import wechatOrder.po.vo.QueryOrdersForStaffVo;
import wechatOrder.po.vo.OrderVO;

public interface OrderMapper {

    void saveOrder(OrderVO orderVO);

    int updateOrderById(Order order);

    List<QueryOrdersForStaffVo> queryAllOrdersWithAddress(Map map);

    QueryOrdersForStaffVo getOrderWithAddressByOrderId(String orderId);

    Order getOrderById(String id);

    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}