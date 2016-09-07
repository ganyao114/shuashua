package com.shuashua.buss.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.shuashua.buss.R;
import com.shuashua.buss.View.Window.SelectEditPopupWindow;
import com.shuashua.buss.View.Window.SelectShopEditPopupWindow;

public class ShopActivity extends BaseActivity implements View.OnClickListener{

    private SelectShopEditPopupWindow menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menu == null)
                    menu = new SelectShopEditPopupWindow(ShopActivity.this,ShopActivity.this);
                menu.showAtLocation(getWindow().getDecorView(),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_cancel_edit:
                menu.dismiss();
                break;
            case R.id.btn_change_shop_staff:
                navTo(StaffMngActivity.class);
                break;
        }
    }
}
