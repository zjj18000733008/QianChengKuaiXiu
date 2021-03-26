package wechatOrder.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class RepairMalfunctionItem {
    private Integer id;
    @NotBlank(message = "itemName不能为空")
    private String itemName;
    @NotNull(message = "原价不能为空")
    private Double originalPrice;
    @NotNull(message = "现价不能为空")
    private Double currentPrice;
    @NotNull(message = "malfunctionId不能为空")
    private Integer malfunctionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
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

    public Integer getMalfunctionId() {
        return malfunctionId;
    }

    public void setMalfunctionId(Integer malfunctionId) {
        this.malfunctionId = malfunctionId;
    }
}