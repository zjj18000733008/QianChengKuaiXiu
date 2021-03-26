package wechatOrder.po;

public class User {
    private Integer id;

    private String gender;

    private String userAvatar;

//    private String username;
//
//    private String password;



    private String nickname;

//    private String address;

    private Integer roleId;

    private String openid;

    private String city;

    private String province;

    private String country;

    private String phoneNumber;

    private String sessionKey;

    public User(){

    }
    public User(User user){
        this.setOpenid(user.getOpenid());
        this.setUserAvatar(user.getUserAvatar());
        this.setCountry(user.getCountry());
        this.setProvince(user.getProvince());
        this.setCity(user.getCity());
        this.setGender(user.getGender());
        this.setId(user.getId());
        this.setSessionKey(user.getSessionKey());
        this.setNickname(user.getNickname());
        this.setRoleId(user.getRoleId());
        this.setPhoneNumber(user.getPhoneNumber());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", roleId=" + roleId +
                ", openid='" + openid + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sessinKey='" + sessionKey + '\'' +
                '}';
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessinKey) {
        this.sessionKey = sessinKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}