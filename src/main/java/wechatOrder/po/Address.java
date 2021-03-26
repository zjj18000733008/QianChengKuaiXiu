package wechatOrder.po;

import javax.validation.constraints.NotNull;

public class Address {
    private Integer id;
    @NotNull(message = "收货人名称不能为空")
    private String receiverName;
    @NotNull(message = "手机号不能为空")
    private String mobile;
    @NotNull(message="收货地区不能为空")
    private String area;
    @NotNull(message="收货人所在具体地址不能为空")
    private String streetAddress;

    private Integer userId;

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress == null ? null : streetAddress.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}