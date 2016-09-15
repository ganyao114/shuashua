package com.shuashua.buss.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.shuashua.buss.Model.Beans.ImgValiBean;
import com.shuashua.buss.Model.Beans.LoginBean;
import com.shuashua.buss.Model.Http.GetValidateImg;
import com.shuashua.buss.Model.Http.RegistModel;
import com.shuashua.buss.Model.IRegistCallback;
import com.shuashua.buss.Presenter.IRegist;
import com.shuashua.buss.R;
import com.shuashua.buss.Utils.Global;
import com.shuashua.buss.View.Widgets.ImgCanDel.ImgCanDel;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.Exception.model.net.http.HttpServiceException;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVVM.Impl.HttpProxyFactory;
import net.gy.SwiftFrameWork.MVVM.Interface.ICallBack;
import net.gy.SwiftFrameWork.Reactive.IPublisher;
import net.gy.SwiftFrameWork.Reactive.OnObserver;
import net.gy.SwiftFrameWork.Reactive.OnPublisher;
import net.gy.SwiftFrameWork.Reactive.annotation.RunContext;
import net.gy.SwiftFrameWork.Reactive.entity.RunContextType;
import net.gy.SwiftFrameWork.Reactive.impl.Observer;
import net.gy.SwiftFrameWork.Reactive.impl.Publisher;
import net.gy.SwiftFrameWork.Service.loader.imgloader.strategy.mystrategy.impl.ImageLoader;
import net.gy.SwiftFrameWork.UI.customwidget.materaldialog.MaterialDialog;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.activity_signup)
public class RegistActivity extends BaseActivity implements Runnable,IRegistCallback, ICallBack<String,Throwable> {

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
    @ViewInject(R.id.input_adress)
    private EditText adress;
    @ViewInject(R.id.get_area)
    private AppCompatButton getCity;
    @ViewInject(R.id.input_loginname)
    private EditText loginName;

    private ProgressDialog progressDialog;
    private MaterialDialog valiDia;
    private ImageView codeImg;
    private EditText codeInput;

    private RegistModel registModel;
    private GetValidateImg validateImgModel;

    private IPublisher codeImgPub;
    private ImgValiBean valiBean;


    private Thread timethread;
    private int sec = 60;
    private Runnable uirunnable;
    private boolean isAcrun = true;

    private String url = Global.BASE_URL + "/utils/validate";

    private String citycode;

    //业务接口
    private IRegist register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.themeColor);
        }
        super.onCreate(savedInstanceState);
        S.ViewUtils.Inject(this);
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
        register = HttpProxyFactory.With(IRegist.class).setCallBack(this).establish();
    }

    @OnClick({R.id.get_yzm,R.id.btn_signup,R.id.link_login,R.id.get_area})
    public void click(View view){

        switch (view.getId()){
            case R.id.get_yzm:
                showImgValiDia("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg");
                break;
            case R.id.btn_signup:
                regist(getLoginInfo());
                break;
            case R.id.link_login:
                toLoginView();
                break;
            case R.id.get_area:
                navTo(CityPickerActivity.class);
                break;
        }

    }

    private void toLoginView() {
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
    }

    private void regist(LoginBean bean) {
        if (progressDialog == null)
            progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.regist_progress_title));
        progressDialog.show();
        registModel = new RegistModel(this,bean);
        registModel.doHttp();
    }

    private LoginBean getLoginInfo(){
        LoginBean loginBean = new LoginBean();
        loginBean.setAddress(adress.getText().toString());
        loginBean.setAddressCode(citycode);
        loginBean.setTel(phone.getText().toString());
        loginBean.setRealName(username.getText().toString());
        loginBean.setEmail(null);
        loginBean.setIdcard(idcard.getText().toString());
        loginBean.setPasswd(passwd.getText().toString());
        loginBean.setUsername(loginName.getText().toString());
        loginBean.setPromoter(tuiguang.getText().toString());
        return loginBean;
    }

    private void showImgValiDia(String url){
        codeImgPub.post();
        if (valiDia == null){
            valiDia = new MaterialDialog(this);
            valiDia.setTitle(getString(R.string.valiimg_dialog_title));
            codeImg = new ImageView(this);
//            codeImg.setMaxHeight(128);
            codeImg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            codeImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            codeImg.setAdjustViewBounds(true);
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
    }

    //下载图片验证码
    private void PubValiImg(){
        codeImgPub = Publisher.getInstance().create(new OnPublisher() {
            @Override
            @RunContext(RunContextType.IO)
            public void call(OnObserver observer) {
                ImgValiBean bean = validateImgModel.getBitmap(url);
                valiBean = bean;
                if (bean == null)
                    observer.onError(new HttpServiceException("未能获取到图片"));
                else
                    observer.onSuccess(bean.getBitmap());
            }
        }).bind(new Observer<Bitmap>() {
            @RunContext(RunContextType.MainThread)
            @Override
            public void onSuccess(Bitmap o) {
                codeImg.setImageBitmap(o);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        citycode = data.getStringExtra("city");
        String cityname = data.getStringExtra("cityname");
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 0:
            getCity.setText(cityname);
                break;
            default:
                break;
        }
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
        makeToast("注册成功");
        toLoginView();
    }

    @Override
    public void onRegistFail(String msg) {
        progressDialog.cancel();
        showSnakeBar(msg);
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onFailed(Throwable throwable) {

    }
}
