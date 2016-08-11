package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Model.Beans.DescBean;
import com.shuashua.buss.View.Widgets.CitySelect.Model.BaseParse;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;

/**
 * Created by pc on 16/8/10.
 */
public class getDescsModel extends MyBaseHttp<DescBean>{
    @Override
    public String setUrl() {
        return null;
    }

    @Override
    public IHttpDealCallBack setCallBack() {
        return new BaseParse(this);
    }

    @Override
    public HttpConnectMode setconMode() {
        return null;
    }

    @Override
    public HttpTheadConfigBean setConfig() {
        return null;
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        return null;
    }
}
