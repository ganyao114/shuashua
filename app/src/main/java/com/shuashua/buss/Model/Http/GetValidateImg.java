package com.shuashua.buss.Model.Http;

import android.graphics.Bitmap;

import net.gy.SwiftFrameWork.Model.net.http.impl.MyHttpService;

/**
 * Created by pc on 16/8/8.
 */
public class GetValidateImg {

    private MyHttpService httpService;

    public GetValidateImg() {
        httpService = new MyHttpService();
    }

    public Bitmap getValiImg(String url){
        try {
            return httpService.getBitmap(url,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
