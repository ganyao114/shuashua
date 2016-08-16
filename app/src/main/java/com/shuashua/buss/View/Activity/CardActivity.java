package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.shuashua.buss.Presenter.Base.CardPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Widgets.QRView.QRView;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.UI.customwidget.materaldialog.MaterialDialog;

@ContentView(R.layout.activity_card)
@InjectPresenter(CardPresenter.class)
public class CardActivity extends BaseMvpActivity<CardPresenter> {

    private MaterialDialog dialog;
    private QRView qrview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "您只可以修改部分属性", Snackbar.LENGTH_LONG)
                        .setAction("修改", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("XXX会员卡");
    }


    private void showQr(String string){
        if (dialog == null){
            dialog = new MaterialDialog(this);
            dialog.setTitle("本店二维码");
            qrview = new QRView(this);
            qrview.setAdjustViewBounds(true);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            qrview.setLayoutParams(layoutParams);
            dialog.setContentView(qrview);
            dialog.setPositiveButton("知道了", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        qrview.ShowQR(string,500);
        dialog.show();
    }

    @OnClick(R.id.imgbtn_cardqr)
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.imgbtn_cardqr:
                showQr("测试字符串");
                break;
        }
    }


}
