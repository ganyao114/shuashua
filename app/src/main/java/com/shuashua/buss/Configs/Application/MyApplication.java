package com.shuashua.buss.Configs.Application;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.shuashua.buss.View.Fragment.HomeFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HCampFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HCardsFragment;
import com.shuashua.buss.View.Fragment.HomeInner.HMoreFragment;
import com.shuashua.buss.View.Fragment.MainFragment;
import com.shuashua.buss.View.Fragment.MngFragment;
import com.shuashua.buss.View.Fragment.MngInner.CardManagerFragment;
import com.shuashua.buss.View.Fragment.MngInner.MemManagerFragment;
import com.shuashua.buss.View.Fragment.MngInner.OrderManagerFragment;
import com.shuashua.buss.View.Fragment.MngInner.ShopManagerFragment;
import com.squareup.leakcanary.LeakCanary;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.Core.cache.ClassType;
import net.gy.SwiftFrameWork.IOC.Core.cache.ReflectCacheControl;
import net.gy.SwiftFrameWork.IOC.Core.entity.AnnotationPackage;
import net.gy.SwiftFrameWork.IOC.Core.parase.AnnotationFactory;

import org.xutils.x;

/**
 * Created by pc on 16/8/2.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        S.init(this);
        SDKInitializer.initialize(this);
//        LeakCanary.install(this);
        ReflectCacheControl.getInstance().AddpreLoad(ClassType.ACTIVITY, AnnotationFactory.getAllActivity(this));
        ReflectCacheControl.getInstance().AddpreLoad(ClassType.FRAGMENT,new Class[]{HomeFragment.class, MngFragment.class
                , HCampFragment.class, HCardsFragment.class, HMoreFragment.class, CardManagerFragment.class, MemManagerFragment.class
                , OrderManagerFragment.class, ShopManagerFragment.class});
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("gy","start");
                ReflectCacheControl.getInstance().preLoad(ClassType.FRAGMENT);
                Log.e("gy","end");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("gy","start");
                ReflectCacheControl.getInstance().preLoad(ClassType.FRAGMENT);
                Log.e("gy","end");
            }
        }).start();
    }
}
