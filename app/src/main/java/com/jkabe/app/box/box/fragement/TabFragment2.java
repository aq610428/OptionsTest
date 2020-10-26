package com.jkabe.app.box.box.fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.TransitionRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.IncomeAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.BoxInfo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.DateUtils;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.StatusBarUtil;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/10/20
 * @name:TabFragment1
 */
public class TabFragment2 extends BaseFragment implements NetWorkListener, OnRefreshListener, OnLoadMoreListener {
    private View rootView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private TextView text_total, text_day, text_mining, text_machinery;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private List<BoxInfo> data = new ArrayList<>();
    private IncomeAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        text_day = getView(rootView, R.id.text_day);
        text_mining = getView(rootView, R.id.text_mining);
        text_machinery = getView(rootView, R.id.text_machinery);
        text_total = getView(rootView, R.id.text_total);
        recyclerView = getView(rootView, R.id.recyclerView);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        recyclerView.setNestedScrollingEnabled(true);
        swipeToLoadLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_day);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_mining);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_machinery);
        TypefaceUtil.setTextType(getContext(), "DINOT-Bold.ttf", text_total);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusBarUtil.setTranslucentStatus(getActivity());
        updateView();
    }


    private void updateView() {
        if (SaveUtils.getCar() == null || Utility.isEmpty(SaveUtils.getCar().getId())) {
            DialogUtils.showBind(1, getActivity());
        } else {
            if (!"2".equals(SaveUtils.getSaveInfo().getIsMining() + "")) {
                DialogUtils.showBind(2, getActivity());
            }
        }
    }



    protected void lazyLoad() {
        query();
        queryData();
    }


    public void queryData() {
        showProgressDialog(getActivity(), false);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_UNMODEL_BOX, params, Api.GET_UNMODEL_BOX_ID, this);
    }


    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MINING_BOX, params, Api.MINING_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_UNMODEL_BOX_ID:
                        updateJson(object);
                        break;
                    case Api.MINING_BOX_ID:
                        List<BoxInfo> boxInfos = JsonParse.getBoxJson(object);
                        if (boxInfos != null && boxInfos.size() > 0) {
                            setAdapter(boxInfos);
                        }
                        break;

                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }


    private void setAdapter(List<BoxInfo> boxInfos) {
        if (!isRefresh) {
            data.clear();
            data.addAll(boxInfos);
            adapter = new IncomeAdapter(getActivity(), data);
            recyclerView.setAdapter(adapter);
        } else {
            data.addAll(boxInfos);
            adapter.setData(data);
        }
    }


    private void updateJson(JSONObject object) {
        JSONObject jsonObject = object.optJSONObject("result");
        if (jsonObject != null) {
            String yestodayBox = jsonObject.optString("yestodayBox");
            String miningBox = jsonObject.optString("miningBox");
            String totalBox = jsonObject.optString("totalBox");
            String activeCount = jsonObject.optString("activeCount");
            text_day.setText(yestodayBox + "BOX");
            text_total.setText(totalBox + "BOX");
            text_mining.setText(miningBox + "BOX");
            text_machinery.setText(activeCount + "个");

            String stat = jsonObject.optString("state");
            String miningDate = jsonObject.optString("miningDate");
            if ("2".equals(stat + "") && !Utility.isEmpty(miningDate)) {
                String str[] = miningDate.split("T");
                if (str.length == 2) {
                    String date = str[0] + " " + str[1].substring(0, 8);
                    float day = DateUtils.getDayLong(date);
                    if (day <= 7) {
                        DialogUtils.showPay(getActivity());
                    }
                }
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
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
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
}
