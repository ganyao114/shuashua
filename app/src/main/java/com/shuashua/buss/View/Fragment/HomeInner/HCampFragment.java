package com.shuashua.buss.View.Fragment.HomeInner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.Model.Beans.ADListBean;
import com.shuashua.buss.Presenter.Base.HomePresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.fragment_mainad_layout)
public class HCampFragment extends BaseFragmentV4<HomePresenter> implements LoadMoreRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    @ViewInject(R.id.mainad_list)
    private LoadMoreRecyclerView ad_list;
    @ViewInject(R.id.ad_refreshlayout)
    private SwipeRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        init();
        return view;
    }

    private void init(){
        refreshLayout.setOnRefreshListener(this);
        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(getContext());
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        ad_list.setLayoutManager(mFullyLinearLayoutManager);
        ad_list.setHasFixedSize(true);
        ad_list.setNestedScrollingEnabled(false);
        ad_list.setAutoLoadMoreEnable(true);
        ad_list.setLoadMoreListener(this);
        getPresenter().adlist = TestModel.getAds();
        S.ViewUtils.ListBind(ad_list)
                .setClass(ADListBean.class)
                .setLtnImpl(this)
                .bind(getPresenter().adlist);
        LoadMoreRecyclerView.AutoLoadAdapter adapter = (LoadMoreRecyclerView.AutoLoadAdapter) ad_list.getAdapter();
        NomRcViewAdapter rowadapter = (NomRcViewAdapter) adapter.getRowAdapter();
        rowadapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {

    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }
}
