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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.box.R;
import com.jkabe.app.box.adapter.CarAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.Money;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.ui.PreviewActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.Utility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/7/2
 * @name:资产
 */
public class RecordFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, NetWorkListener {
    private View rootView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private List<Money> monies = new ArrayList<>();
    private CarAdapter carAdapter;
    private TextView btn_next;
    private TextView text_integral, text_grand;
    private UserInfo info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_record, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        text_grand = rootView.findViewById(R.id.text_grand);
        text_integral = rootView.findViewById(R.id.text_integral);
        btn_next = rootView.findViewById(R.id.btn_next);
        swipe_target = rootView.findViewById(R.id.mListView);
        swipeToLoadLayout = rootView.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        btn_next.setOnClickListener(this);
        info = SaveUtils.getSaveInfo();
        query();
        queryList();
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
    }


    /*******积分
     * @param ********/
    public void queryList() {
        String sign = "memberid=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", info.getId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_INTER, params, Api.GET_COINS_INTER_ID, this);
    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), PreviewActivity.class);
        intent.putExtra("url", "http://kb.jkabe.com/app/dlchezhen");
        intent.putExtra("name", "积分兑换");
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        query();
        queryList();
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_COINS_LIST_ID:
                        monies = JsonParse.getBespokemoniesJson(object);
                        if (monies != null && monies.size() > 0) {
                            setAdapter();
                        }
                        break;
                    case Api.GET_COINS_INTER_ID:
                        JSONObject jsonObject = object.optJSONObject("result");
                        if (jsonObject != null) {
                            String totalintegral = jsonObject.optString("totalintegral");
                            String usablelintegral = jsonObject.optString("usablelintegral");
                            text_grand.setText(totalintegral + "");
                            text_integral.setText(usablelintegral + "");
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
//        carAdapter = new CarAdapter(getContext(), monies);
//        swipe_target.setAdapter(carAdapter);
    }

    @Override
    public void onFail() {
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        swipeToLoadLayout.setRefreshing(false);
    }
}
