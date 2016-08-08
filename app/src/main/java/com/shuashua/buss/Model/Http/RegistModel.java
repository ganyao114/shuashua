package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Utils.ParseProxy;
import com.shuashua.buss.View.IRegistCallback;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;
import java.lang.ref.WeakReference;

/**
 * Created by pc on 16/8/8.
 */
public class RegistModel extends MyBaseHttp<String>{

    private WeakReference<IRegistCallback> callbackRef;

    public RegistModel(IRegistCallback callback) {
        super();
        this.callbackRef = new WeakReference<IRegistCallback>(callback);

    }

    @Override
    public String setUrl() {
        return null;
    }

    @Override
    public IHttpDealCallBack setCallBack() {
        return new ParseProxy(this);
    }

    @Override
    public HttpConnectMode setconMode() {
        return HttpConnectMode.Post;
    }

    @Override
    public HttpTheadConfigBean setConfig() {
        return HttpTheadConfigBean.Default();
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        return null;
    }

    @Override
    public void onError(Object object) {
        IRegistCallback callback = callbackRef.get();
        if (callback == null)
            return;
        callback.onRegistFail();
    }

    @Override
    public <T> void onResult(T t) {
        IRegistCallback callback = callbackRef.get();
        if (callback == null)
            return;
        callback.onRegistOk();
    }
}
