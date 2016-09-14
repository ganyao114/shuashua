package com.shuashua.buss.View.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.shuashua.buss.Model.Beans.DescsBean;
import com.shuashua.buss.Model.Beans.ResultArea;
import com.shuashua.buss.Presenter.IGetDescBycity;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Widgets.CityPicker.adapter.CityListAdapter;
import com.shuashua.buss.View.Widgets.CityPicker.adapter.ResultListAdapter;
import com.shuashua.buss.View.Widgets.CityPicker.db.DBManager;
import com.shuashua.buss.View.Widgets.CityPicker.model.City;
import com.shuashua.buss.View.Widgets.CityPicker.model.LocateState;
import com.shuashua.buss.View.Widgets.CityPicker.utils.StringUtils;
import com.shuashua.buss.View.Widgets.CityPicker.utils.ToastUtils;
import com.shuashua.buss.View.Widgets.SideBar.SideLetterBar;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.impl.ListBinder;
import net.gy.SwiftFrameWork.MVVM.Impl.HttpProxyFactory;
import net.gy.SwiftFrameWork.MVVM.Interface.ICallBack;

import java.util.List;

/**
 * author zaaach on 2016/1/26.
 */
public class CityPickerActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener, ICallBack<ResultArea,Throwable> {


    public final static @IdRes int ListViewId = 12312313;

    public final static int RET_TYPE_QX = 0;

    public final static int RET_TYPE_CITY = 1;

    public final static int RET_CODE = 3;

    public static final int REQUEST_CODE_PICK_CITY = 2333;
    public static final String KEY_PICKED_CITY = "picked_city";

    private int pick_type = 0;

    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;

    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private DBManager dbManager;

    private AMapLocationClient mLocationClient;

    private MaterialDialog.Builder Descdialog;
    private ListView descListView;

    private String tarCity;

    private IGetDescBycity getDescBycity;

    private ResultArea descsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initData();
        initView();
        initLocation();

        //简单实现
        getDescBycity = HttpProxyFactory.With(IGetDescBycity.class)
                                        .setViewContent(descListView)
                                        .setCallBack(this)
                                        .establish();
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = StringUtils.extractLocation(city, district);
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
                    } else {
                        //定位失败
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }

    private void initData() {
        dbManager = new DBManager(this);
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        mCityAdapter = new CityListAdapter(this, mAllCities);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                Log.e("onLocateClick", "重新定位...");
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                mLocationClient.startLocation();
            }
        });

        mResultAdapter = new ResultListAdapter(this, null);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);

        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });

        if (Descdialog == null) {
            Descdialog = new MaterialDialog.Builder(this);
            Descdialog.title("选择区县");
            Descdialog.backgroundColor(Color.WHITE);
            Descdialog.titleColor(Color.DKGRAY);
            descListView = new ListView(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            descListView.setLayoutParams(params);
            descListView.setOnItemClickListener(this);
            descListView.setId(ListViewId);
            Descdialog.customView(descListView,false);
        }

        searchBox = (EditText) findViewById(R.id.et_search);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });

        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position).getName());
            }
        });

        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        backBtn = (ImageView) findViewById(R.id.back);

        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    private void back(String city){
        ToastUtils.showToast(this, "选择城市：" + city);
        switch (pick_type){
            case RET_TYPE_CITY:
                Intent intent = new Intent();
                intent.putExtra(KEY_PICKED_CITY,city);
                setResult(RET_CODE,intent);
                finish();
                break;
            case RET_TYPE_QX:
                showDesc(city);
                break;
        }
    }

    private void showDesc(String city){
        getDescBycity.getdesces(city);
        Descdialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_clear:
                searchBox.setText("");
                clearBtn.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                mResultListView.setVisibility(View.GONE);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onSuccess(ResultArea o) {
        descsBean = o;
//        S.ViewUtils.ListBind(descListView).bind(descsBean.getChild());
    }

    @Override
    public void onFailed(Throwable o) {
        showSnakeBar(o.getMessage());
    }
}
