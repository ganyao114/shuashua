package com.shuashua.buss.Presenter;

import com.shuashua.buss.Model.Beans.User;

import net.gy.SwiftFrameWork.MVVM.Annotations.HttpSrcMethod;
import net.gy.SwiftFrameWork.MVVM.Annotations.Param;

/**
 * Created by gy on 2016/9/1.
 */
public interface ILogin {
    @HttpSrcMethod(url = "",session = "beforelogin")
    public User login(@Param("name")String name,@Param("passwd")String passwd);
}
