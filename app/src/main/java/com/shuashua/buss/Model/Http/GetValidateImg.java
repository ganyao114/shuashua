package com.shuashua.buss.Model.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.shuashua.buss.Model.Beans.ImgValiBean;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pc on 16/8/8.
 */
public class GetValidateImg {


    public ImgValiBean getBitmap(String url) {
        InputStream is = null;
        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl
                    .openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            String id = conn.getHeaderField("jsessionid");
            is.close();
            return new ImgValiBean(bitmap,id);
        } catch (IOException ex) {
            ex.printStackTrace();
            try {
                if (is!=null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
