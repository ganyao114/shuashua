package com.shuashua.buss.Configs.Application;

import android.app.Application;

import net.gy.SwiftFrameWork.Core.S;

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
    }
}