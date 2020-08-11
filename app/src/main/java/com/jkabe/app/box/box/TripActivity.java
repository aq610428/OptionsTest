package com.jkabe.app.box.box;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.AsetsAdapter;
import com.jkabe.app.box.adapter.TripAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.DateUtils;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.NoDataView;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/8/10
 * @name:行程数据区块
 */
public class TripActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener, NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private NoDataView mNoDataView;
    private List<Travrt> travrts = new ArrayList<>();
    private TripAdapter tripAdapter;
    private String selectTime, endtime;


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
        title_text_tv.setText("行程数据区块");
        mNoDataView.textView.setText("暂无行程数据区块");
        selectTime = DateUtils.getNextTime1();
        endtime = DateUtils.DateToStr1(DateUtils.getAddDay(new Date(), -4));
    }

    @Override
    protected void initData() {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(gridLayoutManager);
        query();
    }

    public void query() {
        String sign = "endtime=" + endtime + "&imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&starttime=" + endtime + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("endtime", endtime);
        params.put("imeicode", SaveUtils.getCar().getImeicode());
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("starttime", endtime);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_TRACK_TRIP, params, Api.GET_TRACK_TRIP_ID, this);
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
                    case Api.GET_TRACK_TRIP_ID:
                        travrts = JsonParse.getTraverJson(object);
                        if (travrts != null && travrts.size() > 0) {
                            setAdapter();
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


    private void setAdapter() {
        mNoDataView.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        tripAdapter = new TripAdapter(this, travrts);
        swipe_target.setAdapter(tripAdapter);
        tripAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TripActivity.this, TripDeilActivity.class);
                intent.putExtra("travrt", travrts.get(position));
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
