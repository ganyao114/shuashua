package com.shuashua.buss.View.Widgets.QRView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.google.zxing.WriterException;

/**
 * Created by pc on 16/8/4.
 */
public class QRView extends ImageView{
    public QRView(Context context) {
        this(context,null);
    }

    public QRView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public QRView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void ShowQR(String str){
        try {
            Bitmap bitmap = QRUtil.createQRCode(str,getWidth());
            setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
