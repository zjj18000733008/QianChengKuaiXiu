package wechatOrder.po;

import org.hibernate.validator.constraints.NotBlank;
import wechatOrder.group.Insert;
import wechatOrder.group.Update;

import javax.validation.constraints.NotNull;

public class Merchant {

    @NotNull(message = "id cannot be null",groups = Update.class)
    private Integer id;
    @NotBlank(message="name cannot be blank" ,groups = {Update.class, Insert.class})
    private String name;
    @NotBlank(message="address cannot be blank" ,groups = {Update.class, Insert.class})
    private String address;
    @NotBlank(message="phone cannot be blank" ,groups = {Update.class, Insert.class})
    private String phone;
    @NotBlank(message="longitude cannot be blank" ,groups = {Update.class, Insert.class})
    private String longitude;
    @NotBlank(message="latitude cannot be blank" ,groups = {Update.class, Insert.class})
    private String latitude;
    @NotBlank(message="img cannot be blank" ,groups = {Update.class, Insert.class})
    private String img;
    @NotBlank(message="url cannot be blank" ,groups = {Update.class,Insert.class})
    private String url;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}