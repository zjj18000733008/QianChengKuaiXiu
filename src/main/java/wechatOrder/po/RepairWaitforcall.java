package wechatOrder.po;

public class RepairWaitforcall {
    private Integer id;

    private Integer merchantId;

    private String phone;

    private String name;

    private Integer malfunctionItemId;

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMalfunctionItemId() {
        return malfunctionItemId;
    }

    public void setMalfunctionItemId(Integer malfunctionItemId) {
        this.malfunctionItemId = malfunctionItemId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}