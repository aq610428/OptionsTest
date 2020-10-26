package com.jkabe.app.box.box.fragement;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.AsetsAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.box.DrawActivity;
import com.jkabe.app.box.box.RechargeActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
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
public class TabFragment4 extends BaseFragment implements NetWorkListener, OnRefreshListener, View.OnClickListener, OnLoadMoreListener {
    private View rootView;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private UsdtBean usdtBean;
    private TextView text_usd, text_usdt_charge, text_usdt_carry, text_box_charge, text_box_carry;
    private TextView text_usdt, text_box;
    private String coinTypeId = "1";
    private List<AssetsBean> data = new ArrayList<>();
    private AsetsAdapter asetsAdapter;
    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipeToLoadLayout;


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
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        recyclerView = getView(rootView, R.id.recyclerView);
        text_box = getView(rootView, R.id.text_box);
        text_usdt = getView(rootView, R.id.text_usdt);
        text_usdt_charge = getView(rootView, R.id.text_usdt_charge);
        text_usdt_carry = getView(rootView, R.id.text_usdt_carry);
        text_box_charge = getView(rootView, R.id.text_box_charge);
        text_box_carry = getView(rootView, R.id.text_box_carry);
        text_usd = getView(rootView, R.id.text_usd);
        text_usd.setOnClickListener(this);
        text_usdt_charge.setOnClickListener(this);
        text_usdt_carry.setOnClickListener(this);
        text_box_charge.setOnClickListener(this);
        text_box_carry.setOnClickListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(false);
    }



    protected void lazyLoad() {
        load();
        query();
    }

    public void load() {
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


    public void query() {
        String sign = "coinTypeId=" + coinTypeId + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("coinTypeId", coinTypeId + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GETRECORD_BOX, params, Api.MINING_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GETRECORD_BOX_ID:
                        usdtBean = JsonParse.getJSONObjectUsdtBean(object);
                        if (usdtBean != null) {
                            updateView(usdtBean);
                        }
                        break;
                    case Api.MINING_BOX_ID:
                        List<AssetsBean> assetsBeans = JsonParse.getJSONObjectAssetsBean(object);
                        if (assetsBeans != null && assetsBeans.size() > 0) {
                            setAdapter(assetsBeans);
                        }
                        break;

                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }


    private void setAdapter(List<AssetsBean> assetsBeans) {
        if (!isRefresh) {
            data.clear();
            data.addAll(assetsBeans);
            asetsAdapter = new AsetsAdapter(getContext(), data);
            recyclerView.setAdapter(asetsAdapter);
        } else {
            data.addAll(assetsBeans);
            asetsAdapter.setData(data);
        }
    }

    private void updateView(UsdtBean usdtBean) {
        if (usdtBean.getUsdt() != null) {
            text_usdt.setText(usdtBean.getUsdt().getUserable() + "");
        }
        if (usdtBean.getBox() != null) {
            text_box.setText(usdtBean.getBox().getUserable() + "");
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


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.text_usdt_charge:
                if (usdtBean != null && usdtBean.getUsdt() != null) {
                    intent = new Intent(getContext(), RechargeActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getUsdt().getCoinTypeId());
                    startActivity(intent);
                }
                break;
            case R.id.text_usdt_carry:
                if (usdtBean != null && usdtBean.getUsdt() != null) {
                    intent = new Intent(getContext(), DrawActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getUsdt().getCoinTypeId());
                    startActivity(intent);
                }
                break;
            case R.id.text_box_charge:
                if (usdtBean != null && usdtBean.getBox() != null) {
                    intent = new Intent(getContext(), RechargeActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getBox().getCoinTypeId());
                    startActivity(intent);
                }
                break;
            case R.id.text_box_carry:
                if (usdtBean != null && usdtBean.getBox() != null) {
                    intent = new Intent(getContext(), DrawActivity.class);
                    intent.putExtra("usdtBean", usdtBean);
                    intent.putExtra("coinTypeId", usdtBean.getBox().getCoinTypeId());
                    startActivity(intent);
                }
                break;
            case R.id.text_usd:
                showUsdt();
                break;

        }
    }


    public void showUsdt() {
        Dialog dialog = new Dialog(getContext(), R.style.dialog_bottom_full);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_map, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(view);
        view.findViewById(R.id.text_usd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usdtBean != null && usdtBean.getUsdt() != null) {
                    coinTypeId = usdtBean.getUsdt().getCoinTypeId();
                    text_usd.setText("USDT");
                    onRefresh();
                }
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.text_bc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usdtBean != null && usdtBean.getBox() != null) {
                    coinTypeId = usdtBean.getBox().getCoinTypeId();
                    text_usd.setText("BOX");
                    onRefresh();
                }
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
