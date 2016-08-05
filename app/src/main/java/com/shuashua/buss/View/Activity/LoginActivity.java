package com.shuashua.buss.View.Activity;

import android.os.Bundle;

import com.shuashua.buss.Presenter.Base.LoginPresenter;
import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;

/**
 * Created by pc on 16/8/3.
 */
@ContentView(R.layout.activity_login)
@InjectPresenter(LoginPresenter.class)
public class LoginActivity extends BaseAppCompactActivity<LoginPresenter>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
