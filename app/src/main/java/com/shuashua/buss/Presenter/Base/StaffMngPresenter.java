package com.shuashua.buss.Presenter.Base;

import android.content.Context;

import com.shuashua.buss.Model.Beans.Staff;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;

import java.util.List;

/**
 * Created by gy939 on 2016/9/7.
 */
public class StaffMngPresenter extends Presenter{

    public List<Staff> staffs;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {

    }
}
