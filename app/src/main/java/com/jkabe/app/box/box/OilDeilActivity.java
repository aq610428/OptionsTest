package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.OilInfo;
import com.jkabe.app.box.bean.Violation;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/8/10
 * @name:行程数据区块
 */
public class OilDeilActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private OilInfo travrt;
    private TextView text_address, text_high, text_device, text_ignition, text_extinguish,
            text_travel, text_begin_lat, text_reporting, text_oil_time;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_oildeil);
    }

    @Override
    protected void initView() {
        text_oil_time = getView(R.id.text_oil_time);
        text_reporting = getView(R.id.text_reporting);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("加油数据区块详情");
        text_address = getView(R.id.text_address);
        text_high = getView(R.id.text_high);
        text_device = getView(R.id.text_device);
        text_ignition = getView(R.id.text_ignition);
        text_extinguish = getView(R.id.text_extinguish);
        text_travel = getView(R.id.text_travel);
        text_begin_lat = getView(R.id.text_begin_lat);
    }

    @Override
    protected void initData() {
        travrt = (OilInfo) getIntent().getSerializableExtra("travrt");
        if (travrt != null) {
            text_address.setText(travrt.getHashcode() + "");
            text_high.setText(travrt.getBlockid() + "");
            text_device.setText(travrt.getOilStationName() + "");
            text_extinguish.setText(travrt.getOilType() + "");
            text_reporting.setText(travrt.getOilModel() + "");
            text_travel.setText(travrt.getOilSize() + "L");
            text_oil_time.setText(travrt.getOilTime() + "");
            text_ignition.setText(travrt.getOilStationAddress() + "");
            text_begin_lat.setText("暂无其它信息");
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
