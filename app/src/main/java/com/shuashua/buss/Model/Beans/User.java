package com.shuashua.buss.Model.Beans;

import net.gy.SwiftFrameWork.IOC.Model.local.shareprefrence.annotation.SPOptions;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonOrm;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonString;
import net.gy.SwiftFrameWork.MVVM.Interface.IHandler;

import java.io.Serializable;

/**
 * Created by pc on 16/8/3.
 */
@JsonOrm
@SPOptions(name = "userinfo")
public class User implements Serializable,IHandler{

    @JsonString("username")
    private String name;
    @JsonString("icon")
    private String iconurl;
    @JsonString("email")
    private String email;
    @JsonString("tel")
    private String tel;
    @JsonString("realName")
    private String realName;
    @JsonString("shopid")
    private String shopIn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getShopIn() {
        return shopIn;
    }

    public void setShopIn(String shopIn) {
        this.shopIn = shopIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", iconurl='" + iconurl + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", realName='" + realName + '\'' +
                ", shopIn='" + shopIn + '\'' +
                '}';
    }

    @Override
    public void handler() throws Exception {

    }
}
