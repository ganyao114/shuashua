package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shuashua.buss.Presenter.Base.CreateShopPresenter;
import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.UI.customwidget.calendar.CalendarView;

@ContentView(R.layout.activity_create_shop)
@InjectPresenter(CreateShopPresenter.class)
public class CreateShopActivity extends BaseMvpActivity<CreateShopPresenter> {

    private CalendarView calendarView;


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

    @OnClick(R.id.cardbtn_choosep)
    public void onclick(View view){
        switch (view.getId()){
            case R.id.cardbtn_choosep:
                navTo(ChoosePositionActivity.class);
                break;
        }
    }

}
