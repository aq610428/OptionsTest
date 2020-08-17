package com.jkabe.app.box.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.box.AssetsActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.app.box.weight.NoDataView;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:资产
 */
public class AssetsFragmnt extends BaseActivity implements NetWorkListener, OnRefreshListener, View.OnClickListener {
    private TextView text_cny, text_num_usd, text_user_usd, text_congeal_usd, text_usd_usd;
    private SwipeToLoadLayout swipeToLoadLayout;
    private int limit = 10;
    private int page = 1;
    private NoDataView mNoDataView;
    private TextView text_num_bc, text_user_bc, text_congeal_bc, text_usd_bc;
    UsdtBean usdtBean;
    private TextView title_text_tv, title_left_btn;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_assets);
        initView();
        query();
    }

    public void initView() {
        text_num_bc = getView(R.id.text_num_bc);
        text_user_bc = getView(R.id.text_user_bc);
        text_congeal_bc = getView(R.id.text_congeal_bc);
        text_usd_bc = getView(R.id.text_usd_bc);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_num_usd = getView(R.id.text_num_usd);
        text_user_usd = getView(R.id.text_user_usd);
        text_congeal_usd = getView(R.id.text_congeal_usd);
        text_usd_usd = getView(R.id.text_usd_usd);
        title_text_tv.setText("我的资产");

        mNoDataView = getView(R.id.mNoDataView);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        text_cny = getView(R.id.text_cny);
        swipeToLoadLayout.setOnRefreshListener(this);
        mNoDataView.textView.setText("账户暂无资产流水");


        text_num_usd.setOnClickListener(this);
        text_num_bc.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }


    private void updateView() {
        if (SaveUtils.getCar() == null || Utility.isEmpty(SaveUtils.getCar().getId())) {
            DialogUtils.showBind(1, this);
        } else {
            if (!"2".equals(SaveUtils.getSaveInfo().getIsMining() + "")) {
                DialogUtils.showBind(2, this);
            }
        }
    }


    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MINING_BAL_BOX, params, Api.GETRECORD_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GETRECORD_BOX_ID:
                        usdtBean = JsonParse.getJSONObjectUsdtBean(object);
                        if (usdtBean != null) {
                            setAdapter(usdtBean);
                        } else {
                            mNoDataView.setVisibility(View.VISIBLE);
                            swipeToLoadLayout.setVisibility(View.GONE);
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    private void setAdapter(UsdtBean usdtBean) {
        mNoDataView.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        if (usdtBean.getUsdt() != null) {
            text_num_usd.setText(usdtBean.getUsdt().getCoinTypeName());
            text_user_usd.setText(usdtBean.getUsdt().getUserable() + "");
            text_congeal_usd.setText(usdtBean.getUsdt().getFreeze() + "");
            text_cny.setText(usdtBean.getUsdt().getUserable() + "");
            if (!Utility.isEmpty(usdtBean.getUsdt().getStringCreateTime())) {
                String time = usdtBean.getUsdt().getStringCreateTime();
                text_usd_usd.setText(time.substring(0, 10));
            }

        }


        if (usdtBean.getBox() != null) {
            text_num_bc.setText(usdtBean.getBox().getCoinTypeName());
            text_user_bc.setText(usdtBean.getBox().getUserable() + "");
            text_congeal_bc.setText(usdtBean.getBox().getFreeze() + "");
            if (!Utility.isEmpty(usdtBean.getBox().getStringCreateTime())) {
                String time = usdtBean.getBox().getStringCreateTime();
                text_usd_bc.setText(time.substring(0, 10));
            }
        }

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
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        page = 1;
        query();
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.text_num_usd:
                if (usdtBean != null && usdtBean.getUsdt() != null) {
                    intent = new Intent(this, AssetsActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getUsdt().getCoinTypeId() + "");
                    startActivity(intent);
                }
                break;
            case R.id.text_num_bc:
                if (usdtBean != null && usdtBean.getBox() != null) {
                    intent = new Intent(this, AssetsActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getBox().getCoinTypeId() + "");
                    startActivity(intent);
                }
                break;
            case R.id.title_left_btn:
                finish();
                break;

        }

    }
}
