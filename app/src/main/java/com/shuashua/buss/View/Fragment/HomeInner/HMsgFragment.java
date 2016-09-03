package com.shuashua.buss.View.Fragment.HomeInner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.Presenter.Event.RefreshMsg;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.Service.event.annotation.InjectEvent;
import net.gy.SwiftFrameWork.IOC.Service.event.entity.EventThreadType;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;

/**
 * Created by pc on 16/8/3.
 */
public class HMsgFragment extends BaseFragmentV4{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        S.Event().regist(this);
        return view;
    }

    @InjectEvent(type = EventThreadType.MainLoop)
    public void onRefesh(RefreshMsg msg){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        S.Event().unregist(this);
    }
}
