package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shuashua.buss.Presenter.Base.MemListPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Widgets.SideBar.SideLetterBar;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
@InjectPresenter(MemListPresenter.class)
@ContentView(R.layout.activity_mems_search)
public class MemSearchActivity extends BaseMvpActivity<MemListPresenter> {

    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;
    private TextView overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

    }


}
