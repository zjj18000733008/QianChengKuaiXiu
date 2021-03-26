package wechatOrder.po.vo;

import wechatOrder.po.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JJ
 * @date 2019/11/28 - 21:57
 */
public class ProductVO extends Product {

    private String[] slideImgs;

    private String[] introImgs;

    @Override
    public String toString() {
        return "ProductVO{" +
                "slideImgs=" + Arrays.toString(slideImgs) +
                ", introImgs=" + Arrays.toString(introImgs) +
                ", id=" + id +
                ", productName='" + productName + '\'' +
                ", overview='" + overview + '\'' +
                ", typeId=" + typeId +
                ", concretTypeId=" + concretTypeId +
                ", surfaceImg='" + surfaceImg + '\'' +
                ", weight='" + weight + '\'' +
                ", addTime='" + addTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", state='" + state + '\'' +
                "} " + super.toString();
    }

    public String[] getSlideImgs() {
        return slideImgs;
    }

    public void setSlideImgs(String[] slideImgs) {
        this.slideImgs = slideImgs;
    }

    public String[] getIntroImgs() {
        return introImgs;
    }

    public void setIntroImgs(String[] introImgs) {
        this.introImgs = introImgs;
    }
}
