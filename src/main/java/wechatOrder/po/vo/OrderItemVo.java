package wechatOrder.po.vo;

import wechatOrder.po.Orderitem;

/**
 * @author JJ
 * @date 2020/1/18 - 15:47
 */
public class OrderItemVo extends Orderitem {

    private String img;

    private String specificationName;

    private Integer productId;

    private String productName;


    @Override
    public String toString() {
        return "OrderItemVo{" +
                "img='" + img + '\'' +
                ", specificationName='" + specificationName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                "} " + super.toString();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
}
