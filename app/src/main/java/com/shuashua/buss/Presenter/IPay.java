package com.shuashua.buss.Presenter;

import com.shuashua.buss.Presenter.CallBack.OnPayBack;

/**
 * Created by gy939 on 2016/9/12.
 */
public interface IPay {
    public void setCallBack(OnPayBack payBack);
    public void payAsync(String orderStr);
    public String paySync(String orderStr);
}
