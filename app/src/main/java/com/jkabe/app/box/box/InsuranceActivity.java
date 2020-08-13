package com.jkabe.app.box.box;

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
import com.jkabe.app.box.adapter.InsurAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.InsureInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.NoDataView;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/8/10
 * @name:行程数据区块
 */
public class InsuranceActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener, NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private int limit = 20;
    private int page = 1;
    private boolean isRefresh;
    private NoDataView mNoDataView;
    private List<InsureInfo> travrts = new ArrayList<>();
    private InsurAdapter tripAdapter;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_trip);
    }

    @Override
    protected void initView() {
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        mNoDataView = getView(R.id.mNoDataView);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        title_text_tv.setText("保险数据区块");
        mNoDataView.textView.setText("暂无保险数据区块");
    }

    @Override
    protected void initData() {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(gridLayoutManager);
        query();
    }

    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.RESET_TOKEN_INSURE, params, Api.RESET_TOKEN_TRIP_ID, this);
    }


    @Override
    public void onRefresh() {
        isRefresh = false;
        page = 1;
        query();
    }

    @Override
    public void onLoadMore() {
        isRefresh = true;
        page++;
        query();
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.RESET_TOKEN_TRIP_ID:
                        List<InsureInfo> travrts = JsonParse.getInsureInfoJson2(object);
                        if (travrts != null && travrts.size() > 0) {
                            setAdapter(travrts);
                        } else {
                            if (!isRefresh && page == 1) {
                                mNoDataView.setVisibility(View.VISIBLE);
                                swipeToLoadLayout.setVisibility(View.GONE);
                            }
                        }
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }


    private void setAdapter(List<InsureInfo> voList) {
        mNoDataView.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);

        if (!isRefresh) {
            travrts.clear();
            travrts.addAll(voList);
            tripAdapter = new InsurAdapter(this, travrts);
            swipe_target.setAdapter(tripAdapter);
        } else {
            travrts.addAll(voList);
            tripAdapter.setData(travrts);
        }

        tripAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(InsuranceActivity.this, InsureDeilActivity.class);
                intent.putExtra("travrt",travrts.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
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
