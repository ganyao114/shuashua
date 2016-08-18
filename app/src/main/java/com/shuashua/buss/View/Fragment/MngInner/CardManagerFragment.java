package com.shuashua.buss.View.Fragment.MngInner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import com.shuashua.buss.Model.Beans.Cards;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Activity.CardActivity;
import com.shuashua.buss.View.Activity.CreateCardActivity;
import com.shuashua.buss.View.Widgets.PopupMenu.StringMenuHelper;
import com.shuashua.buss.View.Widgets.PopupMenu.OnMenuClick;
import com.shuashua.buss.View.Widgets.RateView.OnTabItemClickListener;
import com.shuashua.buss.View.Widgets.RateView.RateBean;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.adapter.NomRcViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pc on 16/8/2.
 */
@ContentView(R.layout.fragment_cardmng_layout)
public class CardManagerFragment extends BaseFragmentV4 implements OnTabItemClickListener<RateBean>,OnMenuClick,OnItemClickListener,View.OnClickListener {

    @ViewInject(R.id.mng_card_list)
    private LoadMoreRecyclerView card_list;
    @ViewInject(R.id.card_mngfrag_content)
    private FrameLayout content;

    private StringMenuHelper menu1Helper;
    private StringMenuHelper menu2Helper;
    private StringMenuHelper menu3Helper;

    @ViewInject(R.id.menu_cardmng_btn1)
    private ToggleButton menu1;

    private List<Cards> cards;
    private List<String> menuData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        test();
        return view;
    }

    private void init(){

    }

    private void test(){
//        List<RateBean> list = new ArrayList<>();
//        for (int i = 0;i < 20;i++){
//            RateBean item = new RateBean();
//            item.setId(i);
//            item.setName("测试标签");
//            item.setCount(40);
//            list.add(item);
//        }
//        rateView.showRates(list);
//        rateView.setTabItemListener(this);

        menuData = new ArrayList<String>();
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menuData.add("ladfj");
        menu1Helper = new StringMenuHelper(getContext(), menu1, this, menuData, content);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu1Helper.showMenu();
            }
        });

        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(getContext());
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        card_list.setLayoutManager(mFullyLinearLayoutManager);
        card_list.setNestedScrollingEnabled(false);
        card_list.setHasFixedSize(true);
        card_list.setAutoLoadMoreEnable(true);
        cards = TestModel.getCards();
        S.ViewUtils.ListBind(card_list)
                .setLtnImpl(this)
                .bind(cards);
        LoadMoreRecyclerView.AutoLoadAdapter adapter = (LoadMoreRecyclerView.AutoLoadAdapter) card_list.getAdapter();
        NomRcViewAdapter rowadapter = (NomRcViewAdapter) adapter.getRowAdapter();
        rowadapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void OnTabItemClick(RateBean bean) {
        Snackbar.make(view, "click"+bean.getId(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onPopupMenuClick(int position,View topview) {

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(), CardActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @OnClick(R.id.cardmng_newcard_btn)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_newdd:
                Log.e("gy","click"+v.getTag());
                Intent intent = new Intent();
                intent.setClass(getContext(), CardActivity.class);
                String cid = cards.get((Integer) v.getTag()).getId();
                intent.putExtra("cardid",cid);
                startActivity(intent);
                break;
            case R.id.cardmng_newcard_btn:
                Intent newcardintent = new Intent();
                newcardintent.setClass(getContext(), CreateCardActivity.class);
                startActivity(newcardintent);
                break;
        }
    }
}
