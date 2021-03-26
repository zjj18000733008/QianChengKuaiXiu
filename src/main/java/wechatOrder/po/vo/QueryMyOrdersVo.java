package wechatOrder.po.vo;

import wechatOrder.po.Orderitem;

import java.util.List;

/**
 * @author JJ
 * @date 2020/1/18 - 13:49
 */
public class QueryMyOrdersVo {

    private String orderId;
    private String deliveryman;
    private String deliverymanPhone;
    private Double goodsAmount;//商品总金额
    private Double freightCharge;//货运金额
    private Double actualAmount;//实付金额
    private String state;//订单状态
    private List<OrderItemVo> orderItemVos;

    public String getDeliveryman() {
        return deliveryman;
    }

    public void setDeliveryman(String deliveryman) {
        this.deliveryman = deliveryman;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "QueryMyOrdersVo{" +
                "orderId='" + orderId + '\'' +
                ", deliveryman='" + deliveryman + '\'' +
                ", deliverymanPhone='" + deliverymanPhone + '\'' +
                ", goodsAmount=" + goodsAmount +
                ", freightCharge=" + freightCharge +
                ", actualAmount=" + actualAmount +
                ", state='" + state + '\'' +
                ", orderItemVos=" + orderItemVos +
                '}';
    }

    public String getDeliverymanPhone() {
        return deliverymanPhone;
    }

    public void setDeliverymanPhone(String deliverymanPhone) {
        this.deliverymanPhone = deliverymanPhone;
    }

    public Double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Double getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(Double freightCharge) {
        this.freightCharge = freightCharge;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrderItemVo> getOrderItemVos() {
        return orderItemVos;
    }

    public void setOrderItemVos(List<OrderItemVo> orderItemVos) {
        this.orderItemVos = orderItemVos;
    }
}
