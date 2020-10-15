package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.adapter.LogisticsAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.LocgistBean;
import com.jkabe.app.box.bean.OrderBean;
import com.jkabe.app.box.bean.OrderVo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/10/10
 * @name:查看物流
 */
public class LogisticsActivity extends BaseActivity implements NetWorkListener {
    private OrderBean.ItemsBean itemsBean;
    private OrderVo.GooditemsBean goodBean;
    private TextView title_text_tv, title_left_btn, text_name, text_address, text_order,text_company;
    private RecyclerView recyclerView;
    private ImageView iv_logo;
    private LogisticsAdapter adapter;
    private List<LocgistBean> beans = new ArrayList<>();

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_logistics);
        BaseApplication.activityTaskManager.putActivity("LogisticsActivity", this);
    }

    @Override
    protected void initView() {
        text_company = getView(R.id.text_company);
        text_order = getView(R.id.text_order);
        iv_logo = getView(R.id.iv_logo);
        text_address = getView(R.id.text_address);
        text_name = getView(R.id.text_name);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        recyclerView = getView(R.id.recyclerView);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("物流信息");
    }

    @Override
    protected void initData() {
        itemsBean = (OrderBean.ItemsBean) getIntent().getSerializableExtra("ItemsBean");
        goodBean = (OrderVo.GooditemsBean) getIntent().getSerializableExtra("goodBean");
        text_address.setText("收货地址：" + getIntent().getStringExtra("address"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (itemsBean != null) {
            text_order.setText("物流单号:" + itemsBean.getExpressorder());
            text_name.setText("订单编号:" + itemsBean.getOrderid());
            GlideUtils.setImageUrl(itemsBean.getSmallImg(), iv_logo);
            if (!Utility.isEmpty(itemsBean.getExpressorder())) {
                query(itemsBean.getExpressorder(), itemsBean.getExpresscompany());
            }
            text_company.setText(itemsBean.getExpresscompanyname());
        } else if (goodBean != null) {
            text_company.setText(goodBean.getExpresscompanyname());
            text_name.setText("订单编号:" + goodBean.getOrderid());
            text_order.setText("物流单号:" + goodBean.getExpressorder());
            GlideUtils.setImageUrl(goodBean.getSmallImg(), iv_logo);
            if (!Utility.isEmpty(goodBean.getExpressorder())) {
                query(goodBean.getExpressorder(), goodBean.getExpresscompany());
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
        }
    }

    /******查看物流*****/
    public void query(String orderId, String name) {
        showProgressDialog(this, false);
        String sign = "expresscompany=" + name + "&expressorder=" + orderId + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("expresscompany", name);
        params.put("expressorder", orderId);

        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_EXO, params, Api.PAY_REMOVE_EXO_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.PAY_REMOVE_EXO_ID:
                        beans = JsonParse.getLocgistBeanJSON(object);
                        if (beans != null && beans.size() > 0) {
                            update();
                        }
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }

        stopProgressDialog();
    }


    public void update() {
        adapter=new LogisticsAdapter(this,beans);
        recyclerView.setAdapter(adapter);
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
