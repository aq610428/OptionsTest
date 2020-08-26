package com.jkabe.app.box.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.CarAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.ui.MsgActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/8/17
 * @name:矿场
 */
public class OreFragment extends BaseFragment implements NetWorkListener, OnRefreshListener, View.OnClickListener {
    private View rootView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private List<OreInfo> oreInfos = new ArrayList<>();
    private RelativeLayout rl_mining, rl_assets;
    private CarAdapter carAdapter;
    private TextView text_right;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_ore, container, false);
            initView();
            lazyLoad();
            query();
        }
        return rootView;
    }

    private void initView() {
        text_right= rootView.findViewById(R.id.text_right);
        rl_assets = rootView.findViewById(R.id.rl_assets);
        rl_mining = rootView.findViewById(R.id.rl_mining);
        swipe_target = rootView.findViewById(R.id.mListView);
        swipeToLoadLayout = rootView.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        rl_assets.setOnClickListener(this);
        rl_mining.setOnClickListener(this);
        text_right.setOnClickListener(this);

    }


    /*******虚拟货币列表
     * @param ********/
    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_LIST, params, Api.GET_COINS_LIST_ID, this);
    }


    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
        query();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_assets:
                startActivity(new Intent(getContext(), AssetsFragmnt.class));
                break;
            case R.id.rl_mining:
                startActivity(new Intent(getContext(), MiningFragmnt.class));
                break;
            case R.id.text_right:
                startActivity(new Intent(getContext(), MsgActivity.class));
                break;

        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_COINS_LIST_ID:
                        oreInfos = JsonParse.getBespokemoniesJson1(object);
                        if (oreInfos != null && oreInfos.size() > 0) {
                            setAdapter();
                        }
                        break;
                }
            }
        }
        swipeToLoadLayout.setRefreshing(false);
    }


    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(layoutManager);
        carAdapter = new CarAdapter(getContext(), oreInfos);
        swipe_target.setAdapter(carAdapter);
    }


    @Override
    public void onFail() {
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        query();
    }
}
