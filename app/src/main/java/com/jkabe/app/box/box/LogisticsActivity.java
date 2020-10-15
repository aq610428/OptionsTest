package com.jkabe.app.box.box;

import android.os.Bundle;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/10/10
 * @name:查看物流
 */
public class LogisticsActivity extends BaseActivity {
    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_logistics);
        BaseApplication.activityTaskManager.putActivity("LogisticsActivity", this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
