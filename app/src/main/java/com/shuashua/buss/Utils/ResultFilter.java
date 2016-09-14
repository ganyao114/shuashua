package com.shuashua.buss.Utils;

import net.gy.SwiftFrameWork.MVVM.Exception.HttpServiceException;
import net.gy.SwiftFrameWork.MVVM.Interface.IFilter;

import org.json.JSONObject;

/**
 * Created by gy939 on 2016/9/13.
 */
public class ResultFilter implements IFilter<String,String>{

    public final static String DATA = "object";
    public final static String CODE = "code";
    public final static String TEXT = "text";

    public final static int CODE_OK = 0;
    public final static int CODE_UNLOGIN = 1;


    @Override
    public String filter(String s) throws Exception {
        JSONObject object = new JSONObject(s);
        int code = object.getInt(CODE);
        if (code != 0){

            throw new HttpServiceException(object.getString(TEXT));
        }
        String content = object.getString(DATA);
        return content;
    }
}
