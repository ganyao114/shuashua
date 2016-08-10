package com.shuashua.buss.View.Widgets.CitySelect.Model;

import com.shuashua.buss.Utils.Validate;

import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.Model.net.http.IHttpDealCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by pc on 16/8/2.
 */
public class BaseParse implements IHttpDealCallBack{

    private IHttpDealCallBack callBack;

    public BaseParse(IHttpDealCallBack callBack) {
        this.callBack = callBack;
    }

    public void setCallBack(IHttpDealCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        String data = null;
        try {
            JSONObject content = new JSONObject(result);
            int code = content.getInt("code");
            if (code != 0)
                throw new HttpServiceException(content.getString("text"));

            data = content.getString("object");

        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return callBack.dealReturn(data);
    }
}
