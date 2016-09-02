package com.shuashua.buss.Model.Beans;

import net.gy.SwiftFrameWork.IOC.Model.local.shareprefrence.annotation.SPOptions;
import net.gy.SwiftFrameWork.MVVM.Interface.IHandler;

import java.io.Serializable;

/**
 * Created by pc on 16/8/3.
 */
@SPOptions(name = "userinfo")
public class User implements Serializable,IHandler{

    private String name;
    private String passmd5;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassmd5() {
        return passmd5;
    }

    public void setPassmd5(String passmd5) {
        this.passmd5 = passmd5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void handler() throws Exception{

    }
}
