package com.shuashua.buss.Presenter.Base;

import android.content.Context;

import com.shuashua.buss.Model.Entity.CardPropertys;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;

import java.util.List;

/**
 * Created by pc on 16/8/9.
 */
public class CardCreatePresenter extends Presenter{

    public List<CardPropertys> propertysList;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {

    }
}
