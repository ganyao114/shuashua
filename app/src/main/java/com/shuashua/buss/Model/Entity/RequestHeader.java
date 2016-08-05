package com.shuashua.buss.Model.Entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 16/8/2.
 */
public class RequestHeader implements Serializable{

    private StringEntry appkey;
    private StringEntry udid;
    private StringEntry os;
    private StringEntry osversion;
    private StringEntry interfacever;
    private StringEntry tokenid;
    private StringEntry userid;

    public StringEntry getAppkey() {
        return appkey;
    }

    public void setAppkey(StringEntry appkey) {
        this.appkey = appkey;
    }

    public StringEntry getUdid() {
        return udid;
    }

    public void setUdid(StringEntry udid) {
        this.udid = udid;
    }

    public StringEntry getOs() {
        return os;
    }

    public void setOs(StringEntry os) {
        this.os = os;
    }

    public StringEntry getOsversion() {
        return osversion;
    }

    public void setOsversion(StringEntry osversion) {
        this.osversion = osversion;
    }

    public StringEntry getInterfacever() {
        return interfacever;
    }

    public void setInterfacever(StringEntry interfacever) {
        this.interfacever = interfacever;
    }

    public StringEntry getTokenid() {
        return tokenid;
    }

    public void setTokenid(StringEntry tokenid) {
        this.tokenid = tokenid;
    }

    public StringEntry getUserid() {
        return userid;
    }

    public void setUserid(StringEntry userid) {
        this.userid = userid;
    }
}
