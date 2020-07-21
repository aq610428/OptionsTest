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
import com.jkabe.app.android.adapter.EarlyAdapter;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.bean.EarlyInfo;
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
 * @date: 2020/7/15
 * @name:EarlyActivity
 */
public class EarlyActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener, NetWorkListener {
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private NoDataView mNoDataView;
    private TextView title_text_tv, title_left_btn, title_right_btn;
    private List<EarlyInfo> electronics = new ArrayList<>();
    private EarlyAdapter adapter;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_early);
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
        title_text_tv.setText("车辆预警");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(manager);
        mNoDataView.textView.setText("暂无车辆预警");
        title_right_btn.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_set_miao, 0, 0, 0);
    }

    @Override
    protected void initData() {
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


    private void qury() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_AGE_DEVICE, params, Api.GET_AGE_DEVICE_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_AGE_DEVICE_ID:
                        List<EarlyInfo> infos = JsonParse.getEarlyInfoJson(object);
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


    private void setAdapter(List<EarlyInfo> voList) {
        mNoDataView.setVisibility(View.GONE);
        swipe_target.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            electronics.clear();
            electronics.addAll(voList);
            adapter = new EarlyAdapter(this, electronics);
            swipe_target.setAdapter(adapter);
        } else {
            electronics.addAll(voList);
            adapter.setData(electronics);
        }

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EarlyActivity.this, LocationEarlyActivity.class);
                intent.putExtra("info", electronics.get(position));
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_right_btn:
                startActivity(new Intent(this, SafeActivity.class));
                break;
            case R.id.title_left_btn:
                finish();
                break;
        }
    }

}
