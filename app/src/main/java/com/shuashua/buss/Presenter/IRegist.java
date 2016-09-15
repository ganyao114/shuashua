package com.shuashua.buss.Presenter;

import com.shuashua.buss.Utils.Global;
import com.shuashua.buss.Utils.ResultFilter;

import net.gy.SwiftFrameWork.MVVM.Annotations.HttpSrcMethod;
import net.gy.SwiftFrameWork.MVVM.Annotations.Param;

/**
 * Created by gy on 2016/9/1.
 */
public interface IRegist {
    @HttpSrcMethod(url = "/store/seller/signup",filters = ResultFilter.class,session = Global.SKEY_UNLOGIN)
    public String regist(@Param("username") String username, @Param("email")String email, @Param("tel")String tel,@Param("realName")String realName);
}
