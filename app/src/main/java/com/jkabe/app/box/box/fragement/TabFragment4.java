package com.jkabe.app.box.box.fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.DefiListAdapter;
import com.jkabe.app.box.adapter.DefiListAdapter1;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/10/20
 * @name:TabFragment1
 */
public class TabFragment4 extends BaseFragment implements NetWorkListener, OnRefreshListener, View.OnClickListener {
    private View rootView;
    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<OreInfo> beans = new ArrayList<>();
    private DefiListAdapter adapter;
    private DefiListAdapter1 adapter1;
    private LinearLayout ll_tab2, ll_tab1;
    private TextView text_account, text_usdt, text_account_box, text_money_box;
    private int page = 1;


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
        text_money_box = getView(rootView, R.id.text_money_box);
        text_account_box = getView(rootView, R.id.text_account_box);
        text_usdt = getView(rootView, R.id.text_usdt);
        text_account = getView(rootView, R.id.text_account);
        ll_tab2 = getView(rootView, R.id.ll_tab2);
        ll_tab1 = getView(rootView, R.id.ll_tab1);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        recyclerView = getView(rootView, R.id.recyclerView);
        swipeToLoadLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_tab2:
                clealView();
                page = 2;
                text_account_box.setTextColor(Color.parseColor("#FFFFFF"));
                text_money_box.setTextColor(Color.parseColor("#FFFFFF"));
                ll_tab2.setBackgroundResource(R.drawable.shape_bg_vx2);
                query();
                break;
            case R.id.ll_tab1:
                page = 1;
                clealView();
                text_account.setTextColor(Color.parseColor("#FFFFFF"));
                text_usdt.setTextColor(Color.parseColor("#FFFFFF"));
                ll_tab1.setBackgroundResource(R.drawable.shape_bg_vx2);
                query();
                break;
        }
    }

    public void clealView() {
        ll_tab1.setBackgroundResource(R.drawable.shape_bg_mi);
        ll_tab2.setBackgroundResource(R.drawable.shape_bg_mi);
        text_account.setTextColor(Color.parseColor("#333333"));
        text_usdt.setTextColor(Color.parseColor("#F05330"));
        text_account_box.setTextColor(Color.parseColor("#333333"));
        text_money_box.setTextColor(Color.parseColor("#F05330"));
    }


    @Override
    protected void lazyLoad() {
        query();
    }


    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_LIST, params, Api.GET_COINS_LIST_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_COINS_LIST_ID:
                        beans = JsonParse.getBespokemoniesJson1(object);
                        if (beans != null && beans.size() > 0) {
                            setAdapter();
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }


    private void setAdapter() {
        if (page == 1) {
            adapter = new DefiListAdapter(getContext(), beans);
            recyclerView.setAdapter(adapter);
        } else {
            adapter1 = new DefiListAdapter1(getContext(), beans);
            recyclerView.setAdapter(adapter1);
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
    public void onRefresh() {
        query();
    }


}
