package wechatOrder.po.vo;

import wechatOrder.po.Order;
import wechatOrder.po.Orderitem;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/7 - 12:56
 */
public class OrderVO extends Order {

    @Valid
    @NotNull(message = "orderItem 不能为空")
    List<Orderitem> orderItems;

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderItem=" + orderItems +
                "} " + super.toString();
    }

    public List<Orderitem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Orderitem> orderItems) {
        this.orderItems = orderItems;
    }
}
