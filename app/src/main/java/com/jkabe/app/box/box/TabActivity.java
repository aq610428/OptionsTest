package com.jkabe.app.box.box;

import android.app.Dialog;
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
import com.jkabe.app.box.adapter.TabAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.BoxVo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.box.fragement.PayFragment;
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
 * @date: 2020/11/4
 * @name:TabActivity
 */
public class TabActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener, NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private int page = 1;
    private int limit = 10;
    private boolean isRefresh;
    private NoDataView noDataView;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<BoxVo> beans = new ArrayList<>();
    private TabAdapter tabAdapter;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tab);
    }

    @Override
    protected void initView() {
        noDataView = getView(R.id.noDataView);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_text_tv.setText("理财记录");
        title_left_btn.setOnClickListener(this);
        noDataView.textView.setText("暂无理财记录");
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(layoutManager);
        query();
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


    public void query() {
        showProgressDialog(this, false);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.LIST_CHANCE_BOX, params, Api.LIST_CHANCE_BOX_ID, this);
    }


    public void query1(String id) {
        showProgressDialog(this, false);
        String sign = "id=" + id + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", id + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.post(Api.REDE_MEMBER_BOX, params, Api.REDE_MEMBER_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.LIST_CHANCE_BOX_ID:
                        List<BoxVo> list = JsonParse.getBoxVoJSON(object);
                        if (list != null && list.size() > 0) {
                            setAdapter(list);
                        } else {
                            if (!isRefresh && page == 1) {
                                swipeToLoadLayout.setVisibility(View.GONE);
                                noDataView.setVisibility(View.VISIBLE);
                            }

                        }
                        break;
                    case Api.REDE_MEMBER_BOX_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        onRefresh();
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        stopProgressDialog();
    }


    private void setAdapter(List<BoxVo> boxVoList) {
        noDataView.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            beans.clear();
            beans.addAll(boxVoList);
            tabAdapter = new TabAdapter(this, beans);
            swipe_target.setAdapter(tabAdapter);
        } else {
            beans.addAll(boxVoList);
            tabAdapter.setData(beans);
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
        }
    }


    public void showOrder(String id, String money) {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText("如果赎回当前理财，您将违约" + "违约金" + money + "BOX将从本金中扣除,是否赎回？");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query1(id);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onFail() {
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        stopProgressDialog();
    }
}
