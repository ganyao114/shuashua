package com.shuashua.buss.View.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shuashua.buss.Model.Entity.CardPropertys;
import com.shuashua.buss.Presenter.Base.CreateShopPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.Utils.FileUtil;
import com.shuashua.buss.View.Utils.PhotoEdit;
import com.shuashua.buss.View.Window.SelectPicPopupWindow;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.UI.customwidget.calendar.CalendarView;

import java.io.File;

@ContentView(R.layout.activity_create_shop)
@InjectPresenter(CreateShopPresenter.class)
public class CreateShopActivity extends BaseMvpActivity<CreateShopPresenter> implements View.OnClickListener {

    private CalendarView calendarView;
    private SelectPicPopupWindow menu;

    private String photoname = "uploadtemp.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menu == null)
                    menu = new SelectPicPopupWindow(CreateShopActivity.this,CreateShopActivity.this);
                menu.showAtLocation(getWindow().getDecorView(),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takePhotoBtn:
                menu.dismiss();
                PhotoEdit.takePt(this, photoname);
                break;
            case R.id.pickPhotoBtn:
                menu.dismiss();
                PhotoEdit.PickPt(this, photoname);
                break;
            case R.id.cancelBtn:
                menu.dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PhotoEdit.REQUESTCODE_PICK:
                try {
                    PhotoEdit.zoomPt(this,data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;
            case PhotoEdit.REQUESTCODE_TAKE:
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + photoname);
                PhotoEdit.zoomPt(this, Uri.fromFile(temp));
                break;
            case PhotoEdit.REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setPicToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            FileUtil.saveFile(this, "temphead.jpg", photo);
//            card_cover.setImageBitmap(photo);
            uploadPt();
        }
    }

    private void uploadPt() {

    }
}
