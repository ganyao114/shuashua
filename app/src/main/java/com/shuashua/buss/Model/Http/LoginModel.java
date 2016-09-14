package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Model.Beans.User;
import com.shuashua.buss.Model.ILoginCallBack;
import com.shuashua.buss.Presenter.Login;
import com.shuashua.buss.Utils.Global;
import com.shuashua.buss.Utils.ParseProxy;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;
import java.lang.ref.WeakReference;

/**
 * Created by pc on 16/8/3.
 */
public class LoginModel extends MyBaseHttp<User>implements Login {

    private WeakReference<ILoginCallBack> callRef;

    @Override
    public void initModel() {
        super.initModel();
    }

    public LoginModel(ILoginCallBack loginCallBack) {
        callRef = new WeakReference<ILoginCallBack>(loginCallBack);
    }


    public User userlogin(String name,String pass) {
        addParam("username",name);
        addParam("password",pass);
        super.doHttp();
        return null;
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
    public <T> void onResult(T t) {
        Global.user = (User) t;
        if (callRef!=null&&callRef.get()!=null)
            callRef.get().onLogSuccess();
    }

    @Override
    public void onError(Object object) {
        if (callRef!=null&&callRef.get()!=null)
            callRef.get().onLogFailed();
    }
}
