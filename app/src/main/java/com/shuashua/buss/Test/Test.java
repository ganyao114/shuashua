package com.shuashua.buss.Test;

import android.util.Log;

import com.shuashua.buss.Model.Beans.ResultArea;
import com.shuashua.buss.Presenter.IGetDescBycity;

import net.gy.SwiftFrameWork.MVVM.Impl.HttpProxyFactory;
import net.gy.SwiftFrameWork.MVVM.Interface.ICallBack;

/**
 * Created by gy939 on 2016/9/13.
 */
public class Test implements ICallBack<ResultArea,Throwable>{
    public void test(){
        IGetDescBycity getDescBycity = HttpProxyFactory.With(IGetDescBycity.class).setCallBack(this).establish();
        getDescBycity.getdesces("北京市");
    }

    @Override
    public void onSuccess(ResultArea resultArea) {
        Log.e("gy","size:"+resultArea.getChild().size());
    }

    @Override
    public void onFailed(Throwable throwable) {

    }
}
