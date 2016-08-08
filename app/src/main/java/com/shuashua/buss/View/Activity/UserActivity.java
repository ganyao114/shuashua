package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.shuashua.buss.Presenter.Base.UserPresenter;
import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;
import net.gy.SwiftFrameWork.UI.customwidget.autoloadimgview.AutoLoadImgView;

@ContentView(R.layout.activity_user)
@InjectPresenter(UserPresenter.class)
public class UserActivity extends BaseAppCompactActivity<UserPresenter> {

    @ViewInject(R.id.user_name)
    private TextView name;
    @ViewInject(R.id.user_tel)
    private TextView tel;
    @ViewInject(R.id.user_posistion)
    private TextView position;
    @ViewInject(R.id.user_date)
    private TextView date;
    @ViewInject(R.id.user_icon)
    private AutoLoadImgView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
