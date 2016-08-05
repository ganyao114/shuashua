package com.shuashua.buss.View.Widgets.RateView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuashua.buss.R;
import com.shuashua.buss.Utils.ViewUtils;

/**
 * Created by pc on 16/8/5.
 */
public class RateItem extends TextView{
    public RateItem(Context context) {
        super(context);
        init();
    }

    public RateItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RateItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setClickable(true);
        setBackgroundResource(R.drawable.rate_item_theme);
        setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
        setGravity(Gravity.CENTER);
        setHeight(ViewUtils.dip2px(getContext(),24));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void showRate(RateBean bean){
        setText("  "+bean.getName()+"  "+bean.getCount()+"  ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
