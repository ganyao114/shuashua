package com.shuashua.buss.View.Widgets.CitySelect.Model;

import java.io.Serializable;

/**
 * Created by pc on 16/8/10.
 */
public class AreaBase implements Serializable{

    private String id;
    private String parentid;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
