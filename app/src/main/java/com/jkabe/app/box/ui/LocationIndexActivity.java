package com.jkabe.app.box.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CarVo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OdbAndLocationVO;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/6/6
 * @name:行车轨迹
 */
public class LocationIndexActivity extends BaseActivity implements AMap.InfoWindowAdapter, NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_line, text_satellite, text_mileage1, title_right_tv;
    private MapView mapView;
    private CarVo carVo;
    private AMap aMap;
    private TextView text_bat, text_temper, text_load, text_speed, text_voltage;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_location_map);
        mapView = getView(R.id.mMapView);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        BaseApplication.activityTaskManager.putActivity("LocationTravelActivity", this);
    }


    @Override
    protected void initView() {
        title_right_tv = getView(R.id.title_right_tv);
        text_bat = getView(R.id.text_bat);
        text_temper = getView(R.id.text_temper);
        text_load = getView(R.id.text_load);
        text_speed = getView(R.id.text_speed);
        text_voltage = getView(R.id.text_voltage);
        text_line = getView(R.id.text_line);
        text_satellite = getView(R.id.text_satellite);
        text_mileage1 = getView(R.id.text_mileage1);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("车辆位置");
        aMap = mapView.getMap();
        aMap.setInfoWindowAdapter(this);
        text_mileage1.setOnClickListener(this);
        text_satellite.setOnClickListener(this);
        text_line.setOnClickListener(this);
        title_right_tv.setOnClickListener(this);
        title_right_tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_map_location, 0, 0, 0);
    }


    @Override
    protected void initData() {
        qury();
        quryDeil();
    }

    private void qury() {
        String sign = "imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_CAR_DEVICE, params, Api.GET_CAR_DEVICE_ID, this);
    }

    private void quryDeil() {
        String sign = "imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.RESET_TOKEN_DEVICE, params, Api.RESET_TOKEN_DEVICE_ID, this);
    }


    private boolean isTraffic, mapType, eage;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_right_tv:
                if (carVo != null) {
                    openMap();
                }
                break;

            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_line:
                if (!isTraffic) {
                    isTraffic = true;
                    aMap.setTrafficEnabled(true);
                    ToastUtil.showToast("已开启实时路况");
                } else {
                    isTraffic = false;
                    aMap.setTrafficEnabled(false);
                    ToastUtil.showToast("已关闭实时路况");
                }

                break;
            case R.id.text_satellite:
                if (!mapType) {
                    mapType = true;
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                    ToastUtil.showToast("已开启卫星地图");
                } else {
                    mapType = false;
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                    ToastUtil.showToast("已关闭卫星地图");
                }

                break;
            case R.id.text_mileage1:
                if (!eage) {
                    eage = true;
                    aMap.setMapType(AMap.MAP_TYPE_NAVI);
                    ToastUtil.showToast("已开启导航地图");
                } else {
                    eage = false;
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                    ToastUtil.showToast("已关闭导航地图");
                }
                break;
        }
    }

    private void openMap() {
        if (SystemTools.isInstallByread("com.autonavi.minimap")){
            SystemTools.openGaoDeMap(Double.parseDouble(carVo.getLocationInfo().getLat()),Double.parseDouble(carVo.getLocationInfo().getLng()),carVo.getLocationInfo().getAddress(),this);
        }else if (SystemTools.isInstallByread("com.baidu.BaiduMap")){
            SystemTools.openBaiduMap(Double.parseDouble(carVo.getLocationInfo().getLat()),Double.parseDouble(carVo.getLocationInfo().getLng()),carVo.getLocationInfo().getAddress(),this);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        render(marker, infoWindow);
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoContent = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        render(marker, infoContent);
        return infoContent;
    }


    public void render(Marker marker, View view) {
        TextView text_device = view.findViewById(R.id.text_device);
        TextView text_sim = view.findViewById(R.id.text_sim);
        TextView text_name = view.findViewById(R.id.text_name);
        TextView text_date = view.findViewById(R.id.text_date);
        TextView text_adress = view.findViewById(R.id.text_adress);
        TextView text_acc = view.findViewById(R.id.text_acc);
        TextView text_km = view.findViewById(R.id.text_km);
        TextView text_gps = view.findViewById(R.id.text_gps);
        TextView text_vh = view.findViewById(R.id.text_vh);


        if (carVo != null) {
            text_device.setText("设备编号：" + SaveUtils.getCar().getImeicode());
            text_sim.setText("流量卡号：" + carVo.getSimcode() + "");
            text_name.setText("车牌号码：" + carVo.getCarcard());
            if (carVo.getLocationInfo().getAcc() == 0) {
                text_acc.setText("acc状态：关闭");
            } else {
                text_acc.setText("acc状态：已开启");
            }
            text_vh.setText("车辆型号：" + carVo.getCartype());
            text_km.setText("车辆里程：" + BigDecimalUtils.div(new BigDecimal(carVo.getLocationInfo().getMileage()), new BigDecimal(1000), 2) + "KM");
            String ss = carVo.getLocationInfo().getSignaltime();
            String ss1 = carVo.getLocationInfo().getGpstime();
            if (!Utility.isEmpty(ss)) {
                String start = ss.substring(0, ss.length() - 8);
                String end = ss.substring(ss.length() - 8, ss.length());


                String start1 = ss1.substring(0, ss1.length() - 8);
                String end2 = ss.substring(ss1.length() - 8, ss1.length());
                text_date.setText("定位时间：" + start + " " + end);
                text_gps.setText("车辆信号：" + start1 + " " + end2);

            }

            text_adress.setText("车辆位置：" + carVo.getLocationInfo().getAddress());
            marker.showInfoWindow();
        }
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_CAR_DEVICE_ID:
                        carVo = JsonParse.getCarVoJson(object);
                        if (carVo != null) {
                            updateMap();
                        }
                        break;
                    case Api.RESET_TOKEN_DEVICE_ID:
                        OdbAndLocationVO vo = JsonParse.buildNonDefaultMapper(object);
                        if (vo != null) {
                            updateView(vo);
                        }
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
    }

    /*****设置车辆状态******/
    private void updateView(OdbAndLocationVO vo) {
        text_bat.setText(Utility.judgeStrState(vo.getLocationdata().getSpeed() + "km/h", "--"));
        text_temper.setText(vo.getObddata().getControl_module_voltage() + "V");
        text_load.setText(vo.getObddata().getLoad_calculation_value() + "%");
        text_speed.setText(vo.getObddata().getEngine_speed() + "rmp");
        text_voltage.setText(vo.getObddata().getCoolant_temperature() + "°c");
    }


    private void updateMap() {
        LatLng latLng = SystemTools.getLatLng(Double.parseDouble(carVo.getLocationInfo().getLat()), Double.parseDouble(carVo.getLocationInfo().getLng()), this);
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.im_device_loc)));
        markerOption.position(latLng);
        Marker marker = aMap.addMarker(markerOption);
        marker.showInfoWindow();
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(Exception e) {

    }
}
