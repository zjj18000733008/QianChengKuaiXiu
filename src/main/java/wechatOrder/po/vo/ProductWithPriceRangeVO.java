package wechatOrder.po.vo;

import wechatOrder.po.Product;

/**
 * @author JJ
 * @date 2019/11/30 - 13:41
 */
public class ProductWithPriceRangeVO extends Product {

    private Double maxPrice;

    private Double minPrice;

    @Override
    public String toString() {
        return "ProductWithPriceRangeVO{" +
                "maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", id=" + id +
                ", productName='" + productName + '\'' +
                ", overview='" + overview + '\'' +
                ", typeId=" + typeId +
                ", concretTypeId=" + concretTypeId +
                ", surfaceImg='" + surfaceImg + '\'' +
                ", weight='" + weight + '\'' +
                ", addTime='" + addTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", state='" + state + '\'' +
                "} " + super.toString();
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
