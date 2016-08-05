package com.shuashua.buss.Model.Http;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;

/**
 * Created by pc on 16/8/8.
 */
public class IdentyCodeModel extends MyBaseHttp<String>{
    @Override
    public String setUrl() {
        return null;
    }

    @Override
    public IHttpDealCallBack setCallBack() {
        return null;
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
