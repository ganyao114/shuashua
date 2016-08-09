package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.view.View;

import com.shuashua.buss.Presenter.Base.LoginPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.View.ILoginCallBack;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.activity_login)
@InjectPresenter(LoginPresenter.class)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements ILoginCallBack{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.link_regist,R.id.link_getpass})
    public void btclick(View view){
        switch (view.getId()){
            case R.id.link_regist:
                navTo(RegistActivity.class);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLogSuccess() {

    }

    @Override
    public void onLogFailed() {

    }
}
