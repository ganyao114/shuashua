package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Model.Beans.AeraBean;
import com.shuashua.buss.Model.Beans.DescBean;
import com.shuashua.buss.View.Widgets.CitySelect.Model.AreaBase;
import com.shuashua.buss.View.Widgets.CitySelect.Model.BaseParse;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.Model.net.http.entity.HttpConnectMode;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;
import net.gy.SwiftFrameWork.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/8/10.
 */
public class getDescsModel extends MyBaseHttp<List<AeraBean>>{
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
        return HttpConnectMode.Post;
    }

    @Override
    public HttpTheadConfigBean setConfig() {
        return HttpTheadConfigBean.Default();
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        List<AeraBean> list = new ArrayList<>();
        AeraBean bean = new AeraBean();
        bean.setName("全市");
        list.add(bean);
        return null;
    }

    @Override
    public <T> void onResult(T t) {
    }

    @Override
    public void onError(Object object) {
    }
}
