package com.shuashua.buss.View.Widgets.RateView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 16/8/4.
 */
public class RateView extends ViewGroup implements View.OnClickListener{

    private List<RateBean> list;
    private int mLineSpacing = 30;
    private int mTagSpacing = 30;
    private OnTabItemClickListener<RateBean> onTabItemClickListener;

    public RateView(Context context) {
        super(context);
    }

    public RateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void addViews(){
        for (int i = 0;i < list.size();i++){
            RateItem item = new RateItem(getContext());
            item.showRate(list.get(i));
            item.setOnClickListener(this);
            item.setTag(i);
            addView(item);
        }
    }

    public void setTabItemListener(OnTabItemClickListener listener){
        this.onTabItemClickListener = listener;
    }

    public void showRates(List<RateBean> list){
        this.list = list;
        addViews();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {

            final View childView = getChildAt(i);

            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > width) {
                childLeft = paddingLeft;
                childTop += mLineSpacing + lineHeight;
                lineHeight = childHeight;
            }

            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + mTagSpacing;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wantHeight = 0;
        int wantWidth = resolveSize(0, widthMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            LayoutParams params = childView.getLayoutParams();
            childView.measure(
                    getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, params.width),
                    getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, params.height)
            );

            int childHeight = childView.getHeight();
            int childWidth = childView.getWidth();
            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > wantWidth) {
                childLeft = paddingLeft;
                childTop += mLineSpacing + childHeight;
                lineHeight = childHeight;
            } else {
                childLeft += childWidth + mTagSpacing;
            }


        }
        wantHeight = childTop + lineHeight + paddingBottom;
        setMeasuredDimension(wantWidth, resolveSize(wantHeight, heightMeasureSpec));

    }

    @Override
    public void onClick(View v) {
        if (onTabItemClickListener!=null)
            onTabItemClickListener.OnTabItemClick(list.get((Integer) v.getTag()));
    }
}
