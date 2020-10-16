package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.adapter.OrderListAdapter3;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OrderVo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/10/13
 * @name:OrderDetileActivity
 */
public class OrderDetileActivity1 extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_name, text_address, text_price, text_postage;
    public OrderVo orderBean;
    private RecyclerView recyclerView;
    private OrderListAdapter3 orderListAdapter;
    private TextView text_order, text_pay, text_logistics, text_baill, text_next, text_message, text_paytime, text_delete;
    private TextView text_stats;
    private String orderId, orderStatus;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_orderdetile1);
        BaseApplication.activityTaskManager.putActivity("OrderDetileActivity1", this);
    }

    @Override
    protected void initView() {
        text_delete = getView(R.id.text_delete);
        text_paytime = getView(R.id.text_paytime);
        text_stats = getView(R.id.text_stats);
        text_postage = getView(R.id.text_postage);
        text_pay = getView(R.id.text_pay);
        text_logistics = getView(R.id.text_logistics);
        text_baill = getView(R.id.text_baill);
        text_next = getView(R.id.text_next);
        text_message = getView(R.id.text_message);
        text_order = getView(R.id.text_order);
        text_price = getView(R.id.text_price);
        text_address = getView(R.id.text_address);
        text_name = getView(R.id.text_name);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_delete.setOnClickListener(this);
        title_text_tv.setText("订单详情");
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        orderId = getIntent().getStringExtra("id");
        orderStatus = getIntent().getStringExtra("orderStatus");
        query();
    }


    /******查询订单详情*****/
    public void query() {
        showProgressDialog(this, false);
        String sign = "id=" + orderId + "&orderStatus=" + orderStatus + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", orderId + "");
        params.put("orderStatus", orderStatus + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_ORDER, params, Api.PAY_REMOVE_ORDER_ID, this);
    }

    /******催发货*****/
    public void showUrge(String id) {
        showProgressDialog(this, false);
        String sign = "id=" + id + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", id);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_GOOD, params, Api.PAY_REMOVE_GOOD_ID, this);
    }


    /******确认收货*****/
    public void payConfirm(String orderId) {
        showProgressDialog(this, false);
        String sign = "id=" + orderId + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", orderId);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_GOODS, params, Api.PAY_REMOVE_GOODS_ID, this);
    }

    /******删除订单*****/
    public void delete() {
        showProgressDialog(this, false);
        String sign = "id=" + orderId + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", orderId);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_DELETE, params, Api.PAY_REMOVE_DELETE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.PAY_REMOVE_ORDER_ID:
                        orderBean = JsonParse.getorderBean(object);
                        if (orderBean != null) {
                            updateView();
                        }
                        break;
                    case Api.PAY_REMOVE_DELETE_ID:
                    case Api.PAY_REMOVE_GOODS_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        finish();
                        break;
                    case Api.PAY_REMOVE_GOOD_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }

        stopProgressDialog();
    }


    private void updateView() {
        List<OrderVo.GooditemsBean> beans = orderBean.getGooditems();
        if (beans != null && beans.size() > 0) {
            orderListAdapter = new OrderListAdapter3(this, beans);
            recyclerView.setAdapter(orderListAdapter);
            if (Utility.isEmpty(beans.get(0).getExpresscompanyname())) {
                text_logistics.setText("物流公司: --");
            } else {
                text_logistics.setText("物流公司: " + beans.get(0).getExpresscompanyname());
            }

            if (Utility.isEmpty(beans.get(0).getExpressorder())) {
                text_baill.setText("快递单号: --");
            } else {
                text_baill.setText("快递单号: " + beans.get(0).getExpressorder());
            }
            switch (beans.get(0).getOrderStatus()) {
                case 2://已发货
                    text_stats.setText("待发货");
                    break;
                case 3://已发货
                    text_stats.setText("待收货");
                    break;
                case 4://已确认收货
                    text_stats.setText("已收货");
                    text_delete.setVisibility(View.VISIBLE);
                    break;
                case 5://订单取消
                    text_stats.setText("已取消");
                    text_delete.setVisibility(View.VISIBLE);
                    break;
                case 8://订单已完成
                    text_stats.setText("已完成");
                    text_delete.setVisibility(View.VISIBLE);
                    break;
            }

        }
        OrderVo.OrderinfoBean orderinfoBean = orderBean.getOrderinfo();
        if (orderinfoBean != null) {
            text_name.setText(orderinfoBean.getReceiveName() + "  " + orderinfoBean.getReceiveMobile());
            text_address.setText(orderinfoBean.getReceiveAddress());
            text_price.setText("￥" + orderinfoBean.getGoodMoney());
            text_order.setText("订单编号: " + orderinfoBean.getOrderid());
            if (orderinfoBean.getPayType() == 1) {
                text_pay.setText("支付方式: 微信支付");
            } else if (orderinfoBean.getPayType() == 2) {
                text_pay.setText("支付方式: 支付宝支付");
            } else {
                text_pay.setText("支付方式: --");
            }

            if (!Utility.isEmpty(orderinfoBean.getStringPaytime())) {
                String stringPaytime = orderinfoBean.getStringPaytime();
                text_paytime.setText("支付时间: " + stringPaytime.substring(0, 10) + " " + stringPaytime.substring(stringPaytime.length() - 8, stringPaytime.length()));
            } else {
                text_paytime.setText("支付时间: --");
            }

            if (!Utility.isEmpty(orderinfoBean.getStringOrdertime())) {
                String stringOrdertime = orderinfoBean.getStringOrdertime();
                text_next.setText("下单时间: " + stringOrdertime.substring(0, 10) + " " + stringOrdertime.substring(stringOrdertime.length() - 8, stringOrdertime.length()));
            } else {
                text_next.setText("下单时间: --");
            }


            if (Utility.isEmpty(orderinfoBean.getMessage())) {
                text_message.setText("买家留言: --");
            } else {
                text_message.setText("买家留言: " + orderinfoBean.getMessage());
            }

            if (orderinfoBean.getPostage() == 0) {
                text_postage.setText("免邮");
            } else {
                text_postage.setText("￥" + orderinfoBean.getPostage());
            }


        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_delete:
                delete();
                break;
        }
    }


    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }


}
