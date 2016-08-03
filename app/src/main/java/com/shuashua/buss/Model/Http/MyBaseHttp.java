package com.shuashua.buss.Model.Http;

import com.shuashua.buss.Model.Entity.RequestHeader;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.MVP.Model.BaseHttpModel;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;

import java.io.Serializable;

/**
 * Created by pc on 16/8/2.
 */
public abstract class MyBaseHttp<T> extends BaseHttpModel<T> implements IHttpDealCallBack{

    protected static RequestHeader header;


}
