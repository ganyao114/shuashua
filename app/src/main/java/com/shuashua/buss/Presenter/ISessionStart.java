package com.shuashua.buss.Presenter;

import com.shuashua.buss.Utils.Global;
import com.shuashua.buss.Utils.ResultFilter;

import net.gy.SwiftFrameWork.MVVM.Annotations.HttpSrcMethod;

/**
 * Created by gy939 on 2016/9/13.
 */
public interface ISessionStart {
    @HttpSrcMethod(url = "/test",session = Global.SKEY_UNLOGIN,filters = ResultFilter.class)
    public String start();
}
