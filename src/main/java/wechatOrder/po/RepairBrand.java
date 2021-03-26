package wechatOrder.po;

public class RepairBrand {
    private Integer id;

    private String name;

    private Integer electricApplianceId;

    private String weight;

    @Override
    public String toString() {
        return "RepairBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", electricApplianceId=" + electricApplianceId +
                ", weight='" + weight + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getElectricApplianceId() {
        return electricApplianceId;
    }

    public void setElectricApplianceId(Integer electricApplianceId) {
        this.electricApplianceId = electricApplianceId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }
}