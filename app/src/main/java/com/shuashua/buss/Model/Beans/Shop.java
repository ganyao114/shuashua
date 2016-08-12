package com.shuashua.buss.Model.Beans;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindAsyncImgUrl;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindText;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.OnBtClick;

import java.io.Serializable;

/**
 * Created by pc on 16/8/1.
 */
@ListDataSrc(R.layout.item_mngshop_layout)
public class Shop implements Serializable{

    @OnBtClick(R.id.btn_shop_more)
    private String id;
    @BindText(R.id.shops_title)
    private String name;
    @BindText(R.id.shops_desc)
    private String Desc;
    private String kind;
    private String subKind;
    private String areacode;
    private String areaName;
    @BindAsyncImgUrl(R.id.shops_cover)
    private String coverUrl;
    private String tel;
    @BindText(R.id.shops_vips)
    private String grade;

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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getSubKind() {
        return subKind;
    }

    public void setSubKind(String subKind) {
        this.subKind = subKind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
