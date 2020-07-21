package com.jkabe.app.android.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.bigkoo.pickerview.TimePickerView;
import com.jkabe.box.R;
import com.jkabe.app.android.banner.Banner1;
import com.jkabe.app.android.banner.BannerConfig;
import com.jkabe.app.android.banner.Transformer;
import com.jkabe.app.android.banner.listener.OnBannerListener;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.base.BaseApplication;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.bean.ImageInfo;
import com.jkabe.app.android.bean.StoreInfo;
import com.jkabe.app.android.bean.UserInfo;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.util.BigDecimalUtils;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.DateUtils;
import com.jkabe.app.android.util.JsonParse;
import com.jkabe.app.android.util.LogUtils;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.StatusBarUtil;
import com.jkabe.app.android.util.SystemTools;
import com.jkabe.app.android.util.ToastUtil;
import com.jkabe.app.android.util.Utility;
import com.jkabe.app.android.weight.MyLoader1;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/5/28
 * @name:门店详情
 */
public class StoreDeilActivity extends BaseActivity implements NetWorkListener, OnBannerListener {
    private TextView title_left_btn, title_right_btn, text_store, text_distance, text_address, text_mobile, text_date, text_keep, text_wash, text_repair;
    private TextView text_mobile_cal, text_order;
    private UserInfo info;
    private StoreInfo storeInfo;
    private Banner1 banner;
    private List<ImageInfo> banners = new ArrayList<>();
    private String project = "保养";


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_store_deil);
        BaseApplication.activityTaskManager.putActivity("StoreActivity", this);
        info = SaveUtils.getSaveInfo();
    }


    @Override
    protected void initView() {
        text_store = getView(R.id.text_store);
        text_distance = getView(R.id.text_distance);
        text_address = getView(R.id.text_address);
        text_mobile = getView(R.id.text_mobile);
        text_date = getView(R.id.text_date);
        text_keep = getView(R.id.text_keep);
        text_wash = getView(R.id.text_wash);
        text_repair = getView(R.id.text_repair);
        text_mobile_cal = getView(R.id.text_mobile_cal);
        text_order = getView(R.id.text_order);
        text_order.setOnClickListener(this);
        banner = getView(R.id.banner);
        title_right_btn = getView(R.id.title_right_btn);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_right_btn.setOnClickListener(this);
        text_keep.setOnClickListener(this);
        text_wash.setOnClickListener(this);
        text_repair.setOnClickListener(this);
        text_date.setOnClickListener(this);
        text_address.setOnClickListener(this);
        text_mobile.setOnClickListener(this);
        text_mobile_cal.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        storeInfo = (StoreInfo) getIntent().getSerializableExtra("storeInfo");
        if (storeInfo != null) {
            text_store.setText(storeInfo.getName() + "");
            if (storeInfo.getDistance() >= 1000) {
                text_distance.setText(BigDecimalUtils.div(new BigDecimal(storeInfo.getDistance()), new BigDecimal(1000), 2) + "km");
            } else {
                text_distance.setText(storeInfo.getDistance() + "m");
            }
            text_address.setText(storeInfo.getAddress());
            text_mobile.setText(storeInfo.getPhone());
            qury();
        }
        initDataTime();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_order:
                checkData();
                break;


            case R.id.text_date:
                pvTime1.show();
                break;
            case R.id.text_address:
                checkLocation();
                break;
            case R.id.text_mobile_cal:
            case R.id.text_mobile:
                SystemTools.callPhone(this, text_mobile.getText().toString());
                break;

            case R.id.title_right_btn:
                showWindow(v);
                break;
            case R.id.text_keep:
                clealView();
                text_keep.setTextColor(Color.parseColor("#3F80F4"));
                text_keep.setBackgroundResource(R.drawable.store_shape_bg);
                project = "保养";
                break;
            case R.id.text_wash:
                clealView();
                text_wash.setTextColor(Color.parseColor("#3F80F4"));
                text_wash.setBackgroundResource(R.drawable.store_shape_bg);
                project = "洗车";
                break;
            case R.id.text_repair:
                clealView();
                text_repair.setTextColor(Color.parseColor("#3F80F4"));
                text_repair.setBackgroundResource(R.drawable.store_shape_bg);
                project = "维修";
                break;
        }
    }


    public void clealView() {
        text_repair.setTextColor(Color.parseColor("#333333"));
        text_wash.setTextColor(Color.parseColor("#333333"));
        text_keep.setTextColor(Color.parseColor("#333333"));
        text_keep.setBackgroundResource(R.drawable.shape_wash);
        text_wash.setBackgroundResource(R.drawable.shape_wash);
        text_repair.setBackgroundResource(R.drawable.shape_wash);
    }


    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtil.setTranslucentStatus(this);
    }


    private void qury() {
        String sign = "partnerid=" + Constants.PARTNERID + "&storeId=" + storeInfo.getId() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("storeId", storeInfo.getId());
        params.put("limit", "10");
        params.put("page", "1");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_IMAGE_VERSION, params, Api.GET_IMAGE_VERSION_ID, this);
    }


    private void bindQury() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + "&storeId=" + storeInfo.getId() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberId", info.getId() + "");
        params.put("storeId", storeInfo.getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_REMOVE_VERSION, params, Api.GET_REMOVE_VERSION_ID, this);
    }

    private void checkData() {
        String date = text_date.getText().toString();
        if (Utility.isEmpty(date)) {
            ToastUtil.showToast("请选择预约时间");
            return;
        }
        if (SaveUtils.getCar() == null || Utility.isEmpty(SaveUtils.getCar().getCarcard())) {
            ToastUtil.showToast("会员还未绑定车辆");
            return;
        }

        if (storeInfo != null) {
            openOrder();
        }
    }


    private void openOrder() {
        String date = text_date.getText().toString();
        String sign ="carcard="+ SaveUtils.getCar().getCarcard()+"&memberId="+ SaveUtils.getSaveInfo().getId()+"&mobile=" + storeInfo.getPhone()+"&orderTime="+date+ "&partnerid=" + Constants.PARTNERID
                +"&project="+project + "&storeId=" + storeInfo.getId() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("carcard", SaveUtils.getCar().getCarcard());
        params.put("memberId", SaveUtils.getSaveInfo().getId()+ "");
        params.put("mobile", storeInfo.getPhone() + "");
        params.put("orderTime", date);
        params.put("partnerid", Constants.PARTNERID);
        params.put("project", project + "");
        params.put("storeId", storeInfo.getId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ORDER_VERSION, params, Api.GET_ORDER_VERSION_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_IMAGE_VERSION_ID:
                        banners = JsonParse.getImageInfoJson(object);
                        if (banners != null && banners.size() > 0) {
                            updateView();
                        } else {
                            banners = new ArrayList<>();
                            banners.add(new ImageInfo());
                            updateView();
                        }
                        break;
                    case Api.GET_REMOVE_VERSION_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                    case Api.GET_ORDER_VERSION_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }


    public void updateView() {
        //设置图片网址或地址的集合
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader1());
        banner.setImages(banners);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        banner.setTitleView(true);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.RIGHT)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    private Calendar startDate, endDate;
    private TimePickerView pvTime1;
    protected void initDataTime() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        int year = selectedDate.get(Calendar.YEAR);
        int month = selectedDate.get(Calendar.MONTH) ;
        int day = selectedDate.get(Calendar.DATE);

        startDate.set(year, month, day);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                text_date.setText(DateUtils.getTyTimes(date) + "");
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.BLUE)
                .setContentSize(18)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }

    public void showWindow(View v) {
        View popupView = getLayoutInflater().inflate(R.layout.layout_popup, null);
        PopupWindow mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupView.findViewById(R.id.rl_popu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.text_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreDeilActivity.this, StoreActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });

        popupView.findViewById(R.id.text_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StoreDeilActivity.this,OrderActivity.class));
                mPopupWindow.dismiss();
            }
        });

        popupView.findViewById(R.id.text_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                mPopupWindow.dismiss();
            }
        });


        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAsDropDown(v);
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
    public void OnBannerClick(int position) {

    }


    private void checkLocation() {
        if (storeInfo != null && !Utility.isEmpty(storeInfo.getLng())) {
            LatLng latLng = new LatLng(storeInfo.getLng(), storeInfo.getLng());
            if (SystemTools.isInstallByread("com.autonavi.minimap") && latLng != null) {
                SystemTools.invokingGD(storeInfo.getAddress(), this);
            } else if (SystemTools.isInstallByread("com.baidu.BaiduMap") && latLng != null) {
                SystemTools.invokingBD(storeInfo.getAddress(), this);
            } else {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=" + latLng.latitude + "," + latLng.longitude));
                startActivity(i);
            }
        }
    }


    public void showDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.title)).setText("温馨提示");
        ((TextView) view.findViewById(R.id.message)).setText("是否确认解绑?");
        ((TextView) view.findViewById(R.id.cancel)).setText("以后再说");
        ((TextView) view.findViewById(R.id.confirm)).setText("立即解绑");
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindQury();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
