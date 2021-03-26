package wechatOrder.service;

import wechatOrder.po.Order;
import wechatOrder.po.Orderitem;
import wechatOrder.po.Specification;
import wechatOrder.po.vo.OrderItemVo;
import wechatOrder.po.vo.QueryOrdersForStaffVo;
import wechatOrder.po.vo.OrderVO;
import wechatOrder.po.vo.QueryMyOrdersVo;

import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/7 - 11:23
 */
public interface OrderService {

    OrderVO saveOrderAndOrderItems(OrderVO orderVO);

    List<QueryOrdersForStaffVo> queryOrderForStaff(Map map);

    QueryOrdersForStaffVo getOrderForStaff(String orderId);

    List<QueryMyOrdersVo> queryAllOrdersForUser(Integer userId,String state);

    List<OrderItemVo> getOrderItemsByOrderId(String orderId);

    Integer appointOrderToDeliveryman(String orderId,Integer staffId);

    Order getOrderById(String id);

    void updateOrderById(Order order);

    List<Orderitem> queryOrderItemsByOrderId(String orderid);

}
