package com.shuashua.buss.View.Activity;

import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shuashua.buss.Presenter.Base.SearchPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Fragment.HomeInner.HCampFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HCardsFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMainFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMoreFragment;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchCardFragment;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchMemFragment;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchOrderFragment;
import com.shuashua.buss.View.Fragment.SearchFragment.SearchUserFragment;
import com.shuashua.buss.View.Listener.ISearch;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_search)
@InjectPresenter(SearchPresenter.class)
public class ActivitySearch extends BaseMvpActivity<SearchPresenter> implements ViewPager.OnPageChangeListener, TextView.OnEditorActionListener {

    @ViewInject(R.id.search_tab)
    private TabLayout search_tab;
    @ViewInject(R.id.search_viewPager)
    private ViewPager search_viewpager;
    @ViewInject(R.id.et_search)
    private EditText searchBox;

    private List<Fragment> fragments = new ArrayList<>(4);
    private String[] title = new String[]{"搜会员", "搜会员卡", "搜订单","搜用户"};

    private SearchMemFragment memFragment;
    private SearchCardFragment cardFragment;
    private SearchOrderFragment orderFragment;
    private SearchUserFragment userFragment;

    private ISearch cursearch;

    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        Intent intent = getIntent();
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
        if (userFragment == null){
            userFragment = new SearchUserFragment();
            fragments.add(userFragment);
        }
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        myAdapter.notifyDataSetChanged();
        search_viewpager.setAdapter(myAdapter);
        search_viewpager.setOffscreenPageLimit(4);
        search_tab.setTabMode(TabLayout.MODE_FIXED);
        search_tab.setupWithViewPager(search_viewpager);
        search_viewpager.addOnPageChangeListener(this);
        searchBox.setOnEditorActionListener(this);
        cursearch = memFragment;
    }

    private void search(ISearch searchFrag,String str){
        searchFrag.search(str);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                cursearch = memFragment;
                break;
            case 1:
                cursearch = cardFragment;
                break;
            case 2:
                cursearch = orderFragment;
                break;
            case 3:
                cursearch = userFragment;
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ){
            if (cursearch != null)
                cursearch.search(searchBox.getText().toString());
            return true;
        }
        return false;
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
