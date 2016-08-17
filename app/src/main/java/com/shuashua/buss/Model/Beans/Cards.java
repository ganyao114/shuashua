package com.shuashua.buss.Model.Beans;

import com.shuashua.buss.Model.Entity.CardPropertys;
import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindAsyncImgUrl;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindText;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.OnBtClick;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/8/1.
 */
@ListDataSrc(R.layout.item_mainfrag_cards)
public class Cards implements Serializable{

    @OnBtClick({R.id.btn_newmem,R.id.btn_newdd})
    private String id;
    @BindText(R.id.cards_title)
    private String name;
    @BindText(R.id.cards_desc)
    private String content;
    @BindAsyncImgUrl(R.id.cards_cover)
    private String coverurl;
    private String time;
    private String deadline;
    private List<CardPropertys> propertys;
    private List<Shop> shopsava;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<CardPropertys> getPropertys() {
        return propertys;
    }

    public void setPropertys(List<CardPropertys> propertys) {
        this.propertys = propertys;
    }

    public List<Shop> getShopsava() {
        return shopsava;
    }

    public void setShopsava(List<Shop> shopsava) {
        this.shopsava = shopsava;
    }
}
