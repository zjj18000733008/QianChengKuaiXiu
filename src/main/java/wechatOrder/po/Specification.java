package wechatOrder.po;

import javax.validation.constraints.NotNull;

public class Specification {
    private Integer id;

    private Integer productId;
    @NotNull(message = "specification.name为空")
    private String name;
    @NotNull(message = "specification.originalPrice为空")
    private Double originalPrice;
    @NotNull(message = "specification.currentPrice为空")
    private Double currentPrice;
    @NotNull(message = "specification.inventory为空")
    private Integer inventory;
    @NotNull(message = "specification.img为空")
    private String img;

    private String state;

    @Override
    public String toString() {
        return "Specification{" +
                "id=" + id +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", originalPrice=" + originalPrice +
                ", currentPrice=" + currentPrice +
                ", inventory=" + inventory +
                ", img='" + img + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}