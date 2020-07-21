package com.jkabe.app.android.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:充币
 */
public class DrawActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn,text_copy;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_draw);
    }

    @Override
    protected void initView() {
        text_copy = getView(R.id.text_copy);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_copy.setOnClickListener(this);
        title_text_tv.setText("提币");
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
            case R.id.text_copy:

                break;
        }
    }

}
