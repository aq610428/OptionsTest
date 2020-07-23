package com.jkabe.app.android.ui;


import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.jkabe.box.R;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.base.BaseApplication;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.bean.Electronic;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.util.BigDecimalUtils;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.LogUtils;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.ToastUtil;
import com.jkabe.app.android.util.Utility;
import com.jkabe.app.android.weight.ClearEditText;
import com.jkabe.app.android.weight.PreferenceUtils;
import org.json.JSONObject;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/6/6
 * @name:围栏
 */
public class LocationMapActivity extends BaseActivity implements NetWorkListener, AMap.OnMarkerClickListener, GeocodeSearch.OnGeocodeSearchListener {
    private TextView title_text_tv, title_left_btn, text_address;
    private MapView mapView;
    private ClearEditText et_name;
    private TextView text_sumber, text_in, text_out, text_close, text_out1;
    private SeekBar seekBar;
    private Electronic electronic;
    AMap aMap;
    private String address;
    private int radius;
    private String alarmstate = "1";


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_location_elc);
        mapView = getView(R.id.mMapView);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        BaseApplication.activityTaskManager.putActivity("LocationActivity", this);
    }


    @Override
    protected void initView() {
        text_out1 = getView(R.id.text_out1);
        text_in = getView(R.id.text_in);
        text_out = getView(R.id.text_out);
        text_close = getView(R.id.text_close);
        text_sumber = getView(R.id.text_sumber);
        seekBar = getView(R.id.seekBar);
        et_name = getView(R.id.et_name);
        text_address = getView(R.id.text_address);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("电子围栏");
        aMap = mapView.getMap();
        aMap.setOnMarkerClickListener(this);
        text_sumber.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                aMap.clear();
                create(seekBar.getProgress());
            }
        });
        text_in.setOnClickListener(this);
        text_out.setOnClickListener(this);
        text_close.setOnClickListener(this);
        text_out1.setOnClickListener(this);

    }


    /*****获取地址*****/
    GeocodeSearch geocoderSearch;

    private void getAddress(LatLng latLng) {
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latLng.latitude, latLng.longitude), 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }


    @Override
    protected void initData() {
        electronic = (Electronic) getIntent().getSerializableExtra("electronic");
        if (electronic != null) {
            text_address.setText(electronic.getAddress());
            et_name.setText(electronic.getName());
            int alarmstate = electronic.getAlarmstate();
            clealview();
            switch (alarmstate) {
                case 0:
                    text_close.setBackgroundResource(R.drawable.shape_license);
                    text_close.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 1:
                    text_in.setBackgroundResource(R.drawable.shape_license);
                    text_in.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 2:
                    text_out.setBackgroundResource(R.drawable.shape_license);
                    text_out.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 3:
                    text_out1.setBackgroundResource(R.drawable.shape_license);
                    text_out1.setTextColor(Color.parseColor("#FFFFFF"));
                    break;

            }
            create(electronic.getRadius());
        } else {
            create(200);
        }


    }


    /******生成电子围栏*****/
    LatLng latLng = null;

    public void create(int radius) {
        aMap.clear();
        CoordinateConverter converter = new CoordinateConverter(this);
        converter.from(CoordinateConverter.CoordType.GPS);
        if (electronic != null) {
            latLng = new LatLng(electronic.getLat(), electronic.getLng());
        } else {
            String lat1 = PreferenceUtils.getPrefString(this, Constants.LAT, "");
            String lon1 = PreferenceUtils.getPrefString(this, Constants.LON, "");
            if (!Utility.isEmpty(lat1)) {
                latLng = new LatLng(Double.parseDouble(lat1), Double.parseDouble(lon1));
            }
        }
        DPoint dPoint = new DPoint();
        dPoint.setLatitude(latLng.latitude);
        dPoint.setLongitude(latLng.longitude);
        this.radius = radius;
        seekBar.setProgress(radius);
        try {
            converter.coord(dPoint);
            DPoint desLatLng = converter.convert();
            latLng = new LatLng(desLatLng.getLatitude(), desLatLng.getLongitude());
            CircleOptions circle = new CircleOptions().
                    center(latLng).
                    radius(radius)
                    .fillColor(Color.parseColor("#50000000"))
                    .strokeColor(Color.parseColor("#3F80F4")).strokeWidth(0.5f);
            aMap.addCircle(circle);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));


            //车辆位置
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.im_device_loc)));
            markerOption.position(latLng);
            aMap.addMarker(markerOption);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getAddress(latLng);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_in:
                clealview();
                text_in.setBackgroundResource(R.drawable.shape_license);
                text_in.setTextColor(Color.parseColor("#FFFFFF"));
                alarmstate = "1";
                break;
            case R.id.text_out:
                clealview();
                text_out.setBackgroundResource(R.drawable.shape_license);
                text_out.setTextColor(Color.parseColor("#FFFFFF"));
                alarmstate = "2";
                break;
            case R.id.text_out1:
                clealview();
                text_out1.setBackgroundResource(R.drawable.shape_license);
                text_out1.setTextColor(Color.parseColor("#FFFFFF"));
                alarmstate = "3";
                break;

            case R.id.text_close:
                clealview();
                text_close.setBackgroundResource(R.drawable.shape_license);
                text_close.setTextColor(Color.parseColor("#FFFFFF"));
                alarmstate = "0";
                break;
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_sumber:
                if (latLng != null) {
                    double lat = BigDecimalUtils.subLastBit(latLng.latitude, 6).doubleValue();
                    double lon = BigDecimalUtils.subLastBit(latLng.longitude, 6).doubleValue();
                    latLng = new LatLng(lat, lon);
                }
                if (electronic != null) {
                    qury();
                } else {
                    save();
                }
                break;
        }
    }

    private void clealview() {
        text_in.setBackgroundResource(R.drawable.shape_license_nor);
        text_in.setTextColor(Color.parseColor("#969FB5"));
        text_out.setBackgroundResource(R.drawable.shape_license_nor);
        text_out.setTextColor(Color.parseColor("#969FB5"));
        text_close.setBackgroundResource(R.drawable.shape_license_nor);
        text_close.setTextColor(Color.parseColor("#969FB5"));
        text_out1.setBackgroundResource(R.drawable.shape_license_nor);
        text_out1.setTextColor(Color.parseColor("#969FB5"));

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

    /*****修改******/
    private void qury() {
        String name = et_name.getText().toString();
        if (Utility.isEmpty(name)) {
            ToastUtil.showToast("围栏名称不能为空");
            return;
        }
        String sign = "address=" + electronic.getAddress() + "&alarmstate=" + alarmstate + "&deviceid=" + SaveUtils.getCar().getId() + "&id=" + electronic.getId()
                + "&lat=" + latLng.latitude + "&lng=" + latLng.longitude + "&name=" + name + "&partnerid=" + Constants.PARTNERID + "&radius=" + radius + Constants.SECREKEY;
        LogUtils.e(sign);
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("address", electronic.getAddress() + "");
        params.put("alarmstate", alarmstate);
        params.put("deviceid", SaveUtils.getCar().getId() + "");
        params.put("id", electronic.getId() + "");
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("lat", latLng.latitude + "");
        params.put("lng", latLng.longitude + "");
        params.put("name", name + "");
        params.put("radius", radius + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        params.put("apptype", Constants.TYPE);
        okHttpModel.get(Api.GET_SAV_DEVICE, params, Api.GET_SAV_DEVICE_ID, this);
    }

    /*****新增******/
    private void save() {
        String name = et_name.getText().toString();
        if (Utility.isEmpty(name)) {
            ToastUtil.showToast("围栏名称不能为空");
            return;
        }
        String sign = "address=" + address + "&alarmstate=" + alarmstate + "&deviceid=" + SaveUtils.getCar().getId()
                + "&lat=" + latLng.latitude + "&lng=" + latLng.longitude + "&name=" + name + "&partnerid=" + Constants.PARTNERID + "&radius=" + radius + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("address", address + "");
        params.put("alarmstate", alarmstate);
        params.put("deviceid", SaveUtils.getCar().getId() + "");
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("lat", latLng.latitude + "");
        params.put("lng", latLng.longitude + "");
        params.put("name", name + "");
        params.put("radius", radius + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        params.put("apptype", Constants.TYPE);
        okHttpModel.get(Api.GET_SAV_DEVICE, params, Api.GET_SAV_DEVICE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_SAV_DEVICE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int rCode) {
        if (rCode == 1000 && regeocodeResult != null) {
            address = regeocodeResult.getRegeocodeAddress().getFormatAddress() + "";
            if (electronic == null) {
                text_address.setText(address);
            }
            LogUtils.e("address=" + address);
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
