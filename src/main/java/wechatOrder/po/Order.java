package wechatOrder.po;

public class Order {
    private String id;

    private Integer deliverymanId;

    private Integer userId;

    private Integer addressId;

    private String pattern;

    private String paymentMethod;

    private String leaveWord;

    private String servicingTime;

    private Double goodsAmount;

    private Double freightCharge;

    private Double actualAmount;

    private String orderTime;

    private String payTime;

    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(Integer deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern == null ? null : pattern.trim();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    public String getLeaveWord() {
        return leaveWord;
    }

    public void setLeaveWord(String leaveWord) {
        this.leaveWord = leaveWord == null ? null : leaveWord.trim();
    }

    public String getServicingTime() {
        return servicingTime;
    }

    public void setServicingTime(String servicingTime) {
        this.servicingTime = servicingTime == null ? null : servicingTime.trim();
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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}