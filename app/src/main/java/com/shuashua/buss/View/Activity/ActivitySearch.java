package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.Presenter.Base.SearchPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Fragment.HomeInner.HCampFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HCardsFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMainFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMoreFragment;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchCardFragment;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchMemFragment;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchOrderFragment;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_search)
@InjectPresenter(SearchPresenter.class)
public class ActivitySearch extends BaseMvpActivity<SearchPresenter> {

    @ViewInject(R.id.search_tab)
    private TabLayout search_tab;
    @ViewInject(R.id.search_viewPager)
    private ViewPager search_viewpager;

    private List<Fragment> fragments = new ArrayList<>(4);
    private String[] title = new String[]{"搜会员", "搜会员卡", "搜订单","搜用户"};

    private SearchMemFragment memFragment;
    private SearchCardFragment cardFragment;
    private SearchOrderFragment orderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        if (memFragment == null){
            memFragment = new SearchMemFragment();
            fragments.add(memFragment);
        }
        if (cardFragment == null){
            cardFragment = new SearchCardFragment();
            fragments.add(cardFragment);
        }
        if (orderFragment == null){
            orderFragment = new SearchOrderFragment();
            fragments.add(orderFragment);
        }
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        myAdapter.notifyDataSetChanged();
        search_viewpager.setAdapter(myAdapter);
        search_viewpager.setOffscreenPageLimit(3);
        search_tab.setTabMode(TabLayout.MODE_FIXED);
        search_tab.setupWithViewPager(search_viewpager);
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
