package com.shuashua.buss.Model.Beans;


import com.shuashua.buss.R;
import com.shuashua.buss.View.Activity.CityPickerActivity;

import net.gy.SwiftFrameWork.MVVM.Annotations.BindListView;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonOrm;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonSet;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonString;
import net.gy.SwiftFrameWork.MVVM.Interface.IHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gy939 on 2016/9/12.
 */
@JsonOrm
public class ResultArea implements IHandler{

    @JsonString("name")
    private String name;
    @JsonString("id")
    private String id;
    @BindListView(CityPickerActivity.ListViewId)
    @JsonSet(name = "areas",clazz = Area.class)
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

    @Override
    public void handler() throws Exception {
        if (child == null)
            child = new ArrayList<>();
        child.add(0,new Area(name,id));
    }
}
