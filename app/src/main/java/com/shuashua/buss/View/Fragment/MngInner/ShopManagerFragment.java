package com.shuashua.buss.View.Fragment.MngInner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import com.shuashua.buss.Model.Beans.Shop;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Activity.CreateShopActivity;
import com.shuashua.buss.View.Activity.ShopActivity;
import com.shuashua.buss.View.Widgets.PopupMenu.StringMenuHelper;
import com.shuashua.buss.View.Widgets.PopupMenu.OnMenuClick;
import com.shuashua.buss.View.Widgets.RateView.OnTabItemClickListener;
import com.shuashua.buss.View.Widgets.RateView.RateBean;
import com.shuashua.buss.View.Widgets.RateView.RateView;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pc on 16/8/2.
 */
@ContentView(R.layout.fragment_shopmng_layout)
public class ShopManagerFragment extends BaseFragmentV4 implements OnTabItemClickListener<RateBean>,OnMenuClick,OnItemClickListener {

//    @ViewInject(R.id.rate_view)
    private RateView rateView;
    @ViewInject(R.id.mng_shop_list)
    private LoadMoreRecyclerView shop_list;
    @ViewInject(R.id.shop_mngfrag_content)
    private FrameLayout content;

    private StringMenuHelper menu1Helper;
    private StringMenuHelper menu2Helper;
    private StringMenuHelper menu3Helper;

    @ViewInject(R.id.menu_btn1)
    private ToggleButton menu1;

    private List<Shop> shops;
    private List<String> menuData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        test();
        return view;
    }

    private void init(){

    }

    private void test(){
//        List<RateBean> list = new ArrayList<>();
//        for (int i = 0;i < 20;i++){
//            RateBean item = new RateBean();
//            item.setId(i);
//            item.setName("测试标签");
//            item.setCount(40);
//            list.add(item);
//        }
//        rateView.showRates(list);
//        rateView.setTabItemListener(this);

        menuData = new ArrayList<String>();
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menu1Helper = new StringMenuHelper(getContext(), menu1, this, menuData, content);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu1Helper.showMenu();
            }
        });

        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(getContext());
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        shop_list.setLayoutManager(mFullyLinearLayoutManager);
        shop_list.setNestedScrollingEnabled(false);
        shop_list.setHasFixedSize(true);
        shop_list.setAutoLoadMoreEnable(true);
        shops = TestModel.getShops();
        S.ViewUtils.ListBind(shop_list)
                .setLtnImpl(this)
                .bind(shops);
        LoadMoreRecyclerView.AutoLoadAdapter adapter = (LoadMoreRecyclerView.AutoLoadAdapter) shop_list.getAdapter();
        NomRcViewAdapter rowadapter = (NomRcViewAdapter) adapter.getRowAdapter();
        rowadapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.new_shop)
    public void click(View view){
        Intent intent = new Intent();
        intent.setClass(getContext(), CreateShopActivity.class);
        startActivity(intent);
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

    @Override
    public void onPopupMenuClick(int position,View topview) {

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(), ShopActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }
}
