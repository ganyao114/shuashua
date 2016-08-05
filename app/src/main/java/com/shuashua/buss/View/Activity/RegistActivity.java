package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.OnBtClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.activity_signup)
public class RegistActivity extends AppCompatActivity implements Runnable{

    @ViewInject(R.id.get_yzm)
    private AppCompatButton getyzm;

    private Thread timethread;

    private int sec = 60;

    private Runnable uirunnable;

    private boolean isAcrun = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        S.ViewUtils.Inject(this);
        uirunnable = new Runnable() {
            @Override
            public void run() {
                if (!isAcrun)
                    return;
                if (sec > 0)
                    getyzm.setText(sec+"秒");
                else
                    getyzm.setText("获取验证码");
            }
        };
    }

    @OnClick({R.id.get_yzm})
    public void click(View view){
        sec = 60;
        timethread = new Thread(this);
        getyzm.setClickable(false);
        timethread.start();
    }



    @Override
    protected void onDestroy() {
        isAcrun = false;
        super.onDestroy();
        S.ViewUtils.Remove(this);
    }

    @Override
    public void run() {
        while (sec >= 0&&isAcrun){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sec--;
            runOnUiThread(uirunnable);
        }
        if (!isAcrun)
            return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getyzm.setClickable(true);
            }
        });
    }

}
