package com.shuashua.buss.Presenter.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.shuashua.buss.Model.Beans.User;
import com.shuashua.buss.Model.ILoginCallBack;
import com.shuashua.buss.Presenter.ILogin;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Activity.HomeActivity;
import com.shuashua.buss.View.Activity.LoginActivity;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ActivityOnCreatedListener;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;
import net.gy.SwiftFrameWork.MVVM.Impl.HttpProxyFactory;
import net.gy.SwiftFrameWork.MVVM.Interface.ICallBack;

/**
 * Created by pc on 16/8/3.
 */
public class LoginPresenter extends Presenter implements ActivityOnCreatedListener,ICallBack<User,Throwable>{

    private ILogin ILogin;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {
        ILogin = HttpProxyFactory.With(ILogin.class).setCallBack(this).establish();
        getActivityInter().setOnCreateListener(this);
    }

    @Override
    public void ActivityOnCreated(Bundle savedInstanceState, final Activity activity) {
        getActivityInter().getView(R.id.btn_login)
                          .setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  LoginActivity ac = (LoginActivity) activity;
                                  ac.progressDialog.show();
                                  getActivityInter().getView(R.id.btn_login).setClickable(false);
                                  EditText name = getActivityInter().getView(R.id.login_name);
                                  EditText pass = getActivityInter().getView(R.id.login_pass);
                                  ILogin.login(name.getText().toString(),pass.getText().toString());
                              }
                          });
    }


    @Override
    public void onSuccess(User user) {
        Log.e("gy",user.toString());
        getActivityRaw().finish();
        navTo(HomeActivity.class);
    }

    @Override
    public void onFailed(Throwable throwable) {
        ILoginCallBack callBack = (ILoginCallBack) getContext();
        callBack.onLogFailed();
    }
}
