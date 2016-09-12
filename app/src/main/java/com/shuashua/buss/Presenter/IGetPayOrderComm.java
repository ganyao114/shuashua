package com.shuashua.buss.Presenter;

import com.shuashua.buss.Utils.Global;

import net.gy.SwiftFrameWork.MVVM.Annotations.HttpSrcMethod;

/**
 * Created by gy939 on 2016/9/12.
 */
public interface IGetPayOrderComm {
    @HttpSrcMethod(url = "/",session = Global.SKEY_LOGIN)
    public String getOrderStr(String comm);
}
