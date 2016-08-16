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
        super(context);
    }

    public QRView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QRView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void ShowQR(String str,int width){
        try {

            Bitmap bitmap = QRUtil.createQRCode(str,width);
            setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
