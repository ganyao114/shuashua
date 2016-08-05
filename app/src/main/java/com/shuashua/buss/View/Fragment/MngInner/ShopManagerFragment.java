package com.shuashua.buss.View.Fragment.MngInner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.R;
import com.shuashua.buss.View.Widgets.RateView.OnTabItemClickListener;
import com.shuashua.buss.View.Widgets.RateView.RateBean;
import com.shuashua.buss.View.Widgets.RateView.RateItem;
import com.shuashua.buss.View.Widgets.RateView.RateView;

import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.Reactive.test.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/8/2.
 */
@ContentView(R.layout.fragment_shopmng_layout)
public class ShopManagerFragment extends BaseFragmentV4 implements OnTabItemClickListener<RateBean>{

    @ViewInject(R.id.rate_view)
    private RateView rateView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        test();
        return view;
    }

    private void test(){
        List<RateBean> list = new ArrayList<>();
        for (int i = 0;i < 20;i++){
            RateBean item = new RateBean();
            item.setId(i);
            item.setName("测试标签");
            item.setCount(40);
            list.add(item);
        }
        rateView.showRates(list);
        rateView.setTabItemListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void OnTabItemClick(RateBean bean) {
        Snackbar.make(view, "click"+bean.getId(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
