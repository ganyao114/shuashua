package com.shuashua.buss.Presenter;

import com.shuashua.buss.Model.Beans.DescsBean;

import net.gy.SwiftFrameWork.MVVM.Annotations.Param;

/**
 * Created by gy on 2016/9/2.
 */
public interface IGetDescBycity {
    public DescsBean getdesces(@Param("cityname") String city);
}
