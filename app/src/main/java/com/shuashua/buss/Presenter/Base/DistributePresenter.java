package com.shuashua.buss.Presenter.Base;

import android.content.Context;

import com.shuashua.buss.Model.Beans.Mem;
import com.shuashua.buss.Model.Beans.User;
import com.shuashua.buss.Model.Entity.CardPropertys;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;

import java.util.List;

/**
 * Created by pc on 16/8/22.
 */
public class DistributePresenter extends Presenter{

    public List<CardPropertys> propertysList;

    public Mem tarMem;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {

    }
}
