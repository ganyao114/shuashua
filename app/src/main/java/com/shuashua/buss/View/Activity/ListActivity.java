package com.shuashua.buss.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;

import net.gy.SwiftFrameWork.Core.S;

import java.util.List;

public class ListActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.testlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        S.ViewUtils.ListBind(recyclerView).setLtnImpl(this).bind(TestModel.getCards());
    }

    @Override
    public void onClick(View v) {

    }
}
