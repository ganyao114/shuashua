package com.shuashua.buss.View.Fragment.HomeInner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shuashua.buss.Model.Beans.ADInfo;
import com.shuashua.buss.Model.Beans.Cards;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Activity.CardActivity;
import com.shuashua.buss.Model.IShowMainCycleView;
import com.shuashua.buss.View.Widgets.Banner.ImageCycleView;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import net.gy.SwiftFrameWork.UI.view.recyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.fragment_homemain_layout)
public class HMainFragment extends BaseFragmentV4 implements ImageCycleView.ImageCycleViewListener
        ,IShowMainCycleView,View.OnClickListener, LoadMoreRecyclerView.LoadMoreListener {

    @ViewInject(R.id.loop_view)
    private ImageCycleView cycleView;
    @ViewInject(R.id.maincards_list)
    private LoadMoreRecyclerView cardslist_view;
    @ViewInject(R.id.main_refresh_layout)
    private SwipeRefreshLayout refreshLayout;

    private boolean isInit = true;
//    private HeadFooterAdapter moreAdapter;
//    private AppCompatButton moreBt;




    private List<Cards> cards;

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        test();
        return view;
    }

    private void test(){
        ArrayList<ADInfo> infos = new ArrayList<>();
        for(int i=0;i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("top-->" + i);
            infos.add(info);
        }
        showCycleView(infos);
        LinearLayoutManager mFullyLinearLayoutManager = new LinearLayoutManager(getContext());
        mFullyLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mFullyLinearLayoutManager.setAutoMeasureEnabled(true);
        cardslist_view.setLayoutManager(mFullyLinearLayoutManager);
        cardslist_view.setHasFixedSize(true);
        cardslist_view.setNestedScrollingEnabled(false);
        cardslist_view.setAutoLoadMoreEnable(true);
        cardslist_view.setLoadMoreListener(this);
        cards = TestModel.getCards();
        S.ViewUtils.ListBind(cardslist_view)
                   .setClass(Cards.class)
                   .setLtnImpl(this)
                   .bind(cards);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void displayImage(String imageURL, ImageView imageView) {
        S.loadImg(getContext()).ShowImg(imageURL,imageView);
    }

    @Override
    public void onImageClick(ADInfo info, int postion, View imageView) {

    }

    @Override
    public void showCycleView(ArrayList<ADInfo> infos) {
        cycleView.setImageResources(infos,this);
    }


    @Override
    public void onPause() {
        super.onPause();
        cycleView.startImageCycle();
    }


    @Override
    public void onResume() {
        super.onResume();
        cycleView.pushImageCycle();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cycleView.pushImageCycle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_more:
                Log.e("gy","click"+v.getTag());
                Intent intent = new Intent();
                intent.setClass(getContext(), CardActivity.class);
                String cid = cards.get((Integer) v.getTag()).getId();
                intent.putExtra("cardid",cid);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLoadMore() {

    }
}
