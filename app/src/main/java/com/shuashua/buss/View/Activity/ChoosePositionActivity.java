package com.shuashua.buss.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shuashua.buss.R;

public class ChoosePositionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_position);
    }


    private void result(){
        Intent intent = new Intent();
        setResult(0);
    }

}
