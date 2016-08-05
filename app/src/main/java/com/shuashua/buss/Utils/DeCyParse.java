package com.shuashua.buss.Utils;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;

import java.io.Serializable;

/**
 * Created by pc on 16/8/2.
 */
public class DeCyParse implements IHttpDealCallBack{

    private IHttpDealCallBack callBack;

    public DeCyParse(IHttpDealCallBack callBack) {
        this.callBack = callBack;
    }

    public void setCallBack(IHttpDealCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        //解密
        return callBack.dealReturn(result);
    }

}
