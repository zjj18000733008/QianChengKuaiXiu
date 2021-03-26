package wechatOrder.po;

public class ProductWithBLOBs extends Product {
    private String surfaceImg;

    private String slideImg;

    private String introImg;

    public String getSurfaceImg() {
        return surfaceImg;
    }

    public void setSurfaceImg(String surfaceImg) {
        this.surfaceImg = surfaceImg == null ? null : surfaceImg.trim();
    }

    public String getSlideImg() {
        return slideImg;
    }

    public void setSlideImg(String slideImg) {
        this.slideImg = slideImg == null ? null : slideImg.trim();
    }

    public String getIntroImg() {
        return introImg;
    }

    public void setIntroImg(String introImg) {
        this.introImg = introImg == null ? null : introImg.trim();
    }
}