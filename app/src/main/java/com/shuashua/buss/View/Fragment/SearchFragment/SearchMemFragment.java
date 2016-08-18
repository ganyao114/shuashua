package com.shuashua.buss.View.Fragment.SearchFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.Presenter.Base.SearchPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Listener.ISearch;

import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;

/**
 * Created by pc on 16/8/15.
 */
@ContentView(R.layout.fragment_search_mem)
public class SearchMemFragment extends BaseFragmentV4 implements ISearch {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void search(String str) {

    }
}
