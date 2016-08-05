package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Model.Beans.User;
import com.shuashua.buss.Utils.DeCyParse;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.MVP.Model.BaseHttpModel;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;

/**
 * Created by pc on 16/8/3.
 */
public class LoginModel extends BaseHttpModel<User>{

    @Override
    public void initModel() {
        super.initModel();
    }

    public LoginModel() {
    }

    @Override
    public String setUrl() {
        return null;
    }

    @Override
    public IHttpDealCallBack setCallBack() {
        return new DeCyParse(new IHttpDealCallBack() {
            @Override
            public Serializable dealReturn(String result) throws HttpServiceException {
                return null;
            }
        });
    }

    @Override
    public HttpConnectMode setconMode() {
        return HttpConnectMode.Post;
    }

    @Override
    public HttpTheadConfigBean setConfig() {
        return new HttpTheadConfigBean(false,0,"","","");
    }
}
