package com.shuashua.buss.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.activity.BaseAppCompactActivity;

/**
 * Created by pc on 16/8/1.
 */
public abstract class BaseMvpActivity<T extends Presenter> extends BaseAppCompactActivity<T>{

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

    public void navTo(Class<? extends Activity> ac){
        Intent intent = new Intent();
        intent.setClass(this,ac);
        startActivity(intent);
    }

}
