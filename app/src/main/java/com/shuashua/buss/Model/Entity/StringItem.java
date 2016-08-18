package com.shuashua.buss.Model.Entity;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindText;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by pc on 16/8/18.
 */

@ListDataSrc(R.layout.item_text)
public class StringItem implements Serializable{
    @BindText(R.id.textView)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
