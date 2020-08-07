package com.jkabe.app.box.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.LatInfo;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * @author: zt
 * @date: 2020/6/6
 * @name:行车轨迹
 */
public class LocationTravelActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn,text_mileage;
    private MapView mapView;
    private Travrt travrt;
    private AMap aMap;
    private List<LatInfo> list = new ArrayList<>();
    private List<LatLng> readLatLngs;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_location_map);
        mapView = getView(R.id.mMapView);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        BaseApplication.activityTaskManager.putActivity("LocationTravelActivity", this);
    }


    @Override
    protected void initView() {
        text_mileage= getView(R.id.text_mileage);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("行车轨迹");
        aMap = mapView.getMap();
        text_mileage.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        travrt = (Travrt) getIntent().getSerializableExtra("travrt");
        if (travrt != null) {
            query();
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_mileage:
                text_mileage.setVisibility(View.GONE);
                addLatLngBounds();
                break;
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

    public void query() {
        String begin = travrt.getStarttime().substring(0, travrt.getStarttime().length() - 8) + " " + travrt.getStarttime().substring(travrt.getStarttime().length() - 8, travrt.getStarttime().length());
        String end = travrt.getEndtime().substring(0, travrt.getEndtime().length() - 8) + " " + travrt.getEndtime().substring(travrt.getEndtime().length() - 8, travrt.getEndtime().length());

        String sign = "endtime=" + end + "&imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&starttime=" + begin + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("endtime", end);
        params.put("imeicode", SaveUtils.getCar().getImeicode());
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("starttime", begin);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_TRACK_MISS, params, Api.GET_TRACK_TRIP_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_TRACK_TRIP_ID:
                        list = JsonParse.getBannerTrverJson(object);
                        if (list != null && list.size() > 0) {
                            updateView();
                        }
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void updateView() {
        readLatLngs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LatInfo info = list.get(i);
            LatLng latLng = SystemTools.getLatLng(Double.parseDouble(info.getLat()), Double.parseDouble(info.getLng()));
            readLatLngs.add(latLng);
        }
        addPolylineInPlayGround();
        addLatLngBounds();
    }

    /********启动平滑移动******/
    private MovingPointOverlay smoothMarker;
    private Marker marker;
    private void addLatLngBounds() {
        // 读取轨迹点
        List<LatLng> points = readLatLngs;
        // 构建 轨迹的显示区域
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(points.get(0));
        builder.include(points.get(points.size() - 2));
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 50));
        if(smoothMarker == null) {
            marker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_car)).anchor(0.5f,0.5f));
            smoothMarker = new MovingPointOverlay(aMap, marker);
        }
        // 取轨迹点的第一个点 作为 平滑移动的启动
        LatLng drivePoint = points.get(0);
        Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());

        // 设置轨迹点
        smoothMarker.setPoints(subList);
        // 设置平滑移动的总时间  单位  秒
        smoothMarker.setTotalDuration(40);
        // 设置  自定义的InfoWindow 适配器
        aMap.setInfoWindowAdapter(infoWindowAdapter);
        marker.showInfoWindow();
        // 设置移动的监听事件  返回 距终点的距离  单位 米
        smoothMarker.setMoveListener(new MovingPointOverlay.MoveListener() {
            @Override
            public void move(final double distance) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (infoWindowLayout != null && title != null) {
                                if (distance>0){
                                    title.setText("距离终点还有： " + (int) distance + "米");
                                }else{
                                    title.setText("已到终点");
                                    text_mileage.setVisibility(View.VISIBLE);
                                }

                            }
                        }
                    });

                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        // 开始移动
        smoothMarker.startSmoothMove();
    }

    /********添加轨迹线******/
    private void addPolylineInPlayGround() {
        List<LatLng> list = readLatLngs;
        List<Integer> colorList = new ArrayList<>();
        List<BitmapDescriptor> bitmapDescriptors = new ArrayList<>();
        int[] colors = new int[]{Color.argb(255, 0, 255, 0), Color.argb(255, 255, 255, 0), Color.argb(255, 255, 0, 0)};
        List<BitmapDescriptor> textureList = new ArrayList<>();
        textureList.add(BitmapDescriptorFactory.fromResource(R.drawable.custtexture));
        List<Integer> texIndexList = new ArrayList<>();
        texIndexList.add(0);//对应上面的第0个纹理
        texIndexList.add(1);
        texIndexList.add(2);
        Random random = new Random();
        for (int i = 0; i < list.size(); i++) {
            colorList.add(colors[random.nextInt(3)]);
            bitmapDescriptors.add(textureList.get(0));
        }
        aMap.addPolyline(new PolylineOptions().setCustomTexture(BitmapDescriptorFactory.fromResource(R.drawable.custtexture))
                .addAll(list)
                .useGradient(true)
                .width(18));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(list.get(0));
        builder.include(list.get(list.size() - 2));
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));
    }


    AMap.InfoWindowAdapter infoWindowAdapter = new AMap.InfoWindowAdapter(){
        // 如果这个方法返回null，则将会使用默认的信息窗口风格，内容将会调用getInfoContents(Marker)方法获取
        @Override
        public View getInfoWindow(Marker marker) {
            return getInfoWindowView(marker);
        }
        // 这个方法只有在getInfoWindow(Marker)返回null 时才会被调用
        // 定制化的view 做这个信息窗口的内容，如果返回null 将以默认内容渲染
        @Override
        public View getInfoContents(Marker marker) {
            return getInfoWindowView(marker);
        }
    };
    /**
     * 自定义View并且绑定数据方法
     * @param marker 点击的Marker对象
     * @return  返回自定义窗口的视图
     */
    LinearLayout infoWindowLayout;
    TextView title,snippet;
    private View getInfoWindowView(Marker marker) {
        if (infoWindowLayout == null) {
            infoWindowLayout = new LinearLayout(this);
            infoWindowLayout.setOrientation(LinearLayout.VERTICAL);
            title = new TextView(this);
            snippet = new TextView(this);
            title.setText("距离距离展示");
            title.setTextColor(Color.BLACK);
            snippet.setTextColor(Color.BLACK);
            infoWindowLayout.setBackgroundResource(R.drawable.infowindow_bg);
            infoWindowLayout.addView(title);
            infoWindowLayout.addView(snippet);
        }
        return infoWindowLayout;
    }


    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }


}
