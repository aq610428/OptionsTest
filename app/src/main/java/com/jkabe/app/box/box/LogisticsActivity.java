package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.bean.OrderVo;
import com.jkabe.box.R;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/10/10
 * @name:查看物流
 */
public class LogisticsActivity extends BaseActivity implements Serializable {
    private OrderBean.ItemsBean itemsBean;
    private OrderVo.GooditemsBean gooditemsBean;
    private TextView title_text_tv, title_left_btn,text_name,text_address;
    private RecyclerView recyclerView;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_logistics);
        BaseApplication.activityTaskManager.putActivity("LogisticsActivity", this);
    }

    @Override
    protected void initView() {
        text_address= getView(R.id.text_address);
        text_name= getView(R.id.text_name);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("物流信息");
    }

    @Override
    protected void initData() {
        itemsBean = (OrderBean.ItemsBean) getIntent().getSerializableExtra("itemsBean");
        if (itemsBean != null) {

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
