package wechatOrder.po.vo;

import wechatOrder.po.Specification;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author JJ
 * @date 2020/2/7 - 16:20
 */
public class SpecificationVo {

    @Valid
    @NotNull(message = "specifications不全")
    private List<Specification> specifications;

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "ProductSpecificationVo{" +
                "specifications=" + specifications +
                "} " + super.toString();
    }
}
