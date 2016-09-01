package com.shuashua.buss.View.Widgets.ImgCanDel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.Service.loader.imgloader.strategy.mystrategy.impl.ImageLoader;

/**
 * Created by pc on 16/8/10.
 */
public class ImgCanDel extends ImageView{

    Bitmap cha;

    public ImgCanDel(Context context) {
        super(context);
        init();
    }

    public ImgCanDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImgCanDel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        cha = BitmapFactory.decodeResource(getResources(), R.drawable.chacha);
        cha = ImageLoader.zoomImage(cha,16,16);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(cha,0,0,new Paint());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
