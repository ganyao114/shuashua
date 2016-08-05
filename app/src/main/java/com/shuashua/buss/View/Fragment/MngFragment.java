package com.shuashua.buss.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.R;
import com.shuashua.buss.View.Fragment.HomeInner.HCampFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HCardsFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMainFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMoreFragment;
import com.shuashua.buss.View.Fragment.MngInner.ShopManagerFragment;

import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.fragment.BaseFragmentV4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.fragment_mng_layout)
public class MngFragment extends BaseFragmentV4{

    @ViewInject(R.id.mng_tab)
    private TabLayout main_tab;
    @ViewInject(R.id.mng_viewPager)
    private ViewPager main_viewpager;


    private List<Fragment> fragments = new ArrayList<>(4);
    private String[] title = new String[]{"店铺", "会员卡", "会员", "订单"};
    //二级fragment
    private ShopManagerFragment shopManagerFragment;
    private HCardsFragment cardsFragment;
    private HCampFragment campFragment;
    private HMoreFragment moreFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null)
            return view;
        super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    private void initView(){
        if (shopManagerFragment == null){
            shopManagerFragment = new ShopManagerFragment();
            fragments.add(shopManagerFragment);
        }
        if (cardsFragment == null){
            cardsFragment = new HCardsFragment();
            fragments.add(cardsFragment);
        }
        if (campFragment == null){
            campFragment = new HCampFragment();
            fragments.add(campFragment);
        }
        if (moreFragment == null){
            moreFragment = new HMoreFragment();
            fragments.add(moreFragment);
        }
        MyAdapter myAdapter = new MyAdapter(getFragmentManager());
        myAdapter.notifyDataSetChanged();
        main_viewpager.setAdapter(myAdapter);
        main_viewpager.setOffscreenPageLimit(4);
        main_tab.setTabMode(TabLayout.MODE_FIXED);
        main_tab.setupWithViewPager(main_viewpager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}