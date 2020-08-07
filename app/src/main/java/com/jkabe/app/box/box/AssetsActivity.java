package com.jkabe.app.box.box;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.AsetsAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
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
 * @date: 2020/7/9
 * @name:财务记录
 */
public class AssetsActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener, NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_draw, text_recharge;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private AsetsAdapter asetsAdapter;
    private List<AssetsBean> data = new ArrayList<>();
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private NoDataView mNoDataView;
    UsdtBean usdtBean;
    private String coinTypeId;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_assets);
    }

    @Override
    protected void initView() {
        mNoDataView = getView(R.id.mNoDataView);
        text_recharge = getView(R.id.text_recharge);
        text_draw = getView(R.id.text_draw);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("资产明细");
        text_recharge.setOnClickListener(this);
        text_draw.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(layoutManager);
        mNoDataView.textView.setText("暂无资产明细");
        usdtBean = (UsdtBean) getIntent().getSerializableExtra("usdtBean");
    }

    @Override
    protected void initData() {
        coinTypeId = getIntent().getStringExtra("coinTypeId");
        query();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_recharge:
                intent = new Intent(this, RechargeActivity.class);
                intent.putExtra("usdtBean", usdtBean);
                intent.putExtra("coinTypeId", coinTypeId);
                startActivity(intent);
                break;
            case R.id.text_draw:
                intent = new Intent(this, DrawActivity.class);
                intent.putExtra("usdtBean", usdtBean);
                intent.putExtra("coinTypeId", coinTypeId);
                startActivity(intent);
                break;
        }
    }


    public void query() {
        String sign = "coinTypeId=" + coinTypeId + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("coinTypeId", coinTypeId + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GETRECORD_BOX, params, Api.MINING_BOX_ID, this);
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
                    case Api.MINING_BOX_ID:
                        List<AssetsBean> assetsBeans = JsonParse.getJSONObjectAssetsBean(object);
                        if (assetsBeans != null && assetsBeans.size() > 0) {
                            setAdapter(assetsBeans);
                        } else {
                            if (!isRefresh&&page==1){
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


    private void setAdapter(List<AssetsBean> strings) {
        mNoDataView.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            data.clear();
            data.addAll(strings);
            asetsAdapter = new AsetsAdapter(this, data);
            swipe_target.setAdapter(asetsAdapter);
        } else {
            data.addAll(strings);
            asetsAdapter.setData(data);
        }
    }


    @Override
    public void onFail() {

    }

    @Override
    public void onError(Exception e) {

    }

}
