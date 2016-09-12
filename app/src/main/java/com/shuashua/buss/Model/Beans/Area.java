package com.shuashua.buss.Model.Beans;

import net.gy.SwiftFrameWork.MVVM.Annotations.JsonString;

/**
 * Created by gy939 on 2016/9/12.
 */
public class Area {

    @JsonString("name")
    private String name;
    @JsonString("id")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
