package com.shuashua.buss.Model.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/8/1.
 */
public class CardPropertys implements Serializable{

    private String type;
    private String name;
    private float value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

