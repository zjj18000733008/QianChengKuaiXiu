package wechatOrder.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Product {

    protected Integer id;
    @NotBlank(message = "productName为空" ,groups = {})
    protected String productName;
    @NotBlank(message = "overview为空")
    protected String overview;
    @NotNull(message = "typeId为空")
    protected Integer typeId;
    @NotNull(message = "concretTypeId为空")
    protected Integer concretTypeId;

    protected Integer secondTypeId;

    @NotBlank(message = "surfaceImg为空")
    protected String surfaceImg;
    @NotBlank(message = "slideImg为空")
    private String slideImg;
    @NotBlank(message = "introImg为空")
    private String introImg;
    @NotBlank(message = "weight为空")
    protected String weight;

    protected String addTime;

    protected String modifyTime;
    @NotBlank(message = "state为空")
    protected String state;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", overview='" + overview + '\'' +
                ", typeId=" + typeId +
                ", concretTypeId=" + concretTypeId +
                ", secondTypeId=" + secondTypeId +
                ", surfaceImg='" + surfaceImg + '\'' +
                ", slideImg='" + slideImg + '\'' +
                ", introImg='" + introImg + '\'' +
                ", weight='" + weight + '\'' +
                ", addTime='" + addTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getSecondTypeId() {
        return secondTypeId;
    }

    public void setSecondTypeId(Integer secondTypeId) {
        this.secondTypeId = secondTypeId;
    }

    public Integer getConcretTypeId() {
        return concretTypeId;
    }

    public void setConcretTypeId(Integer concretTypeId) {
        this.concretTypeId = concretTypeId;
    }

    public String getSlideImg() {
        return slideImg;
    }

    public void setSlideImg(String slideImg) {
        this.slideImg = slideImg;
    }

    public String getIntroImg() {
        return introImg;
    }

    public void setIntroImg(String introImg) {
        this.introImg = introImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getSurfaceImg() {
        return surfaceImg;
    }

    public void setSurfaceImg(String surfaceImg) {
        this.surfaceImg = surfaceImg == null ? null : surfaceImg.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}