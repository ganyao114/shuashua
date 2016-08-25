package com.shuashua.buss.View.Fragment.SearchFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.shuashua.buss.Presenter.Base.SearchPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Listener.ISearch;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.DividerItemDecoration;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

/**
 * Created by pc on 16/8/15.
 */
@ContentView(R.layout.fragment_search_mem)
public class SearchMemFragment extends BaseFragmentV4<SearchPresenter> implements ISearch, OnItemClickListener {

    @ViewInject(R.id.search_memlist)
    private RecyclerView mem_lsit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        init();
        return view;
    }

    private void init() {
        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(getContext());
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mem_lsit.setLayoutManager(mFullyLinearLayoutManager);
        mem_lsit.setNestedScrollingEnabled(false);
        mem_lsit.setHasFixedSize(true);
        mem_lsit.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        getPresenter().mems = TestModel.getMems();
        S.ViewUtils.ListBind(mem_lsit).bind(getPresenter().mems);
        NomRcViewAdapter adapter = (NomRcViewAdapter) mem_lsit.getAdapter();
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void search(String str) {

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        getPresenter().choose(position);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }
}
