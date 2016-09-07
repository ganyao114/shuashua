package com.shuashua.buss.View.Fragment.HomeInner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.Model.Beans.Staff;
import com.shuashua.buss.Model.Entity.Message;
import com.shuashua.buss.Presenter.Event.RefreshMsg;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Window.StaffMngPopupWindow;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.Service.event.annotation.InjectEvent;
import net.gy.SwiftFrameWork.IOC.Service.event.entity.EventThreadType;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

import java.util.List;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.fragment_mainmsg_layout)
public class HMsgFragment extends BaseFragmentV4 implements OnItemClickListener, LoadMoreRecyclerView.LoadMoreListener {

    @ViewInject(R.id.mainmsg_list)
    private LoadMoreRecyclerView msglist;
    private List<Message> msgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        S.Event().regist(this);
        init();
        return view;
    }

    private void init() {
        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(getContext());
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        msglist.setLayoutManager(mFullyLinearLayoutManager);
        msglist.setHasFixedSize(true);
        msglist.setNestedScrollingEnabled(false);
        msglist.setAutoLoadMoreEnable(true);
        msglist.setLoadMoreListener(this);
        msgs = TestModel.getMsg();
        S.ViewUtils.ListBind(msglist)
                .setClass(Message.class)
                .bind(msgs);
        LoadMoreRecyclerView.AutoLoadAdapter adapter = (LoadMoreRecyclerView.AutoLoadAdapter) msglist.getAdapter();
        NomRcViewAdapter rowadapter = (NomRcViewAdapter) adapter.getRowAdapter();
        rowadapter.setOnItemClickListener(this);
    }

    @InjectEvent(type = EventThreadType.MainLoop)
    public void onRefesh(RefreshMsg msg){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        S.Event().unregist(this);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {

    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @Override
    public void onLoadMore() {

    }
}
