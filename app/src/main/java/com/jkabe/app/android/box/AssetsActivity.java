package com.jkabe.app.android.box;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.android.adapter.AsetsAdapter;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:财务记录
 */
public class AssetsActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    private TextView title_text_tv, title_left_btn,text_draw,text_recharge;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private AsetsAdapter asetsAdapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_assets);
    }

    @Override
    protected void initView() {
        text_recharge = getView(R.id.text_recharge);
        text_draw = getView(R.id.text_draw);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("财务记录");
        text_recharge.setOnClickListener(this);
        text_draw.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        data.add("1");
        data.add("1");
        data.addAll(data);
        data.addAll(data);
        data.addAll(data);
        asetsAdapter = new AsetsAdapter(this, data);
        swipe_target.setAdapter(asetsAdapter);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_recharge:
                 startActivity(new Intent(this,RechargeActivity.class));
                break;
            case R.id.text_draw:
                startActivity(new Intent(this,DrawActivity.class));
                break;
        }
    }


    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
