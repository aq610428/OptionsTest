package com.jkabe.app.box.box.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.TakeAdapter2;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OrderBean;
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
 * @date: 2020/9/22
 * @name:CompletedFragment
 */
public class CompletedFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener, NetWorkListener {
    private View rootView;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<OrderBean> beanList = new ArrayList<>();
    private TakeAdapter2 payAdapter;
    private int page = 1;
    private int limit = 10;
    private boolean isRefresh;
    private NoDataView noDataView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_completed, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        noDataView = getView(rootView, R.id.mNoDataView);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        swipe_target = getView(rootView, R.id.swipe_target);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(linearLayoutManager);
        noDataView.textView.setText("无更多订单");
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



    /******商品列表*****/
    public void query() {
        showProgressDialog(getActivity(), false);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&orderStatus=1" + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("orderStatus", "1");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MallGood_ORDER_LIST, params, Api.MallGood_ORDER_LIST_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.MallGood_ORDER_LIST_ID:
                        List<OrderBean> beans = JsonParse.getOrderBeanJSON(object);
                        if (beans != null && beans.size() > 0) {
                            setAdapter(beans);
                        } else {
                            if (isRefresh && page > 1) {
                                ToastUtil.showToast("无更多订单");
                            } else {
                                noDataView.setVisibility(View.VISIBLE);
                                swipeToLoadLayout.setVisibility(View.GONE);
                            }
                        }
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

    private void setAdapter(List<OrderBean> beans) {
        noDataView.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            beanList.clear();
            beanList.addAll(beans);
            payAdapter = new TakeAdapter2(this, beanList);
            swipe_target.setAdapter(payAdapter);
        } else {
            beanList.addAll(beans);
            payAdapter.setData(beanList);
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
    protected void lazyLoad() {

    }
}
