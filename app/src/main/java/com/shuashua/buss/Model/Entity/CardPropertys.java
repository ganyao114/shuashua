package com.shuashua.buss.Model.Entity;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindText;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.OnBtClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.OnFoucsChange;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/8/1.
 */
@ListDataSrc(R.layout.item_card_property)
public class CardPropertys implements Serializable{

    @OnBtClick({R.id.property_type,R.id.property_rm})
    @BindText(R.id.property_type)
    private String type = "属性类型>";
    @BindText(R.id.property_name)
    @OnFoucsChange({R.id.property_name})
    private String name = "";
    private float value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type+">";
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

