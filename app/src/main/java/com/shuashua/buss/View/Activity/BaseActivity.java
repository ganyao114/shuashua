package com.shuashua.buss.View.Activity;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pc on 16/8/9.
 */
public class BaseActivity extends AppCompatActivity{
    protected void showSnakeBar(@StringRes int id){
        Snackbar.make(getWindow().getDecorView(),
                getString(id)
                ,Snackbar.LENGTH_LONG).show();
    }


    protected void showSnakeBar(String str){
        Snackbar.make(getWindow().getDecorView(),
                str
                ,Snackbar.LENGTH_LONG).show();
    }
}
