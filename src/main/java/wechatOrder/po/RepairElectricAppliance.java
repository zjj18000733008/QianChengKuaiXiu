package wechatOrder.po;

/**
 * @author JJ
 * @date 2019/12/9 - 21:03
 */
public class RepairElectricAppliance {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "RepairElectricAppliance{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
        this.name = name;
    }
}
