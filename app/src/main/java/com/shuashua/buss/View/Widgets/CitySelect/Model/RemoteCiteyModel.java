package com.shuashua.buss.View.Widgets.CitySelect.Model;

import com.shuashua.buss.Model.Http.MyBaseHttp;
import com.shuashua.buss.View.Widgets.CitySelect.IUpdateView;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Model.net.http.IUploadCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;
import java.lang.ref.WeakReference;

/**
 * Created by pc on 16/8/10.
 */
public class RemoteCiteyModel extends MyBaseHttp{

    private WeakReference<IUpdateView> callRef;

    public RemoteCiteyModel(IUpdateView call) {
        super();
        callRef = new WeakReference<IUpdateView>(call);
    }

    public void getData(String code){
        addParam("parentCode",code);
        doHttp();
    }

    @Override
    public String setUrl() {
        return "/area/byparentcode";
    }

    @Override
    public IHttpDealCallBack setCallBack() {
        return new BaseParse(this);
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
        if (result == null)
            throw new HttpServiceException("无数据");

        return null;
    }


    @Override
    public void onResult(Object o) {

    }

    @Override
    public void onError(Object object) {

    }
}
