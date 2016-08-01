package com.shuashua.buss.Configs.Application;

import android.app.Application;

import net.gy.SwiftFrameWork.Core.S;

import org.xutils.x;

/**
 * Created by pc on 16/8/1.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        S.init(this);
        x.Ext.init(this);
        configFrame();
    }

    private void configFrame() {

    }
}
