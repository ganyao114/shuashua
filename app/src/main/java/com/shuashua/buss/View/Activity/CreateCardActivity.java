package com.shuashua.buss.View.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shuashua.buss.Model.Entity.CardPropertys;
import com.shuashua.buss.Presenter.Base.CardCreatePresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.Utils.FileUtil;
import com.shuashua.buss.View.Utils.PhotoEdit;
import com.shuashua.buss.View.Widgets.PopupMenu.StringMenuHelper;
import com.shuashua.buss.View.Widgets.PopupMenu.OnMenuClick;
import com.shuashua.buss.View.Window.SelectPicPopupWindow;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.UI.customwidget.autoloadimgview.AutoLoadImgView;
import net.gy.SwiftFrameWork.UI.view.recyclerview.HeadFooterAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@InjectPresenter(CardCreatePresenter.class)
@ContentView(R.layout.activity_create_card)
public class CreateCardActivity extends BaseMvpActivity<CardCreatePresenter> implements View.OnClickListener, OnMenuClick ,View.OnFocusChangeListener{

    private SelectPicPopupWindow menu;
    private String photoname = "uploadtemp.jpg";
    @ViewInject(R.id.property_list)
    private RecyclerView property_list;
    @ViewInject(R.id.ac_cardcreate_content)
    private FrameLayout content;

    @ViewInject(R.id.card_upload_icon)
    private AutoLoadImgView card_cover;

    private HeadFooterAdapter adapter;

    private View footerView;

    private StringMenuHelper menuHelper;

    private List<String> menuData;

    private int curSelectP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menu == null)
                    menu = new SelectPicPopupWindow(CreateCardActivity.this,CreateCardActivity.this);
                menu.showAtLocation(getWindow().getDecorView(),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initview();
    }

    private void initview(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        property_list.setLayoutManager(linearLayoutManager);
        property_list.setHasFixedSize(true);
        property_list.setNestedScrollingEnabled(false);
        property_list.setItemAnimator(new DefaultItemAnimator());
        getPresent().propertysList = TestModel.getProperty();
        S.ViewUtils.ListBind(property_list).setLtnImpl(this).setClass(CardPropertys.class).bind(getPresent().propertysList);
        adapter = new HeadFooterAdapter(property_list.getAdapter());
        footerView = property_list.inflate(this,R.layout.item_property_add,null);
        footerView.findViewById(R.id.property_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresent().propertysList.add(new CardPropertys());
                property_list.getAdapter().notifyItemInserted(getPresent().propertysList.size() - 1);
                property_list.getAdapter().notifyItemRangeChanged(0,getPresent().propertysList.size());
            }
        });
        adapter.addFooterView(footerView);
        property_list.setAdapter(adapter);
        menuData = new ArrayList<String>();
        menuData.add("折扣特权");
        menuData.add("计时特权");
        menuData.add("计次特权");
        menuData.add("其他");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takePhotoBtn:
                menu.dismiss();
                PhotoEdit.takePt(this,photoname);
                break;
            case R.id.pickPhotoBtn:
                menu.dismiss();
                PhotoEdit.PickPt(this,photoname);
                break;
            case R.id.cancelBtn:
                menu.dismiss();
                break;
            case R.id.property_type:
                curSelectP = (int) v.getTag();
                menuHelper = new StringMenuHelper(this, v, this, menuData, content);
                menuHelper.showMenu();
                break;
            case R.id.property_rm:
                int p = (int) v.getTag();
                getPresent().propertysList.remove(p);
                property_list.getAdapter().notifyItemRemoved(p);
                property_list.getAdapter().notifyItemRangeChanged(0,getPresent().propertysList.size());
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
                PhotoEdit.zoomPt(this,Uri.fromFile(temp));
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
            card_cover.setImageBitmap(photo);
            uploadPt();
        }
    }

    private void uploadPt() {

    }

    @Override
    public void onPopupMenuClick(int position,View topview) {
        TextView textView = (TextView) topview;
        getPresent().propertysList.get(curSelectP).setType(menuData.get(position));
        textView.setText(menuData.get(position)+">");
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        CardPropertys propertys = (CardPropertys) v.getTag(net.gy.SwiftFrameWork.R.id.EntityTag);
        EditText editText = (EditText) v;
        if (!hasFocus){
            String text = editText.getText().toString();
            if (propertys!=null)
                propertys.setName(text);
        }else {
            if (propertys!=null)
                editText.setText(propertys.getName());
        }
    }
}
