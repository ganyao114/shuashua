package com.shuashua.buss.View.Fragment.MngInner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.shuashua.buss.Model.Beans.Order;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Activity.CardActivity;
import com.shuashua.buss.View.Activity.OrderActivity;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.DividerItemDecoration;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

import java.util.List;

/**
 * Created by pc on 16/8/15.
 */
@ContentView(R.layout.fragment_ordermng_layout)
public class OrderManagerFragment extends BaseFragmentV4 implements OnItemClickListener {

    @ViewInject(R.id.mng_order_list)
    private LoadMoreRecyclerView order_list;
    @ViewInject(R.id.order_mngfrag_content)
    private FrameLayout content;

    private List<Order> orders;


    @Override
    protected void onLazyLoad() {
        init();
    }

    private void init() {
        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(getContext());
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        order_list.setLayoutManager(mFullyLinearLayoutManager);
        order_list.setNestedScrollingEnabled(false);
        order_list.setHasFixedSize(true);
        order_list.setAutoLoadMoreEnable(true);
        order_list.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        orders = TestModel.getOeder();
        S.ViewUtils.ListBind(order_list).bind(orders);
        LoadMoreRecyclerView.AutoLoadAdapter adapter = (LoadMoreRecyclerView.AutoLoadAdapter) order_list.getAdapter();
        NomRcViewAdapter rowadapter = (NomRcViewAdapter) adapter.getRowAdapter();
        rowadapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(), OrderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }
}
