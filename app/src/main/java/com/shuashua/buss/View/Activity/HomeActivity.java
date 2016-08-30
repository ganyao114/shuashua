package com.shuashua.buss.View.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.shuashua.buss.Model.Beans.User;
import com.shuashua.buss.Presenter.Base.CardCreatePresenter;
import com.shuashua.buss.Presenter.Base.HomePresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.Utils.Global;
import com.shuashua.buss.Utils.LeakFix;
import com.shuashua.buss.View.Fragment.HomeFragment;
import com.shuashua.buss.View.Fragment.MngFragment;

import net.gy.SwiftFrameWork.IOC.Core.cache.ReflectCacheControl;
import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;
import net.gy.SwiftFrameWork.Reactive.test.Test;
import net.gy.SwiftFrameWork.UI.customwidget.materaldialog.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/8/3.
 */
@InjectPresenter(HomePresenter.class)
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseMvpActivity<HomePresenter> implements ViewPager.OnPageChangeListener,NavigationView.OnNavigationItemSelectedListener{

    @ViewInject(R.id.navigation_view)
    private NavigationView mNavigationView;
    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.drawerIcon)
    private ImageView drawerIcon;
    @ViewInject(R.id.drawer)
    private DrawerLayout mDrawerLayout;
    @ViewInject(R.id.bar_home)
    private ImageView bar_home;
    @ViewInject(R.id.bar_mng)
    private ImageView bar_mng;
    @ViewInject(R.id.bar_my)
    private ImageView bar_my;
    @ViewInject(R.id.view_pager)
    private ViewPager view_pager;
    @ViewInject(R.id.tabs)
    private TabLayout tabLayout;

    private View header;
    private ImageView photo;
    private TextView name;
    private TextView tel;

    private boolean isOpen = false;
    private List<Fragment> fragmentlist = new ArrayList<>(3);
    private MyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.themeColor);
        }
        super.onCreate(savedInstanceState);
        addFragments();
        initView();
        net.gy.SwiftFrameWork.MVVM.Test.Test test = new net.gy.SwiftFrameWork.MVVM.Test.Test();
        test.test();
        test.getTree();
    }

    private void addFragments(){
        fragmentlist.add(new HomeFragment());
        fragmentlist.add(new MngFragment());
    }

    private void initView(){
        setSupportActionBar(toolbar);
        //去除状态栏文字
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(this);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);
        view_pager.addOnPageChangeListener(this);
        bar_home.setSelected(true);
        view_pager.setCurrentItem(0);
        header = mNavigationView.getHeaderView(0);
        photo = (ImageView) header.findViewById(R.id.nav_photo);
        name = (TextView) header.findViewById(R.id.nav_name);
        tel = (TextView) header.findViewById(R.id.nav_tel);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                isOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navTo(UserActivity.class);
            }
        });
    }

    @OnClick({R.id.bar_home,R.id.bar_mng,R.id.bar_search,R.id.bar_my,R.id.drawerIcon})
    public void Click(View view){
        int id = view.getId();
        switch (id){
            case R.id.drawerIcon:
                if (!isOpen) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    isOpen = true;
                }
                break;
            case R.id.bar_home:
                bar_home.setSelected(true);
                bar_mng.setSelected(false);
                bar_my.setSelected(false);
                view_pager.setCurrentItem(0);
                break;
            case R.id.bar_mng:
                bar_home.setSelected(false);
                bar_mng.setSelected(true);
                bar_my.setSelected(false);
                view_pager.setCurrentItem(1);
                break;
            case R.id.bar_search:
                Intent intent = new Intent();
                intent.setClass(this,ActivitySearch.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LeakFix.fixInputMethodManagerLeak(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                bar_home.setSelected(true);
                bar_mng.setSelected(false);
                bar_my.setSelected(false);
                break;
            case 1:
                bar_home.setSelected(false);
                bar_mng.setSelected(true);
                bar_my.setSelected(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_login:
                navTo(LoginActivity.class);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mDrawerLayout.closeDrawer(mNavigationView);
            isOpen = false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
