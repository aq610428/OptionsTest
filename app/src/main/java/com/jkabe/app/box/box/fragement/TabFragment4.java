package com.jkabe.app.box.box.fragement;

import android.content.Intent;
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
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.box.AssetsActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/10/20
 * @name:TabFragment1
 */
public class TabFragment4 extends BaseFragment implements NetWorkListener, OnRefreshListener, View.OnClickListener {
    private View rootView;
    private TextView text_cny, text_num_usd, text_user_usd, text_congeal_usd, text_usd_usd;
    private SwipeToLoadLayout swipeToLoadLayout;
    private int limit = 10;
    private int page = 1;
    private TextView text_num_bc, text_user_bc, text_congeal_bc, text_usd_bc;
    private UsdtBean usdtBean;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab4, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        text_num_bc = getView(rootView, R.id.text_num_bc);
        text_user_bc = getView(rootView, R.id.text_user_bc);
        text_congeal_bc = getView(rootView, R.id.text_congeal_bc);
        text_usd_bc = getView(rootView, R.id.text_usd_bc);
        text_num_usd = getView(rootView, R.id.text_num_usd);
        text_user_usd = getView(rootView, R.id.text_user_usd);
        text_congeal_usd = getView(rootView, R.id.text_congeal_usd);
        text_usd_usd = getView(rootView, R.id.text_usd_usd);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        text_cny = getView(rootView, R.id.text_cny);
        swipeToLoadLayout.setOnRefreshListener(this);
        text_num_usd.setOnClickListener(this);
        text_num_bc.setOnClickListener(this);

        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_congeal_bc);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_user_bc);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_user_usd);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_congeal_usd);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_cny);


        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_usd_usd);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_usd_bc);
    }



    @Override
    protected void lazyLoad() {
        query();
    }

    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
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
                    intent = new Intent(getContext(), AssetsActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getUsdt().getCoinTypeId() + "");
                    startActivity(intent);
                }
                break;
            case R.id.text_num_bc:
                if (usdtBean != null && usdtBean.getBox() != null) {
                    intent = new Intent(getContext(), AssetsActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getBox().getCoinTypeId() + "");
                    startActivity(intent);
                }
                break;


        }

    }


}
