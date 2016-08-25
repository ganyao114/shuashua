package com.shuashua.buss.Presenter.Base;

import android.content.Context;
import android.content.Intent;

import com.shuashua.buss.Model.Beans.Mem;
import com.shuashua.buss.View.Activity.ActivitySearch;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchMemFragment;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;

import java.util.List;

/**
 * Created by pc on 16/8/17.
 */
public class SearchPresenter extends Presenter{

    public final static String ACTION_FLAG = "search_action";

    public final static int ACTION_RESCODE = 100;

    public final static String ACTION_FLAG_STR = "pic_res";

    public final static int ACTION_RESULT_MEMINFO = 1;
    public final static int ACTION_RESULT_CARD = 2;
    public final static int ACTION_RESULT_USER = 3;

    public int FLAG = 0;

    public List<Mem> mems;


    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {
        Intent intent = getActivityRaw().getIntent();
        FLAG = intent.getIntExtra(SearchPresenter.ACTION_FLAG,0);
    }

    public void choose(int position){
        switch (FLAG){
            case ACTION_RESULT_MEMINFO:
                retMem(position);
                break;
        }
    }

    private void retMem(int position) {
        ActivitySearch activitySearch = (ActivitySearch) getContext();
        if (activitySearch.cursearch instanceof SearchMemFragment){
            Intent intent = new Intent();
            intent.putExtra(ACTION_FLAG_STR,mems.get(position));
            activitySearch.setResult(ACTION_RESCODE,intent);
            activitySearch.finish();
        }
    }

}
