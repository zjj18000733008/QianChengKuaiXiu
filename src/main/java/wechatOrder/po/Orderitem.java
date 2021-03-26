package wechatOrder.po;

public class Orderitem {
    private Integer id;

    private String orderId;

    private Integer specificationId;

    private Integer buynum;

    private Double unitPrice;

    @Override
    public String toString() {
        return "Orderitem{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", specificationId=" + specificationId +
                ", buynum=" + buynum +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }
}