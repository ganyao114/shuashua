package com.shuashua.buss.View.Widgets.RateView;

import com.shuashua.buss.Model.Entity.StringEntry;

import java.io.Serializable;

/**
 * Created by pc on 16/8/5.
 */
public class RateBean implements Serializable{
    private int id;
    private String name;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
