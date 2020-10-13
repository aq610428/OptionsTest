package com.jkabe.app.box.box.fragement;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.PayAdapter;
import com.jkabe.app.box.adapter.TakeAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.bean.PayBean;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.MeasureWidthUtils;
import com.jkabe.app.box.util.PayUtils;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.NoDataView;
import com.jkabe.box.R;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/9/22
 * @name:PayFragment
 */
public class PayFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener, NetWorkListener {
    private View rootView;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<OrderBean> beanList = new ArrayList<>();
    private PayAdapter payAdapter;
    private int page = 1;
    private int limit = 10;
    private boolean isRefresh;
    private NoDataView noDataView;
    private PayBean payBean;
    private IWXAPI api;


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
        api = WXAPIFactory.createWXAPI(getContext(), Constants.APP_ID);
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


    /******取消订单*****/
    public void cancelOrder(String orderId) {
        showProgressDialog(getActivity(), false);
        String sign = "id=" + orderId + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", orderId);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.CANCAL_ORDER_LIST, params, Api.CANCAL_ORDER_LIST_ID, this);
    }


    /******去支付*****/
    public void payOrder(String orderId) {
        showProgressDialog(getActivity(), false);
        String sign = "id=" + orderId + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", orderId);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_ORDER_LIST, params, Api.PAY_ORDER_LIST_ID, this);
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
                    case Api.CANCAL_ORDER_LIST_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        onRefresh();
                        break;
                    case Api.PAY_ORDER_LIST_ID:
                        payBean = JsonParse.getPayJson(object);
                        if (payBean != null) {
                            update();
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
    private void update() {
        PayUtils.wechatPay(getActivity(), payBean, api);
    }


    private void setAdapter(List<OrderBean> beans) {
        noDataView.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            beanList.clear();
            beanList.addAll(beans);
            payAdapter = new PayAdapter(this, beanList);
            swipe_target.setAdapter(payAdapter);
        } else {
            beanList.addAll(beans);
            payAdapter.setData(beanList);
        }
    }


    public void showTip(OrderBean orderBean) {
        final Dialog dialog = new Dialog(getContext(), R.style.dialog_bottom_full);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout_pay, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = MeasureWidthUtils.getScreenWidth(getContext());
        view.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.share_animation);


        view.findViewById(R.id.text_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payOrder(orderBean.getId());
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.text_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payOrder(orderBean.getId());
                dialog.dismiss();
            }
        });
        dialog.show();
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
