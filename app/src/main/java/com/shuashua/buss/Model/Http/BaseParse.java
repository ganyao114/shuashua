package com.shuashua.buss.Model.Http;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;

import java.io.Serializable;

/**
 * Created by pc on 16/8/2.
 */
public abstract class BaseParse implements IHttpDealCallBack{
    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        return null;
    }
}
