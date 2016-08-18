package com.shuashua.buss.Presenter.Base;

import android.content.Context;
import android.content.Intent;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;

/**
 * Created by pc on 16/8/17.
 */
public class SearchPresenter extends Presenter{

    public final static int ACTION_RESULT_MEMINFO = 1;
    public final static int ACTION_RESULT_CARD = 2;
    public final static int ACTION_RESULT_USER = 3;

    public int FLAG = 0;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {
        Intent intent = getActivityRaw().getIntent();
        FLAG = intent.getIntExtra("FLAG",0);
    }

    public void choose(){
        switch (FLAG){
            case ACTION_RESULT_MEMINFO:
                break;
        }
    }

}
