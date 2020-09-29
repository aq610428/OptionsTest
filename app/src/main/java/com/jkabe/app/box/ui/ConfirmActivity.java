package com.jkabe.app.box.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.adapter.ConfimAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.app.box.util.Constants;
import com.jkabe.box.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/17
 * @name:ConfirmActivity
 */
public class ConfirmActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_sumber;
    private RecyclerView recyclerView;
    private List<ImageInfo> list = new ArrayList<>();
    private ConfimAdapter adapter;
    private RelativeLayout ll_address;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_confirm);
    }

    @Override
    protected void initView() {
        ll_address = getView(R.id.ll_address);
        text_sumber = getView(R.id.text_sumber);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_sumber.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        title_text_tv.setText("确认订单");
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = Constants.getDate();

        adapter = new ConfimAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.ll_address:
                startActivity(new Intent(this, AddressListActivity.class));
                break;
            case R.id.text_sumber:

                break;
        }
    }
}
