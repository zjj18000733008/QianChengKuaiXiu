package wechatOrder.po;

import java.io.Serializable;

/**
 * 这个pojo用于存储购物车中某种规格商品的信息
 * @author JJ
 * @date 2019/12/4 - 9:19
 */
public class CartItem implements Serializable {

    private Integer productId;

    private Integer specificationId;

    private String specificationImg;//此规格商品的规格图

    private Double unitPrice;//此规格商品的单价

    private Double totalPrice;//购物车中此规格商品的总价

    private Integer num;//购物车中此规格商品的数量

    private String productName;//此商品的名称

    private String typeId;//此商品的类型

    private String specificationName;//此规格商品的名称

    private String addTime;//此规格商品添加到购物车中的时间

    @Override
    public String toString() {
        return "CartItem{" +
                "productId=" + productId +
                ", specificationId=" + specificationId +
                ", specificationImg='" + specificationImg + '\'' +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", num=" + num +
                ", productName='" + productName + '\'' +
                ", typeId='" + typeId + '\'' +
                ", specificationName='" + specificationName + '\'' +
                ", addTime='" + addTime + '\'' +
                '}';
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public String getSpecificationImg() {
        return specificationImg;
    }

    public void setSpecificationImg(String specificationImg) {
        this.specificationImg = specificationImg;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
