package com.shuashua.buss.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.shuashua.buss.Model.Http.MyBaseHttp;
import com.shuashua.buss.Model.Http.RegistModel;
import com.shuashua.buss.R;
import com.shuashua.buss.View.IRegistCallback;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.OnBtClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;
import net.gy.SwiftFrameWork.UI.customwidget.materaldialog.MaterialDialog;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.activity_signup)
public class RegistActivity extends AppCompatActivity implements Runnable,IRegistCallback{

    @ViewInject(R.id.get_yzm)
    private AppCompatButton getyzm;
    @ViewInject(R.id.input_tel)
    private EditText phone;
    @ViewInject(R.id.input_validate)
    private EditText validatecode;
    @ViewInject(R.id.input_password)
    private EditText passwd;
    @ViewInject(R.id.input_idcard)
    private EditText idcard;
    @ViewInject(R.id.input_name)
    private EditText username;
    @ViewInject(R.id.tuiguang_name)
    private EditText tuiguang;

    private ProgressDialog progressDialog;

    private RegistModel registModel;


    private Thread timethread;
    private int sec = 60;
    private Runnable uirunnable;
    private boolean isAcrun = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        S.ViewUtils.Inject(this);
        registModel = new RegistModel();
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

    @OnClick({R.id.get_yzm,R.id.btn_signup,R.id.link_login})
    public void click(View view){

        switch (view.getId()){
            case R.id.get_yzm:
                getyzm();
                break;
            case R.id.btn_signup:
                regist();
                break;
            case R.id.link_login:
                toLoginView();
                break;
        }

    }

    private void toLoginView() {
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
    }

    private void regist() {
        if (progressDialog == null)
            progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.regist_progress_title));
        progressDialog.show();

    }

    private void getyzm(){
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
