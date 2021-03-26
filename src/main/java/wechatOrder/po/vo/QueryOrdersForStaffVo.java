package wechatOrder.po.vo;

import wechatOrder.po.Order;

/**
 * @author JJ
 * @date 2019/12/7 - 18:37
 */
public class QueryOrdersForStaffVo extends Order {

    private String deliveryman;

    private String receiverName;

    private String mobile;

    private String area;

    private String streetAddress;

    public String getDeliveryman() {
        return deliveryman;
    }

    public void setDeliveryman(String deliveryman) {
        this.deliveryman = deliveryman;
    }

    @Override
    public String toString() {
        return "QueryOrdersForStaffVo{" +
                "deliveryman='" + deliveryman + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", area='" + area + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                "} " + super.toString();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
