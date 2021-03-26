package wechatOrder.po.vo;

import org.springframework.validation.annotation.Validated;
import wechatOrder.po.Product;
import wechatOrder.po.Specification;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 这个类用来接收前端传来的商品信息以及商品的规格信息
 * @author JJ
 * @date 2019/11/26 - 13:40
 */
public class ProductSpecificationVO extends Product {

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
