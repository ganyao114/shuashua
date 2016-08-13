package com.shuashua.buss.Model.Beans;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindText;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by pc on 16/8/12.
 */
@ListDataSrc(R.layout.item_text)
public class AeraBean implements Serializable{

    private String id;
    private String pid;
    @BindText(R.id.textView)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
