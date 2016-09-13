package com.shuashua.buss.Presenter;

import com.shuashua.buss.Model.Beans.DescsBean;
import com.shuashua.buss.Model.Beans.ResultArea;
import com.shuashua.buss.Utils.Global;
import com.shuashua.buss.Utils.ResultFilter;

import net.gy.SwiftFrameWork.MVVM.Annotations.HttpSrcMethod;
import net.gy.SwiftFrameWork.MVVM.Annotations.Param;

/**
 * Created by gy on 2016/9/2.
 */
public interface IGetDescBycity {
    @HttpSrcMethod(url = "/area/getByCityName",session = Global.SKEY_UNLOGIN,filters = ResultFilter.class)
    public ResultArea getdesces(@Param("cityName") String city);
}
