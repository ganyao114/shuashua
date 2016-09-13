package com.shuashua.buss.Model.Beans;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindText;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonString;

/**
 * Created by gy939 on 2016/9/12.
 */
@ListDataSrc(R.layout.item_text)
public class Area {

    @BindText(R.id.textView)
    @JsonString("areaName")
    private String name;
    @JsonString("id")
    private String id;

    public Area() {
    }

    public Area(String name, String id) {
        this.name = name;
        this.id = id;
    }

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
