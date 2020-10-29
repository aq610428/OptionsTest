package com.jkabe.app.box.box.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.CarAdapter1;
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
public class TabFragment1 extends BaseFragment implements NetWorkListener, OnRefreshListener {
    private View rootView;
    private List<OreInfo> oreInfos = new ArrayList<>();
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private CarAdapter1 carAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        swipe_target = rootView.findViewById(R.id.mListView);
        swipeToLoadLayout = rootView.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipe_target.setNestedScrollingEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(layoutManager);
        query();
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
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }


    private void setAdapter() {
        carAdapter = new CarAdapter1(getContext(), oreInfos);
        swipe_target.setAdapter(carAdapter);
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
