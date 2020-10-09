package com.jkabe.app.box.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.AddressAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressListActivity
 */
public class AddressListActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    private TextView title_text_tv, title_left_btn;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<String> array = new ArrayList<>();
    private AddressAdapter adapter;
    private TextView text_add;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_address);
    }

    @Override
    protected void initView() {
        text_add = getView(R.id.text_add);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        text_add.setOnClickListener(this);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("收货地址");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_add:
               startActivity(new Intent(this,AddressActivity.class));
                break;
        }
    }




    @Override
    protected void initData() {
        array.add("1");
        array.add("1");
        array.addAll(array);
        adapter = new AddressAdapter(this, array);
        swipe_target.setAdapter(adapter);


        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                setResult(101,intent);
                finish();
            }
        });
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
