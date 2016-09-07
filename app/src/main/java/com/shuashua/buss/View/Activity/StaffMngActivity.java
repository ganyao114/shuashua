package com.shuashua.buss.View.Activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.Model.Beans.Cards;
import com.shuashua.buss.Model.Beans.Staff;
import com.shuashua.buss.Presenter.Base.StaffMngPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Window.SelectShopEditPopupWindow;
import com.shuashua.buss.View.Window.StaffMngPopupWindow;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

@ContentView(R.layout.activity_staff_mng)
@InjectPresenter(StaffMngPresenter.class)
public class StaffMngActivity extends BaseMvpActivity<StaffMngPresenter> implements OnItemClickListener, View.OnClickListener, LoadMoreRecyclerView.LoadMoreListener {

    @ViewInject(R.id.staff_list)
    private LoadMoreRecyclerView staff_list;
    @ViewInject(R.id.staffmng_refresh_layout)
    private SwipeRefreshLayout refreshLayout;

    private StaffMngPopupWindow menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        menu = new StaffMngPopupWindow(this,this);
        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(this);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        staff_list.setLayoutManager(mFullyLinearLayoutManager);
        staff_list.setHasFixedSize(true);
        staff_list.setNestedScrollingEnabled(false);
        staff_list.setAutoLoadMoreEnable(true);
        staff_list.setLoadMoreListener(this);
        getPresent().staffs = TestModel.getStaff();
        S.ViewUtils.ListBind(staff_list)
                .setClass(Staff.class)
                .bind(getPresent().staffs);
        LoadMoreRecyclerView.AutoLoadAdapter adapter = (LoadMoreRecyclerView.AutoLoadAdapter) staff_list.getAdapter();
        NomRcViewAdapter rowadapter = (NomRcViewAdapter) adapter.getRowAdapter();
        rowadapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        menu.showAtLocation(getWindow().getDecorView(),
                Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @OnClick(R.id.add_staff)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_staff:
                navTo(ActivitySearch.class);
                break;
        }
    }


    @Override
    public void onLoadMore() {

    }

}
