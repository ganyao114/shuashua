package com.shuashua.buss.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.shuashua.buss.Model.Entity.MainPListBean;
import com.shuashua.buss.Model.Entity.PositionSearchBean;
import com.shuashua.buss.R;
import com.shuashua.buss.View.Widgets.PopupMenu.MenuHelper;
import com.shuashua.buss.View.Widgets.PopupMenu.OnMenuClick;

import net.gy.SwiftFrameWork.Core.S;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ContentView;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.OnClick;
import net.gy.SwiftFrameWork.IOC.UI.view.viewinject.annotation.ViewInject;
import net.gy.SwiftFrameWork.Reactive.IPublisher;
import net.gy.SwiftFrameWork.Reactive.OnObserver;
import net.gy.SwiftFrameWork.Reactive.OnPublisher;
import net.gy.SwiftFrameWork.Reactive.annotation.RunContext;
import net.gy.SwiftFrameWork.Reactive.entity.RunContextType;
import net.gy.SwiftFrameWork.Reactive.impl.Observer;
import net.gy.SwiftFrameWork.Reactive.impl.Publisher;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_choose_position)
public class ChoosePositionActivity extends BaseActivity implements BDLocationListener, BaiduMap.OnMapStatusChangeListener, TextWatcher, OnGetGeoCoderResultListener, OnMenuClick, TextView.OnEditorActionListener {

    @ViewInject(R.id.main_bdmap)
    private MapView mMapView;
    @ViewInject(R.id.main_pois)
    private RecyclerView plist;
    private BaiduMap mBaiduMap;

    private List<PositionSearchBean> searchBeens;
    private List<MainPListBean> mainPListBeens;

    private MenuHelper<PositionSearchBean> poiMenu;

    private IPublisher searchpublisher;

    /**
     * 定位模式
     */
    private MyLocationConfiguration.LocationMode mCurrentMode;
    /**
     * 定位端
     */
    private LocationClient mLocClient;
    /**
     * 是否是第一次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 定位坐标
     */
    private LatLng locationLatLng;
    /**
     * 定位城市
     */
    private String city;
    /**
     * 反地理编码
     */
    private GeoCoder geoCoder;
    /**
     * 界面上方布局
     */
    @ViewInject(R.id.main_top_RL)
    private RelativeLayout topRL;
    /**
     * 搜索地址输入框
     */
    @ViewInject(R.id.et_search)
    private EditText searchAddress;
    @ViewInject(R.id.search_container)
    private FrameLayout search_container;
    private boolean isFirst = true;


    private LatLng choosePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        S.ViewUtils.Inject(this);
        init();
    }

    private void init(){
        mBaiduMap = mMapView.getMap();
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);


        //地图状态改变相关监听
        mBaiduMap.setOnMapStatusChangeListener(this);

        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        //定位图层显示方式
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;

        /**
         * 设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效
         * customMarker用户自定义定位图标
         * enableDirection是否允许显示方向信息
         * locationMode定位图层显示方式
         */
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, null));


        mBaiduMap.showMapPoi(true);

        //初始化定位
        mLocClient = new LocationClient(this);
        //注册定位监听
        mLocClient.registerLocationListener(this);

        //定位选项
        LocationClientOption option = new LocationClientOption();
        /**
         * coorType - 取值有3个：
         * 返回国测局经纬度坐标系：gcj02
         * 返回百度墨卡托坐标系 ：bd09
         * 返回百度经纬度坐标系 ：bd09ll
         */
        option.setCoorType("bd09ll");
        //设置是否需要地址信息，默认为无地址
        option.setIsNeedAddress(true);
        //设置是否需要返回位置语义化信息，可以在BDLocation.getLocationDescribe()中得到数据，ex:"在天安门附近"， 可以用作地址信息的补充
        option.setIsNeedLocationDescribe(true);
        //设置是否需要返回位置POI信息，可以在BDLocation.getPoiList()中得到数据
        option.setIsNeedLocationPoiList(true);
        /**
         * 设置定位模式
         * Battery_Saving
         * 低功耗模式
         * Device_Sensors
         * 仅设备(Gps)模式
         * Hight_Accuracy
         * 高精度模式
         */
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置是否打开gps进行定位
        option.setOpenGps(true);
        //设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
        option.setScanSpan(1000);

        //设置 LocationClientOption
        mLocClient.setLocOption(option);



        //开始定位
        mLocClient.start();

        //recycleview init
        plist.setLayoutManager(new LinearLayoutManager(this));
    }


    private void result(){
        Intent intent = new Intent();
        setResult(0);
    }

    /**
     * 定位监听
     *
     * @param bdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {

        //如果bdLocation为空或mapView销毁后不再处理新数据接收的位置
        if (bdLocation == null || mBaiduMap == null) {
            return;
        }

        //定位数据
        MyLocationData data = new MyLocationData.Builder()
                //定位精度bdLocation.getRadius()
                .accuracy(bdLocation.getRadius())
                //此处设置开发者获取到的方向信息，顺时针0-360
                .direction(bdLocation.getDirection())
                //经度
                .latitude(bdLocation.getLatitude())
                //纬度
                .longitude(bdLocation.getLongitude())
                //构建
                .build();

        //设置定位数据
        mBaiduMap.setMyLocationData(data);

        //是否是第一次定位
        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(ll, 18);
            mBaiduMap.animateMapStatus(msu);
        }

        //获取坐标，待会用于POI信息点与定位的距离
        locationLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        //获取城市，待会用于POISearch
        city = bdLocation.getCity();

        //文本输入框改变监听，必须在定位完成之后
//        searchAddress.addTextChangedListener(this);
        searchAddress.setOnEditorActionListener(this);

        //创建GeoCoder实例对象
        geoCoder = GeoCoder.newInstance();
        //发起反地理编码请求(经纬度->地址信息)
        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
        //设置反地理编码位置坐标
        reverseGeoCodeOption.location(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
        geoCoder.reverseGeoCode(reverseGeoCodeOption);

        //设置查询结果监听者
        geoCoder.setOnGetGeoCodeResultListener(this);
    }

    //地理编码查询结果回调函数
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }

    //反地理编码查询结果回调函数
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();
        isFirst = false;
        if (poiInfos==null)
            return;
        if (mainPListBeens == null){
            mainPListBeens = mainPoiAdapter(poiInfos);
            S.ViewUtils.ListBind(plist).setClass(MainPListBean.class).bind(mainPListBeens);
        }else {
            mainPListBeens.clear();
            mainPListBeens.addAll(mainPoiAdapter(poiInfos));
            S.ViewUtils.ListBind(plist).refresh();
        }
    }

    private List<PositionSearchBean> PoiAdapter(List<PoiInfo> poiInfos){
        if (poiInfos == null)
            return null;
        List<PositionSearchBean> list = new ArrayList<>();
        for (PoiInfo info:poiInfos){
            PositionSearchBean bean = new PositionSearchBean();
            bean.setValue(info,info.location);
            list.add(bean);
        }
        return list;
    }

    private List<MainPListBean> mainPoiAdapter(List<PoiInfo> poiInfos){
        if (poiInfos == null)
            return null;
        List<MainPListBean> list = new ArrayList<>();
        for (PoiInfo info:poiInfos){
            MainPListBean bean = new MainPListBean();
            bean.setValue(info);
            list.add(bean);
        }
        return list;
    }


    /**
     * 手势操作地图，设置地图状态等操作导致地图状态开始改变
     *
     * @param mapStatus 地图状态改变开始时的地图状态
     */
    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {
    }

    /**
     * 地图状态变化中
     *
     * @param mapStatus 当前地图状态
     */
    @Override
    public void onMapStatusChange(MapStatus mapStatus) {
    }

    /**
     * 地图状态改变结束
     *
     * @param mapStatus 地图状态改变结束后的地图状态
     */
    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        //地图操作的中心点
        if (!isFirst&&geoCoder!=null) {
            mLocClient.stop();
        }
        choosePosition = mapStatus.target;
        if (geoCoder!=null)
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(choosePosition));
    }

    /**
     * 输入框监听---输入之前
     *
     * @param s     字符序列
     * @param start 开始
     * @param count 总计
     * @param after 之后
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    /**
     * 输入框监听---正在输入
     *
     * @param s      字符序列
     * @param start  开始
     * @param before 之前
     * @param count  总计
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /**
     * 输入框监听---输入完毕
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0 || "".equals(s.toString())) {
            if (poiMenu != null)
                poiMenu.dismiss();
        } else {
            //创建PoiSearch实例
            PoiSearch poiSearch = PoiSearch.newInstance();
            //城市内检索
            PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption();
            //关键字
            poiCitySearchOption.keyword(s.toString());
            //城市
            poiCitySearchOption.city(city);
            //设置每页容量，默认为每页10条
            poiCitySearchOption.pageCapacity(10);
            //分页编号
            poiCitySearchOption.pageNum(1);
            poiSearch.searchInCity(poiCitySearchOption);
            //设置poi检索监听者
            poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
                //poi 查询结果回调
                @Override
                public void onGetPoiResult(PoiResult poiResult) {
                    List<PoiInfo> poiInfos = poiResult.getAllPoi();
                    if (searchBeens == null){
                        searchBeens = new ArrayList<>();
                    }
                    searchBeens.clear();
                    if (poiInfos!=null)
                        searchBeens.addAll(PoiAdapter(poiInfos));
                    if (poiMenu == null)
                        poiMenu = new MenuHelper<PositionSearchBean>(ChoosePositionActivity.this,searchAddress,ChoosePositionActivity.this,searchBeens,search_container,PositionSearchBean.class);
                    poiMenu.showMenu();
                }

                //poi 详情查询结果回调
                @Override
                public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                }

                @Override
                public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

                }
            });
        }
    }


    //回退键
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //退出时停止定位
        mLocClient.stop();
        //退出时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);

        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();

        //释放资源
        if (geoCoder != null) {
            geoCoder.destroy();
        }

        mMapView = null;
        S.ViewUtils.Remove(this);
    }

    @Override
    public void onPopupMenuClick(int position, View topview) {

        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(searchBeens.get(position).getLatLng(), 18);
        mBaiduMap.animateMapStatus(msu);
        mBaiduMap.setMapStatus(msu);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ){
            if (searchAddress.getText().toString().length() == 0 || "".equals(searchAddress.getText().toString())) {
                if (poiMenu != null)
                    poiMenu.dismiss();
            } else {
                //创建PoiSearch实例
                if (searchpublisher == null)
                    search();
                searchpublisher.post();
            }
            return true;
        }
        return false;
    }

    private void search(){

        searchpublisher = Publisher.getInstance().create(new OnPublisher() {
            @Override
            @RunContext(RunContextType.NewHandlerThread)
            public void call(final OnObserver observer) {
                PoiSearch poiSearch = PoiSearch.newInstance();
                //城市内检索
                PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption();
                //关键字
                poiCitySearchOption.keyword(searchAddress.getText().toString());
                //城市
                poiCitySearchOption.city(city);
                //设置每页容量，默认为每页10条
                poiCitySearchOption.pageCapacity(50);
                //分页编号
                poiCitySearchOption.pageNum(0);
                if (poiCitySearchOption == null)
                    return;
                poiSearch.searchInCity(poiCitySearchOption);
                //设置poi检索监听者
                poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
                    //poi 查询结果回调
                    @Override
                    public void onGetPoiResult(PoiResult poiResult) {
                        observer.onSuccess(poiResult);
                    }

                    //poi 详情查询结果回调
                    @Override
                    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                    }

                    @Override
                    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

                    }
                });
            }
        }).bind(new Observer<PoiResult>() {
            @Override
            @RunContext(RunContextType.MainThread)
            public void onSuccess(PoiResult poiResult) {
                List<PoiInfo> poiInfos = poiResult.getAllPoi();
                if (poiInfos == null)
                    return;
                if (searchBeens == null){
                    searchBeens = new ArrayList<>();
                    searchBeens.addAll(PoiAdapter(poiInfos));
                    if (poiMenu == null)
                        poiMenu = new MenuHelper<PositionSearchBean>(ChoosePositionActivity.this,searchAddress,ChoosePositionActivity.this,searchBeens,search_container,PositionSearchBean.class);
                }else {
                    searchBeens.clear();
                    searchBeens.addAll(PoiAdapter(poiInfos));
                }
                if (searchBeens.size() != 0)
                    poiMenu.showMenu();
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    @OnClick(R.id.center_point)
    public void onCLick(View view){
        switch (view.getId()){
            case R.id.center_point:
                Toast.makeText(this,"选择"+choosePosition.toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
