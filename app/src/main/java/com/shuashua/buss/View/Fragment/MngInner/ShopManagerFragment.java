package com.shuashua.buss.View.Fragment.MngInner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;

/**
 * Created by pc on 16/8/2.
 */
@ContentView(R.layout.fragment_shopmng_layout)
public class ShopManagerFragment extends BaseFragmentV4{
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
}
