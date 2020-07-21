package com.jkabe.app.android.ui;

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
import com.jkabe.box.R;
import com.jkabe.app.android.adapter.ElectronAdapter;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.bean.Electronic;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.JsonParse;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.Utility;
import com.jkabe.app.android.weight.NoDataView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/13
 * @name:电子围栏
 */
public class ElectronicActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener, NetWorkListener {
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private NoDataView mNoDataView;
    private TextView title_text_tv, title_left_btn, title_right_btn;
    private List<Electronic> electronics = new ArrayList<>();
    private ElectronAdapter adapter;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_electronic);
    }

    @Override
    protected void initView() {
        mNoDataView = getView(R.id.mNoDataView);
        title_right_btn = getView(R.id.title_right_btn);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        title_right_btn.setOnClickListener(this);
        title_text_tv.setText("电子围栏");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(manager);
        mNoDataView.textView.setText("暂无电子围栏");
        title_right_btn.setText("添加");
        title_right_btn.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_map_location, 0, 0, 0);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        qury();
    }

    @Override
    public void onLoadMore() {
        isRefresh = true;
        page++;
        qury();
    }

    @Override
    public void onRefresh() {
        isRefresh = false;
        page = 1;
        qury();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_right_btn:
                startActivity(new Intent(this, LocationMapActivity.class));
                break;
            case R.id.title_left_btn:
                finish();
                break;
        }
    }

    private void qury() {
        String sign = "deviceid=" + SaveUtils.getCar().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("deviceid", SaveUtils.getCar().getId() + "");
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ELECT_DEVICE, params, Api.GET_ELECT_DEVICE_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_ELECT_DEVICE_ID:
                        List<Electronic> infos = JsonParse.getElectronicJson(object);
                        if (infos != null && infos.size() > 0) {
                            setAdapter(infos);
                        } else {
                            if (page == 1 && !isRefresh) {
                                swipe_target.setVisibility(View.GONE);
                                mNoDataView.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }

    private void setAdapter(List<Electronic> voList) {
        mNoDataView.setVisibility(View.GONE);
        swipe_target.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            electronics.clear();
            electronics.addAll(voList);
            adapter = new ElectronAdapter(this, electronics);
            swipe_target.setAdapter(adapter);
        } else {
            electronics.addAll(voList);
            adapter.setData(electronics);
        }

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ElectronicActivity.this, LocationMapActivity.class);
                intent.putExtra("electronic", electronics.get(position));
                startActivity(intent);
            }
        });

    }


    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }
}
