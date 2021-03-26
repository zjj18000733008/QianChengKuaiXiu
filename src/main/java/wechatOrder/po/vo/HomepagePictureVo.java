package wechatOrder.po.vo;

import wechatOrder.po.HomepagePicture;

import java.util.List;

/**
 * @author JJ
 * @date 2020/2/9 - 14:03
 */
public class HomepagePictureVo {

    List<HomepagePicture> homepagePictures;

    public List<HomepagePicture> getHomepagePictures() {
        return homepagePictures;
    }

    public void setHomepagePictures(List<HomepagePicture> homepagePictures) {
        this.homepagePictures = homepagePictures;
    }

    @Override
    public String toString() {
        return "HomepagePictureVo{" +
                "homepagePictures=" + homepagePictures +
                '}';
    }
}
