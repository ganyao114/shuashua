package com.shuashua.buss.Presenter;

import com.shuashua.buss.Utils.Global;

import net.gy.SwiftFrameWork.MVVM.Annotations.HttpSrcMethod;

/**
 * Created by gy on 2016/9/2.
 */
public interface ILogout {
    @HttpSrcMethod(url = "",session = Global.SKEY_LOGIN)
    public String logout();
}
