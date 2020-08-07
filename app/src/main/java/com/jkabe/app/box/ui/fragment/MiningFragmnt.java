package com.jkabe.app.box.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.box.IncomeActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.ui.ActivationActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.Map;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:挖矿
 */
public class MiningFragmnt extends BaseFragment implements NetWorkListener, View.OnClickListener, OnRefreshListener {
    private View rootView;
    private TextView text_right, text_cny, text_travel, text_work, text_dig,text_bind;
    private SwipeToLoadLayout swipeToLoadLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mining, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        text_bind = getView(rootView, R.id.text_bind);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        text_cny = getView(rootView, R.id.text_cny);
        text_right = getView(rootView, R.id.text_right);
        text_cny = getView(rootView, R.id.text_cny);
        text_travel = getView(rootView, R.id.text_travel);
        text_work = getView(rootView, R.id.text_work);
        text_dig = getView(rootView, R.id.text_dig);
        text_right.setOnClickListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        text_bind.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
        updateView();
        query();
    }


    @Override
    protected void lazyLoad() {

    }


    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_UNMODEL_BOX, params, Api.GET_UNMODEL_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_UNMODEL_BOX_ID:
                        updateJson(object);
                        break;

                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    private void updateJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        if (jsonObject != null) {
            text_travel.setText(jsonObject.optString("yestodayBox") + " BOX");
            text_work.setText(jsonObject.optString("activeCount") + " 个");
            text_dig.setText(jsonObject.optString("miningBox") + " BOX");
            text_cny.setText(jsonObject.optString("totalBox") + "");
            String stat=jsonObject.optString("state");
            if ("1".equals(stat+"")||"3".equals(stat)){
                text_bind.setVisibility(View.GONE);
            }
        }

    }

    private void updateView() {
        if (SaveUtils.getCar() == null || Utility.isEmpty(SaveUtils.getCar().getId())) {
            DialogUtils.showBind(1, getContext());
        } else {
            if (!"2".equals(SaveUtils.getSaveInfo().getIsMining() + "")) {
                DialogUtils.showBind(2, getContext());
            }
        }
    }

    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_right:
                startActivity(new Intent(getContext(), IncomeActivity.class));
                break;
            case R.id.text_bind:
                startActivity(new Intent(getActivity(), ActivationActivity.class));
                break;
        }
    }

    @Override
    public void onRefresh() {
        query();
    }
}
