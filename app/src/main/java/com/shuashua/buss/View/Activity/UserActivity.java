package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.shuashua.buss.Presenter.Base.UserPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Window.SelectCityPopupWindow;
import com.shuashua.buss.View.Window.SelectEditPopupWindow;

import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;
import net.gy.SwiftFrameWork.UI.customwidget.autoloadimgview.AutoLoadImgView;
import net.gy.SwiftFrameWork.UI.customwidget.materaldialog.MaterialDialog;

@ContentView(R.layout.activity_user)
@InjectPresenter(UserPresenter.class)
public class UserActivity extends BaseMvpActivity<UserPresenter> implements View.OnClickListener{

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

    private SelectEditPopupWindow menu;
    private SelectCityPopupWindow citymenu;
    private MaterialDialog dialog;
    private EditText input;

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
                    menu = new SelectEditPopupWindow(UserActivity.this,UserActivity.this);
                menu.showAtLocation(getWindow().getDecorView(),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showChangeEdit(@IdRes int id){
        if (dialog == null) {
            dialog = new MaterialDialog(this);
            input = new EditText(this);
            dialog.setContentView(input);
            dialog.setPositiveButton("确认修改", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        switch (id){
            case R.id.btn_change_name:
                dialog.setTitle("修改名字");
                dialog.show();
                break;
            case R.id.btn_choose_email:
                dialog.setTitle("修改邮箱");
                dialog.show();
                break;
            case R.id.btn_choose_photo:
                break;
            case R.id.btn_choose_position:
                if (citymenu == null)
                    citymenu = new SelectCityPopupWindow(this);
                citymenu.showAtLocation(getWindow().getDecorView(),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                menu.dismiss();
                break;
            case R.id.cancelBtn:
                menu.dismiss();
                break;
        }
    }

    @OnClick({R.id.logout_btn})
    public void btn_click(View v){
        switch (v.getId()){
            case R.id.logout_btn:
                getPresent().logout();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        showChangeEdit(v.getId());
    }
}
