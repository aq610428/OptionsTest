package com.jkabe.app.box.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.StoreInfo;
import com.jkabe.app.box.bean.UserInfo;
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
import com.jkabe.app.box.weight.PreferenceUtils;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/5/28
 * @name:门店列表
 */
public class StoreListActivity extends BaseActivity implements OnLoadMoreListener, NetWorkListener, OnRefreshListener {
    private TextView title_text_tv, title_left_btn, title_right_btn;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private StoreListAdapter adapter;
    private List<StoreInfo> infos = new ArrayList<>();
    private int limit = 10;
    private int page = 1;
    private UserInfo info;
    private boolean isRefresh;
    private NoDataView mNoDataView;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_store_list);
        BaseApplication.activityTaskManager.putActivity("StoreActivity", this);
        info = SaveUtils.getSaveInfo();
        qury();
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
        title_text_tv.setText("附近门店");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(manager);
        mNoDataView.textView.setText("附近没有门店");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.title_right_btn:
                Intent intent = new Intent(this, LocationActivity.class);
                intent.putExtra("infos", (Serializable) infos);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onLoadMore() {
        isRefresh = true;
        page++;
        qury();
    }


    private void qury() {
        String lat = PreferenceUtils.getPrefString(this, Constants.LAT, "");
        String lon = PreferenceUtils.getPrefString(this, Constants.LON, "");
        String sign = "lat=" + lat + "&lng=" + lon + "&memberid=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", info.getId() + "");
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("lat", lat);
        params.put("lng", lon);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_STORE_VERSION, params, Api.GET_STORE_VERSION_ID, this);
    }


    /*****绑定门店******/
    public void bindQury(String storeId) {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + "&storeId=" + storeId + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberId", info.getId() + "");
        params.put("storeId", storeId + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_BAND_VERSION, params, Api.GET_BAND_VERSION_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_STORE_VERSION_ID:
                        List<StoreInfo> infos = JsonParse.getStoreJson(object);
                        if (infos != null && infos.size() > 0) {
                            setAdapter(infos);
                        } else {
                            if (page == 1 && !isRefresh) {
                                swipe_target.setVisibility(View.GONE);
                                mNoDataView.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case Api.GET_BAND_VERSION_ID:
                        ToastUtil.showToast(commonality.getErrorDesc() + "");
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc() + "");
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }

    private void setAdapter(List<StoreInfo> voList) {
        mNoDataView.setVisibility(View.GONE);
        swipe_target.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            infos.clear();
            infos.addAll(voList);
            adapter = new StoreListAdapter(this, infos);
            swipe_target.setAdapter(adapter);
        } else {
            infos.addAll(voList);
            adapter.setData(infos);
        }

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StoreListActivity.this, StoreDeilActivity.class);
                intent.putExtra("storeInfo", infos.get(position));
                startActivity(intent);
            }
        });
    }

    public void showDialog1(String storeId,String dis) {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.title)).setText("温馨提示");
        ((TextView) view.findViewById(R.id.message)).setText("是否确认绑定该店铺?");
        ((TextView) view.findViewById(R.id.cancel)).setText("以后再说");
        ((TextView) view.findViewById(R.id.confirm)).setText("立即绑定");
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindQury(storeId);
                PreferenceUtils.setPrefString(StoreListActivity.this,"dis",dis);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        stopProgressDialog();
    }

    @Override
    public void onRefresh() {
        isRefresh = false;
        page = 1;
        qury();
    }
}
