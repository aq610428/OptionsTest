package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.adapter.OrderListAdapter;
import com.jkabe.app.box.adapter.OrderListAdapter1;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.bean.OrderVo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
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
public class OrderDetileActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_name, text_address, text_price;
    private OrderVo orderBean;
    private RecyclerView recyclerView;
    private OrderListAdapter1 orderListAdapter;
    private TextView text_order, text_pay, text_logistics, text_baill, text_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_orderdetile);
    }

    @Override
    protected void initView() {
        text_pay= getView(R.id.text_pay);
        text_logistics= getView(R.id.text_logistics);
        text_baill= getView(R.id.text_baill);
        text_next= getView(R.id.text_next);

        text_order= getView(R.id.text_order);
        text_price = getView(R.id.text_price);
        text_address = getView(R.id.text_address);
        text_name = getView(R.id.text_name);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("订单详情");
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        String orderId = getIntent().getStringExtra("id");
        query(orderId);
    }


    /******去支付*****/
    public void query(String orderId) {
        showProgressDialog(this, false);
        String sign = "id=" + orderId + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", orderId);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_ORDER, params, Api.PAY_REMOVE_ORDER_ID, this);
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
            orderListAdapter = new OrderListAdapter1(this, beans);
            recyclerView.setAdapter(orderListAdapter);
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

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }
}
