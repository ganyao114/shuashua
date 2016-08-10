package com.shuashua.buss.Model.Beans;

import android.graphics.Bitmap;

/**
 * Created by pc on 16/8/9.
 */
public class ImgValiBean {
    private Bitmap bitmap;
    private String id;

    public ImgValiBean(Bitmap bitmap, String id) {
        this.bitmap = bitmap;
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
