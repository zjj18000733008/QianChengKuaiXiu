package wechatOrder.po;

import javax.validation.constraints.NotNull;

public class RepairMalfunction {
    private Integer id;

    private String name;
    @NotNull(message="modelId不能为空")
    private Integer modelId;

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

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }
}