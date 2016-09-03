package com.shuashua.buss.Presenter.Notify.Utils;

import net.gy.SwiftFrameWork.IOC.Core.impl.IOC;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by gy on 2016/9/3.
 */
public class JPushUtils {
    public void setId(String id, TagAliasCallback mAliasCallback){
        JPushInterface.setAliasAndTags(IOC.getInstance().getApplication(), id, null, mAliasCallback);
    }
}
