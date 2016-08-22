package com.shuashua.buss.Configs.Application;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("gy","start");
                ReflectCacheControl.getInstance().preLoad(ClassType.ACTIVITY);
                Log.e("gy","end");
            }
        }).start();
    }
}
