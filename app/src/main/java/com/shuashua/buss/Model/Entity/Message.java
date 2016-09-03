package com.shuashua.buss.Model.Entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by gy on 2016/9/3.
 */
@Table(name = "msg")
public class Message implements Serializable{
    @Column(name = "id",isId = true)
    private String id;
    @Column(name = "mid")
    private String mid;
    @Column(name = "action")
    private String action;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
