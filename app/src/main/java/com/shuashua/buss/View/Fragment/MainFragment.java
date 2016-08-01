package com.shuashua.buss.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shuashua.buss.R;
import com.shuashua.buss.View.Widgets.Banner.ADInfo;
import com.shuashua.buss.View.Widgets.Banner.ImageCycleView;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;

/**
 * Created by pc on 16/8/1.
 */
@ContentView(R.layout.fragment_main_layout)
public class MainFragment extends BaseFragmentV4 implements ImageCycleView.ImageCycleViewListener{

    @ViewInject(R.id.main_cycle_view)
    private ImageCycleView cycleView;
    @ViewInject(R.id.mainfrag_cards)
    private RecyclerView cardslist_view;

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
    public void displayImage(String imageURL, ImageView imageView) {
        S.loadImg(getContext()).ShowImg(imageURL,imageView);
    }

    @Override
    public void onImageClick(ADInfo info, int postion, View imageView) {

    }
}
