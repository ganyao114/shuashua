package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Model.Beans.LoginBean;
import com.shuashua.buss.Utils.ParseProxy;
import com.shuashua.buss.View.IRegistCallback;
import com.shuashua.buss.View.Widgets.CitySelect.Model.BaseParse;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;
import net.gy.SwiftFrameWork.utils.MD5;

import java.io.Serializable;
import java.lang.ref.WeakReference;

/**
 * Created by pc on 16/8/8.
 */
public class RegistModel extends MyBaseHttp<String>{

    private WeakReference<IRegistCallback> callbackRef;

    public RegistModel(IRegistCallback callback, LoginBean loginBean) {
        super();
        this.callbackRef = new WeakReference<IRegistCallback>(callback);
        addParam("username",loginBean.getUsername());
        addParam("password", MD5.GetMD5Code(loginBean.getPasswd()));
        addParam("tel",loginBean.getTel());
        addParam("email",loginBean.getEmail());
        addParam("idcard",loginBean.getIdcard());
        addParam("realname",loginBean.getRealName());
        addParam("address",loginBean.getAddress());
        addParam("promoter",loginBean.getPromoter());
        addParam("addressCode",loginBean.getAddressCode());
        addParam("telvalidateCode",loginBean.getTelvali());
    }

    @Override
    public String setUrl() {
        return "/seller/login";
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
        return "login_success";
    }

    @Override
    public void onError(Object object) {
        IRegistCallback callback = callbackRef.get();
        if (callback == null)
            return;
        callback.onRegistFail((String) object);
    }

    @Override
    public <T> void onResult(T t) {
        IRegistCallback callback = callbackRef.get();
        if (callback == null)
            return;
        callback.onRegistOk();
    }
}
