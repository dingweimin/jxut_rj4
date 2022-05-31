package com.jxut.model;


import java.util.Date;

/**
 * @Author: WeiMin
 * @Date: 2022/5/24 11:33
 */
public class User {
    private int id;
    private String userName;//用户名
    private String realName;//真实姓名
    private String password;//密码
    private String sex;//性别
    private Date  birthday;//出生日期
    private String tel;//电话号码
    private String address;//用户地址
    private String type;//用户类型
    private String pic;//图像地址
    private String if_valid;//是否有效

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIf_valid() {
        return if_valid;
    }

    public void setIf_valid(String if_valid) {
        this.if_valid = if_valid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", pic='" + pic + '\'' +
                ", if_valid='" + if_valid + '\'' +
                '}';
    }
}
