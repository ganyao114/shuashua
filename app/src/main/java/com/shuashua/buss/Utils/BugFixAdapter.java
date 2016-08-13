package com.shuashua.buss.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.CommonAdapter;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.IRcAdapterCallBack;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

import java.util.List;

/**
 * Created by pc on 16/8/13.
 */
public class BugFixAdapter extends RecyclerView.Adapter {

    private RecyclerView.Adapter adapter;

    public BugFixAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapter.onBindViewHolder(holder,getItemCount() - position - 1);
    }


    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }
}
