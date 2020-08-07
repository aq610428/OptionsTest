package com.jkabe.app.box.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.RuntimeRationale;
import com.jkabe.app.box.weight.SensorEventHelper;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/6
 * @name:定位
 */
public class LocationActivity extends BaseActivity implements LocationSource, AMapLocationListener, AMap.OnMarkerClickListener, PoiSearch.OnPoiSearchListener {
    private TextView title_text_tv, title_left_btn, gps_park_id_two, gps_park_id_navigation,gps_park_id_one;
    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private boolean mFirstFix = false;
    private SensorEventHelper mSensorHelper;
    private Marker mLocMarker;
    private static String BACK_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    protected String[] needPermissions = {
            Permission.ACCESS_COARSE_LOCATION,
            Permission.ACCESS_FINE_LOCATION,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.READ_EXTERNAL_STORAGE,
            Permission.READ_PHONE_STATE,
            BACK_LOCATION_PERMISSION
    };
    private String city;
    private RelativeLayout rl_verison;
    private String name;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_location);
        mapView = getView(R.id.mMapView);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        request();
        BaseApplication.activityTaskManager.putActivity("LocationActivity", this);
    }


    private void request() {
        AndPermission.with(this).runtime().permission(needPermissions)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        init();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(LocationActivity.this, permissions)) {
                            showSettingDialog(LocationActivity.this, permissions);
                        }
                    }
                })
                .start();
    }


    @Override
    protected void initView() {
        gps_park_id_one = getView(R.id.gps_park_id_one);
        gps_park_id_navigation = getView(R.id.gps_park_id_navigation);
        gps_park_id_two = getView(R.id.gps_park_id_two);
        rl_verison = getView(R.id.rl_verison);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        name = getIntent().getStringExtra("name");
        if (Utility.isEmpty(name)) {
            title_text_tv.setText("保险公司");
        } else {
            title_text_tv.setText(name);
        }
        gps_park_id_navigation.setOnClickListener(this);
    }


    public void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
        mSensorHelper = new SensorEventHelper(this);
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
    }


    @Override
    protected void initData() {

    }


    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.addMarker(new MarkerOptions());
        aMap.setOnMarkerClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.gps_park_id_navigation:
                checkLocation();
                break;
        }
    }


    private void addMarker(LatLng latlng) {
        if (mLocMarker != null) {
            return;
        }
        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.navi_map_gps_locked);
        BitmapDescriptor des = BitmapDescriptorFactory.fromBitmap(bMap);
        MarkerOptions options = new MarkerOptions();
        options.icon(des);
        options.anchor(0.5f, 0.5f);
        options.position(latlng);
        mLocMarker = aMap.addMarker(options);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onPause();
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                city = mlocationClient.getLastKnownLocation().getCity();
                LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                if (!mFirstFix) {
                    mFirstFix = true;
                    addMarker(latLng);//添加定位图标
                    mSensorHelper.setCurrentMarker(mLocMarker);//定位图标旋转
                    poiSearch();
                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    String str;
    private void poiSearch() {
        if (Utility.isEmpty(name)) {
            str = "保险公司";
            gps_park_id_one.setText("已选中的保险公司");
        } else {
            str = "保养";
            gps_park_id_one.setText("已选中的保养场所");
        }
        PoiSearch.Query mPoiSearchQuery = new PoiSearch.Query(str, "", city);
        mPoiSearchQuery.requireSubPois(true);   //true 搜索结果包含POI父子关系; false
        mPoiSearchQuery.setPageSize(100);
        mPoiSearchQuery.setPageNum(0);
        PoiSearch poiSearch = new PoiSearch(this, mPoiSearchQuery);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }


    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        if (rcode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null) {
                List<PoiItem> poiItems = result.getPois();
                if (poiItems != null && poiItems.size() > 0) {
                    for (int i = 0; i < poiItems.size(); i++) {
                        PoiItem poiItem = poiItems.get(i);
                        showMarker(poiItem);
                    }
                }

            }
        } else {
            ToastUtil.showToast(rcode + "");
        }
    }


    public void showMarker(PoiItem poiItem) {
        LatLng latLng = new LatLng(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude());
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.title(poiItem.getSnippet()).snippet(poiItem.getTypeDes());
        markerOption.draggable(true);//设置Marker可拖动
        if ("保养".equals(str)) {
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(), R.mipmap.im_poi_insurance_sel)));
        }else{
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(), R.mipmap.icon_bao)));
        }
        markerOption.position(latLng);
        aMap.addMarker(markerOption);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }


    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }


    @Override
    protected void onPause() {
        super.onPause();
        deactivate();
    }


    LatLng latLng;
    private String address;

    @Override
    public boolean onMarkerClick(Marker marker) {
        rl_verison.setVisibility(View.VISIBLE);
        gps_park_id_two.setText(marker.getTitle());
        address = marker.getTitle();
        latLng = marker.getPosition();
        return false;
    }

    private void checkLocation() {
        if (SystemTools.isInstallByread("com.autonavi.minimap") && latLng != null) {
            SystemTools.openGaoDeMap(latLng.latitude, latLng.longitude, address, this);
        } else if (SystemTools.isInstallByread("com.baidu.BaiduMap") && latLng != null) {
            SystemTools.openBaiduMap(latLng.latitude, latLng.longitude, address, this);
        } else {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=" + latLng.latitude + "," + latLng.longitude));
            startActivity(i);
        }
    }

}
