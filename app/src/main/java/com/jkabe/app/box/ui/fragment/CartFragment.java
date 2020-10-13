package com.jkabe.app.box.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.CartAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CartBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.ui.ConfirmActivity;
import com.jkabe.app.box.ui.MainActivity;
import com.jkabe.app.box.ui.WareDeilActivity;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.NoDataView1;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/9/30
 * @name:CartFragment
 */
public class CartFragment extends BaseFragment implements NetWorkListener, View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private View rootView;
    private TextView text_choose, text_edit, text_balance, text_delete, text_total;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private CartAdapter adapter;
    private List<CartBean> beanList = new ArrayList<>();
    private LinearLayout ll_total;
    private int page = 1;
    private int limit = 10;
    private boolean isRefresh;
    private NoDataView1 mNoDataView1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_cart, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
        cancelAll();
        query();
    }


    private void initView() {
        mNoDataView1 = getView(rootView, R.id.noDataView1);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        text_total = getView(rootView, R.id.text_total);
        text_delete = getView(rootView, R.id.text_delete);
        ll_total = getView(rootView, R.id.ll_total);
        text_balance = getView(rootView, R.id.text_balance);
        text_edit = getView(rootView, R.id.text_edit);
        swipe_target = getView(rootView, R.id.swipe_target);
        text_choose = getView(rootView, R.id.text_choose);
        text_choose.setOnClickListener(this);
        text_edit.setOnClickListener(this);
        text_balance.setOnClickListener(this);
        text_delete.setOnClickListener(this);
        TypefaceUtil.setTextType(getActivity(), "ttp.TTF", text_total);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(manager);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
    }


    @Override
    protected void lazyLoad() {

    }


    /******商品列表*****/
    public void query() {
        showProgressDialog(getActivity(), false);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GOODD_MALL_QUERY, params, Api.GOODD_MALL_QUERY_ID, this);
    }


    /******删除商品*****/
    public void delete() {
        String idList = "";
        for (Map.Entry<Integer, CartBean> entry : adapter.map.entrySet()) {
            if (Utility.isEmpty(idList)) {
                idList = idList + entry.getValue().getId();
            } else {
                idList = idList + "," + entry.getValue().getId();
            }
        }
        showProgressDialog(getActivity(), false);
        String sign = "idList=" + idList + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("idList", idList);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MallGood_REMOVE, params, Api.MallGood_REMOVE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GOODD_MALL_QUERY_ID:
                        List<CartBean> beans = JsonParse.getCartBeanJson(object);
                        if (beans != null && beans.size() > 0) {
                            setAdapter(beans);
                        } else {
                            if (isRefresh && page > 1) {
                                ToastUtil.showToast("无更多商品");
                            } else {
                                mNoDataView1.setVisibility(View.VISIBLE);
                                swipeToLoadLayout.setVisibility(View.GONE);
                            }
                        }
                        break;
                    case Api.MallGood_REMOVE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        isChoose = false;
                        text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose, 0, 0, 0);
                        text_choose.setText("全选");
                        ll_total.setVisibility(View.VISIBLE);
                        text_delete.setVisibility(View.GONE);
                        text_balance.setVisibility(View.VISIBLE);
                        text_edit.setText("编辑");
                        cancelAll();
                        update();
                        query();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }


    private void setAdapter(List<CartBean> beans) {
        mNoDataView1.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            beanList.clear();
            beanList.addAll(beans);
            adapter = new CartAdapter(this, beanList);
            swipe_target.setAdapter(adapter);
        } else {
            beanList.addAll(beans);
            adapter.setData(beanList);
        }

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), WareDeilActivity.class);
                intent.putExtra("goodId", beanList.get(position).getGoodid());
                startActivity(intent);
            }
        });

        update();
    }


    public void update() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            TextView text_num = mainActivity.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.text_num);
            if (beanList.size() > 0) {
                text_num.setVisibility(View.VISIBLE);
                text_num.setText(beanList.size() + "");
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


    private boolean isChoose;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_choose:
                if (!isChoose) {
                    chooseAll();
                } else {
                    cancelAll();
                }
                break;
            case R.id.text_edit:
                setView();
                break;
            case R.id.text_balance:
                Intent intent = new Intent(getContext(), ConfirmActivity.class);
                intent.putExtra("beanList", (Serializable) beanList);
                startActivity(intent);
                break;
            case R.id.text_delete:
                if (adapter != null && adapter.map.size() == 0) {
                    ToastUtil.showToast("请选择商品");
                    return;
                } else {
                    delete();
                }

                break;

        }
    }


    private void setView() {
        String text = text_edit.getText().toString();
        if ("编辑".equals(text)) {
            ll_total.setVisibility(View.GONE);
            text_balance.setVisibility(View.GONE);
            text_delete.setVisibility(View.VISIBLE);
            text_edit.setText("完成");
        } else {
            ll_total.setVisibility(View.VISIBLE);
            text_delete.setVisibility(View.GONE);
            text_balance.setVisibility(View.VISIBLE);
            text_edit.setText("编辑");
        }

    }


    /*****全选*****/
    public void chooseAll() {
        isChoose = true;
        text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose_un, 0, 0, 0);
        text_choose.setText("取消");
        BigDecimal total = BigDecimal.ZERO;
        if (adapter != null) {
            for (int i = 0; i < beanList.size(); i++) {
                adapter.map.put(i, beanList.get(i));
                total = BigDecimalUtils.add(total, BigDecimalUtils.mul(new BigDecimal(beanList.get(i).getSellPrice()),new BigDecimal(beanList.get(i).getGoodNumber())));
            }
            adapter.notifyItemRangeChanged(0, beanList.size());
            text_total.setText("￥" + total.toPlainString());
        }
    }


    /*****取消全选*****/
    public void cancelAll() {
        isChoose = false;
        text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose, 0, 0, 0);
        text_choose.setText("全选");
        if (adapter != null) {
            adapter.map.clear();
            adapter.notifyItemRangeChanged(0, beanList.size());
            text_total.setText("￥0");
        }
    }

    /*****合计*****/
    public void updateView() {
        BigDecimal total = BigDecimal.ZERO;
        if (adapter != null && adapter.map != null) {
            for (Map.Entry<Integer, CartBean> entry : adapter.map.entrySet()) {
                total = BigDecimalUtils.add(total, BigDecimalUtils.mul(new BigDecimal(entry.getValue().getSellPrice()),new BigDecimal(entry.getValue().getGoodNumber())));
            }
            text_total.setText("￥" + total.toPlainString());
        }
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
