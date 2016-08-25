package com.shuashua.buss.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuashua.buss.Model.Beans.Mem;
import com.shuashua.buss.Model.Entity.CardPropertys;
import com.shuashua.buss.Presenter.Base.DistributePresenter;
import com.shuashua.buss.Presenter.Base.SearchPresenter;
import com.shuashua.buss.R;
import com.shuashua.buss.Test.TestModel;
import com.shuashua.buss.View.Utils.PhotoEdit;
import com.shuashua.buss.View.Widgets.PopupMenu.OnMenuClick;
import com.shuashua.buss.View.Widgets.PopupMenu.StringMenuHelper;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.Mvp.annotation.InjectPresenter;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.UI.view.recyclerview.HeadFooterAdapter;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_distribute)
@InjectPresenter(DistributePresenter.class)
public class DistributeActivity extends BaseMvpActivity<DistributePresenter> implements OnMenuClick,View.OnFocusChangeListener,View.OnClickListener{

    @ViewInject(R.id.property_list)
    private RecyclerView property_list;
    @ViewInject(R.id.ac_cardsit_content)
    private FrameLayout content;

    private HeadFooterAdapter adapter;

    private View footerView;

    private StringMenuHelper menuHelper;

    private List<String> menuData;

    private int curSelectP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openCameraIntent = new Intent(DistributeActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
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

    @OnClick({R.id.carddist_picmem_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
            case R.id.carddist_picmem_btn:
                Intent intent  = new Intent();
                intent.setClass(this,ActivitySearch.class);
                intent.putExtra(SearchPresenter.ACTION_FLAG,SearchPresenter.ACTION_RESULT_MEMINFO);
                startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
        }else if (requestCode == SearchPresenter.ACTION_RESCODE){
            if (data == null)
                return;
            Object mem = data.getSerializableExtra(SearchPresenter.ACTION_FLAG_STR);
            if (mem != null) {
                getPresent().tarMem = (Mem) mem;
            }
        }
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
