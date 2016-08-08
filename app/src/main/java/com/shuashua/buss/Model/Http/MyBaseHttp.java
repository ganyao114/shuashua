package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Model.Entity.RequestHeader;
import com.shuashua.buss.Model.Entity.StringEntry;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.MVP.Model.BaseHttpModel;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;

import java.io.Serializable;

/**
 * Created by pc on 16/8/2.
 */
public abstract class MyBaseHttp<T> extends BaseHttpModel<T> implements IHttpDealCallBack{

    protected static RequestHeader header;

    public MyBaseHttp() {
        initModel();
    }

    @Override
    public void initModel() {
        super.initModel();
        if (header != null) {
            insertHeader();
        }
    }

    private void insertHeader() {
        addHeader(header.getAppkey().getKey(),header.getAppkey().getValue());
        addHeader(header.getUdid().getKey(),header.getUdid().getValue());
        addHeader(header.getOs().getKey(),header.getOs().getValue());
        addHeader(header.getOsversion().getKey(),header.getOsversion().getValue());
        addHeader(header.getInterfacever().getKey(),header.getInterfacever().getValue());
        addHeader(header.getTokenid().getKey(),header.getTokenid().getValue());
        addHeader(header.getUserid().getKey(),header.getUserid().getValue());
        addHeader(header.getUdid().getKey(),header.getUdid().getValue());
    }

    private void initHeader(){
        header = new RequestHeader();
        header.setAppkey(new StringEntry());
    }

}
