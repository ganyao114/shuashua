package com.shuashua.buss.Model.Beans;

import net.gy.SwiftFrameWork.MVVM.Annotations.JsonOrm;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonSet;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonString;

import java.util.List;

/**
 * Created by gy939 on 2016/9/12.
 */
@JsonOrm
public class ResultArea {

    @JsonString("name")
    private String name;
    @JsonString("id")
    private String id;
    @JsonSet(name = "children",clazz = Area.class)
    private List<Area> child;

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

    public List<Area> getChild() {
        return child;
    }

    public void setChild(List<Area> child) {
        this.child = child;
    }
}
