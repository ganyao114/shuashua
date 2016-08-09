package com.shuashua.buss.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shuashua.buss.Model.Http.GetValidateImg;
import com.shuashua.buss.Model.Http.MyBaseHttp;
import com.shuashua.buss.Model.Http.RegistModel;
import com.shuashua.buss.R;
import com.shuashua.buss.View.IRegistCallback;
import com.squareup.picasso.Picasso;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.OnBtClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;
import net.gy.SwiftFrameWork.Reactive.IPublisher;
import net.gy.SwiftFrameWork.Reactive.OnObserver;
import net.gy.SwiftFrameWork.Reactive.OnPublisher;
import net.gy.SwiftFrameWork.Reactive.annotation.RunContext;
import net.gy.SwiftFrameWork.Reactive.entity.RunContextType;
import net.gy.SwiftFrameWork.Reactive.impl.Observer;
import net.gy.SwiftFrameWork.Reactive.impl.Publisher;
import net.gy.SwiftFrameWork.Reactive.impl.Subscriber;
import net.gy.SwiftFrameWork.Service.loader.imgloader.strategy.mystrategy.impl.ImageLoader;
import net.gy.SwiftFrameWork.UI.customwidget.autoloadimgview.AutoLoadImgView;
import net.gy.SwiftFrameWork.UI.customwidget.materaldialog.MaterialDialog;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.activity_signup)
public class RegistActivity extends BaseActivity implements Runnable,IRegistCallback{

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
    private MaterialDialog valiDia;
    private AutoLoadImgView codeImg;
    private EditText codeInput;

    private RegistModel registModel;
    private GetValidateImg validateImgModel;

    private IPublisher codeImgPub;


    private Thread timethread;
    private int sec = 60;
    private Runnable uirunnable;
    private boolean isAcrun = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        S.ViewUtils.Inject(this);
        registModel = new RegistModel(this);
        validateImgModel = new GetValidateImg();
        PubValiImg();
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
                showImgValiDia("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg");
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
        registModel.doHttp();
    }

    private void showImgValiDia(String url){
        ImageLoader.With(this).removeCache(url);
        if (valiDia == null){
            valiDia = new MaterialDialog(this);
            valiDia.setTitle(getString(R.string.valiimg_dialog_title));
            codeImg = new AutoLoadImgView(this);
            codeImg.setMaxHeight(64);
            LinearLayout content = new LinearLayout(this);
            content.setOrientation(LinearLayout.VERTICAL);
            codeInput = new EditText(this);
            codeInput.setTextColor(Color.BLACK);
            codeInput.setHintTextColor(Color.GRAY);
            codeInput.setHint(getString(R.string.valiimg_dialog_hint));
            content.addView(codeImg);
            content.addView(codeInput);
            valiDia.setContentView(content);
            valiDia.setPositiveButton(getString(R.string.valiimg_dialog_confirm), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getyzm();
                    valiDia.dismiss();
                }
            })     .setNegativeButton(R.string.valiimg_dialog_cancel, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    valiDia.dismiss();
                }
            });
        }
        valiDia.show();
        codeImg.ShowImg(url);
    }

    //下载图片验证码
    private void PubValiImg(){
        codeImgPub = Publisher.getInstance().create(new OnPublisher() {
            @Override
            @RunContext(RunContextType.IO)
            public void call(OnObserver observer) {
                Bitmap bitmap = validateImgModel.getValiImg("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg");
                if (bitmap == null)
                    observer.onError(new HttpServiceException("未能获取到图片"));
                else
                    observer.onSuccess(bitmap);
            }
        }).bind(new Observer<Bitmap>() {
            @RunContext(RunContextType.MainThread)
            @Override
            public void onSuccess(Bitmap o) {
//                showImgValiDia(o);
            }
            @RunContext(RunContextType.MainThread)
            @Override
            public void onError(Throwable throwable) {
                showSnakeBar(throwable.getMessage());
                getyzm.setClickable(true);
            }

            @Override
            public void onFinished() {
            }
        });
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
        ImageLoader.With(this).destory();
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

    @Override
    public void onRegistOk() {
        progressDialog.cancel();
        toLoginView();
    }

    @Override
    public void onRegistFail() {
        progressDialog.cancel();
        showSnakeBar(R.string.regist_fail_msg);
    }
}
