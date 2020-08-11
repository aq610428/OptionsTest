package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/8/11
 * @name:TripDeilActivity
 */
public class TripDeilActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private Travrt travrt;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
           setContentView(R.layout.activity_tripdeil);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("行程数据区块详情");
    }

    @Override
    protected void initData() {

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
