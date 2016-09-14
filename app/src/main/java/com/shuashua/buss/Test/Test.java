package com.shuashua.buss.Test;

import android.util.Log;

import com.shuashua.buss.Model.Beans.ResultArea;
import com.shuashua.buss.Presenter.IGetDescBycity;
import com.shuashua.buss.Presenter.ISessionStart;

import net.gy.SwiftFrameWork.MVVM.Impl.HttpProxyFactory;
import net.gy.SwiftFrameWork.MVVM.Interface.ICallBack;

/**
 * Created by gy939 on 2016/9/13.
 */
public class Test implements ICallBack<String,Throwable>{
    public void test(){
        ISessionStart sessionStart = HttpProxyFactory.With(ISessionStart.class).setCallBack(this).establish();
        sessionStart.start();
    }

    @Override
    public void onSuccess(String s) {
        Log.e("gy",s);
    }

    @Override
    public void onFailed(Throwable throwable) {
        Log.e("gy",throwable.getMessage());
    }
}
