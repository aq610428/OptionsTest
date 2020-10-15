package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.EnceInfo;
import com.jkabe.app.box.bean.TripVo;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.box.R;

import java.math.BigDecimal;

/**
 * @author: zt
 * @date: 2020/8/11
 * @name:TripDeilActivity
 */
public class MainDeilActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private EnceInfo travrt;
    private TextView text_address, text_high, text_device, text_ignition, text_extinguish, text_reporting,
            text_travel, text_begin_lat, text_end_lat, text_km_lat, text_oil_lat, text_max, text_average, text_battery, text_accelerate, text_down;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_miandeil);
        BaseApplication.activityTaskManager.putActivity("MainDeilActivity", this);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("维保数据区块详情");
        text_address = getView(R.id.text_address);
        text_high = getView(R.id.text_high);
        text_device = getView(R.id.text_device);
        text_ignition = getView(R.id.text_ignition);
        text_extinguish = getView(R.id.text_extinguish);
        text_reporting = getView(R.id.text_reporting);
        text_travel = getView(R.id.text_travel);
        text_begin_lat = getView(R.id.text_begin_lat);
        text_end_lat = getView(R.id.text_end_lat);
        text_km_lat = getView(R.id.text_km_lat);
        text_oil_lat = getView(R.id.text_oil_lat);
        text_max = getView(R.id.text_max);
        text_average = getView(R.id.text_average);
        text_battery = getView(R.id.text_battery);
        text_accelerate = getView(R.id.text_accelerate);
        text_down = getView(R.id.text_down);

    }

    @Override
    protected void initData() {
        travrt = (EnceInfo) getIntent().getSerializableExtra("travrt");
        if (travrt != null) {
            text_address.setText(travrt.getHashcode() + "");
            text_high.setText(travrt.getBlockid()+"");
            text_device.setText(travrt.getPartnername());
            text_ignition.setText(travrt.getShopname());
            text_extinguish.setText(travrt.getShopaddress());
            text_reporting.setText(travrt.getFaultRemark());
            text_travel.setText(travrt.getIntegral()+"");
            text_begin_lat.setText(travrt.getOrderTime()+"");
            text_end_lat.setText(travrt.getFinishTime());
            String one = SystemTools.mathKmOne(Integer.valueOf(travrt.getMileage())) +"KM";//"KM\n里程"
            if (travrt.getLastmileage()==0){
                text_km_lat.setText(one+"KM");
            }else{
                String one1 = SystemTools.mathKmOne(Integer.valueOf(travrt.getLastmileage())) +"KM";//"KM\n里程"
                text_oil_lat.setText(one1+"");
            }
            text_max.setText(travrt.getRepairPlan()+"");
            text_average.setText(travrt.getRepairPlan()+"");
            text_battery.setText(travrt.getPartsnum()+"");
            text_accelerate.setText(travrt.getNextMileage()+"KM");
            text_down.setText(travrt.getNextTime()+"");
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
        }
    }
}
