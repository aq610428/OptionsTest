package com.jkabe.app.box.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:ActivationActivity
 */
public class ActivationActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn,text_key;
    private TextView text_usd;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_activation);
    }

    @Override
    protected void initView() {
        text_usd= getView(R.id.text_usd);
        text_key = getView(R.id.text_key);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_key.setOnClickListener(this);
        title_text_tv.setText("激活挖矿");
        TypefaceUtil tfUtil = new TypefaceUtil(this,"OpenSans-Light.ttf");
        tfUtil.setTypeface(text_usd,false);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_key:
                startActivity(new Intent(this,BindActivity.class));
                break;
        }
    }


    @Override
    protected void initData() {

    }
}
