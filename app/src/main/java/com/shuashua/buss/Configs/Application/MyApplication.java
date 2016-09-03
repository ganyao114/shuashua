package com.shuashua.buss.Configs.Application;

import android.app.Application;

import com.shuashua.buss.View.Fragment.HomeFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HCampFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMsgFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMoreFragment;
import com.shuashua.buss.View.Fragment.MngFragment;
import com.shuashua.buss.View.Fragment.MngInner.CardManagerFragment;
import com.shuashua.buss.View.Fragment.MngInner.MemManagerFragment;
import com.shuashua.buss.View.Fragment.MngInner.OrderManagerFragment;
import com.shuashua.buss.View.Fragment.MngInner.ShopManagerFragment;
import com.squareup.leakcanary.LeakCanary;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.Core.cache.ClassType;
import net.gy.SwiftFrameWork.IOC.Core.cache.ReflectCacheControl;
import net.gy.SwiftFrameWork.IOC.Core.parase.AnnotationFactory;
import net.gy.SwiftFrameWork.MVVM.Cache.MvvmCacheControl;
import net.gy.SwiftFrameWork.MVVM.Test.ILogin;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by pc on 16/8/2.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //xutils初始化
        x.Ext.init(this);
        //SwiftFrame初始化
        S.init(this);
        //LeakCanary初始化
        LeakCanary.install(this);
        //Swift IOC预加载
        ReflectCacheControl.getInstance().AddpreLoad(ClassType.ACTIVITY, AnnotationFactory.getAllActivity(this));
        ReflectCacheControl.getInstance().AddpreLoad(ClassType.FRAGMENT,new Class[]{HomeFragment.class, MngFragment.class
                , HCampFragment.class, HMsgFragment.class, HMoreFragment.class, CardManagerFragment.class, MemManagerFragment.class
                , OrderManagerFragment.class, ShopManagerFragment.class});
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReflectCacheControl.getInstance().preLoad(ClassType.FRAGMENT);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReflectCacheControl.getInstance().preLoad(ClassType.FRAGMENT);
            }
        }).start();
        MvvmCacheControl.preLoad(new Class[]{ILogin.class, com.shuashua.buss.Presenter.ILogin.class});

        //极光初始化
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

    }
}
