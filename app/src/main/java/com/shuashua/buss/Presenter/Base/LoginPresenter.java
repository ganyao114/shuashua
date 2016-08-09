package com.shuashua.buss.Presenter.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.shuashua.buss.Model.Http.LoginModel;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Activity.LoginActivity;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ActivityOnCreatedListener;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;

/**
 * Created by pc on 16/8/3.
 */
public class LoginPresenter extends Presenter implements ActivityOnCreatedListener{

    private LoginModel lmodel;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {
        lmodel = new LoginModel();
        getActivityInter().setOnCreateListener(this);
    }

    @Override
    public void ActivityOnCreated(Bundle savedInstanceState, final Activity activity) {
        getActivityInter().getView(R.id.btn_login)
                          .setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  EditText name = getActivityInter().getView(R.id.login_name);
                                  EditText pass = getActivityInter().getView(R.id.login_pass);
                                  lmodel.doHttp();
                              }
                          });
    }
}
