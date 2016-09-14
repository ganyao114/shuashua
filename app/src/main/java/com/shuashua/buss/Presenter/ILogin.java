package com.shuashua.buss.Presenter;

import com.shuashua.buss.Model.Beans.User;
import com.shuashua.buss.Utils.Global;
import com.shuashua.buss.Utils.ResultFilter;

import net.gy.SwiftFrameWork.MVVM.Annotations.HttpSrcMethod;
import net.gy.SwiftFrameWork.MVVM.Annotations.Param;

/**
 * Created by gy on 2016/9/1.
 */
public interface ILogin {
    @HttpSrcMethod(url = "/store/login",session = Global.SKEY_UNLOGIN,filters = ResultFilter.class)
    public User login(@Param("tel")String name,@Param("password")String passwd);
}
