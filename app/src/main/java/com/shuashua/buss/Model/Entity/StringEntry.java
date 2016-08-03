package com.shuashua.buss.Model.Entity;

import net.gy.SwiftFrameWork.Model.entity.Entry;

import java.io.Serializable;

/**
 * Created by pc on 16/8/2.
 */
public class StringEntry implements Entry<String,String>,Serializable{

    private String k;
    private String v;

    public StringEntry() {
    }

    public StringEntry(String k, String v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public void set(String key, String value) {
        this.k = key;
        this.v = value;
    }

    @Override
    public String getKey() {
        return k;
    }

    @Override
    public String getValue() {
        return v;
    }
}
