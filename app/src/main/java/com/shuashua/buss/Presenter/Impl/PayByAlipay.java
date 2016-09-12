package com.shuashua.buss.Presenter.Impl;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;
import com.shuashua.buss.Presenter.CallBack.OnPayBack;
import com.shuashua.buss.Presenter.IPay;
import net.gy.SwiftFrameWork.Reactive.OnObserver;
import net.gy.SwiftFrameWork.Reactive.OnPublisher;
import net.gy.SwiftFrameWork.Reactive.annotation.RunContext;
import net.gy.SwiftFrameWork.Reactive.entity.RunContextType;
import net.gy.SwiftFrameWork.Reactive.impl.Publisher;

/**
 * Created by gy939 on 2016/9/12.
 */
public class PayByAlipay implements IPay{

    private Activity context;
    private OnPayBack payBack;

    public PayByAlipay(Activity context) {
        this.context = context;
    }

    @Override
    public void setCallBack(OnPayBack payBack) {
        this.payBack = payBack;
    }

    @Override
    public void payAsync(final String orderStr) {
        Publisher.<String>getInstance()
                 .create(new OnPublisher<String>() {
            @RunContext(RunContextType.IO)
            @Override
            public void call(OnObserver<String> observer) {
                    String res = paySync(orderStr);
                    observer.onSuccess(res);
            }
        })
                .bind(new OnObserver<String>() {
            @RunContext(RunContextType.MainThread)
            @Override
            public void onSuccess(String s) {
                if (payBack != null)
                    payBack.payBack(s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onFinished() {

            }
        })
                .post();
    }

    @Override
    public String paySync(String orderStr) {
        // 构造PayTask 对象
        PayTask alipay = new PayTask(context);
        // 调用支付接口，获取支付结果
        String result = alipay.pay(orderStr, true);
        return result;
    }


}
