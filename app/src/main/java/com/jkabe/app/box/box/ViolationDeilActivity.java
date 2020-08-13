package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.InsureInfo;
import com.jkabe.app.box.bean.Violation;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/8/11
 * @name:TripDeilActivity
 */
public class ViolationDeilActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private Violation travrt;
    private TextView text_address, text_high, text_device, text_ignition, text_extinguish,
            text_travel, text_begin_lat;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vioation);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("违章数据区块详情");
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
        travrt = (Violation) getIntent().getSerializableExtra("travrt");
        if (travrt != null) {
            text_address.setText(travrt.getHashcode() + "");
            text_high.setText(travrt.getBlockid()+"");
            text_device.setText(travrt.getVioAddress()+"");
            text_ignition.setText(travrt.getVioTime()+"");
            text_extinguish.setText(travrt.getVioScore()+"分");
            if (Utility.isEmpty(travrt.getVioOrgan())){
                text_travel.setText("暂无");
            }else{
                text_travel.setText(travrt.getVioOrgan()+"");
            }

            text_begin_lat.setText(travrt.getVioAction()+"");
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
