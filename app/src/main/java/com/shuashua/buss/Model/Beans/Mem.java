package com.shuashua.buss.Model.Beans;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * Created by pc on 16/8/16.
 */
@ListDataSrc(R.layout.item_memmng_layout)
public class Mem implements Serializable{

    private String id;
    private String name;
    private String tel;
    private String photourl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}
