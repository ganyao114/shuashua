package com.shuashua.buss.Model.Entity;

import net.gy.SwiftFrameWork.IOC.Model.local.shareprefrence.annotation.SPColumeName;
import net.gy.SwiftFrameWork.IOC.Model.local.shareprefrence.annotation.SPOptions;

/**
 * Created by gy on 2016/9/2.
 */
@SPOptions(name = "session")
public class SessionHolder {

    @SPColumeName("tokenid")
    private String jsessionid;
    @SPColumeName("udid")
    private String udid;

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }
}
