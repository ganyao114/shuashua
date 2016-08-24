package com.shuashua.buss.Utils;

import android.content.Context;
import android.content.Intent;

import com.shuashua.buss.View.Activity.HomeActivity;
import com.shuashua.buss.View.Activity.LoginActivity;

/**
 * Created by pc on 16/8/1.
 */
public class Validate {
    public static void loginVali(String string){

    }

    public static void loginValiAndNaviToLogin(Context context){
        Intent intent = new Intent();
        if (Global.user == null)
            intent.setClass(context, LoginActivity.class);
        else
            intent.setClass(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
