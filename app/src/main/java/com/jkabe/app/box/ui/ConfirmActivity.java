package com.jkabe.app.box.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.adapter.ConfimAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.bean.CartBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.GoodBean;
import com.jkabe.app.box.bean.PayBean;
import com.jkabe.app.box.box.OrderPayActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.PayUtils;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import com.jkabe.box.alipay.PayResult;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/9/17
 * @name:ConfirmActivity
 */
public class ConfirmActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_sumber, text_price, text_total_next, text_total;
    private RecyclerView recyclerView;
    private List<CartBean> beanList = new ArrayList<>();
    private ConfimAdapter adapter;
    private RelativeLayout ll_address;
    private TextView text_wechat, text_alipay, text_address, text_def, text_mobile;
    private IWXAPI api;
    public static final int SDK_PAY_FLAG = 1;
    private int isPay = 1;
    private EditText text_remark;
    private PayBean payBean;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_confirm);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
    }

    @Override
    protected void initView() {
        text_remark = getView(R.id.text_remark);
        text_mobile = getView(R.id.text_mobile);
        text_def = getView(R.id.text_def);
        text_address = getView(R.id.text_address);
        text_total = getView(R.id.text_total);
        text_total_next = getView(R.id.text_total_next);
        text_alipay = getView(R.id.text_alipay);
        text_wechat = getView(R.id.text_wechat);
        text_price = getView(R.id.text_price);
        ll_address = getView(R.id.ll_address);
        text_sumber = getView(R.id.text_sumber);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_sumber.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        text_wechat.setOnClickListener(this);
        text_alipay.setOnClickListener(this);

        TypefaceUtil.setTextType(this, "DINOT-Bold.ttf", text_total_next);
        TypefaceUtil.setTextType(this, "DINOT-Bold.ttf", text_price);
        title_text_tv.setText("确认订单");
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        beanList = (List<CartBean>) getIntent().getSerializableExtra("beanList");
        if (beanList != null && beanList.size() > 0) {
            updateView();
        } else {
            GoodBean goodBean = (GoodBean) getIntent().getSerializableExtra("goodBean");
            if (goodBean != null) {
                beanList = new ArrayList<>();
                CartBean cartBean = new CartBean();
                cartBean.setGoodNumber(goodBean.getGoodNumber());
                cartBean.setGoodid(goodBean.getId());
                cartBean.setSmallImg(goodBean.getGoodsImageList().get(0).getGoodImg());
                cartBean.setSellPrice(goodBean.getSellPrice());
                cartBean.setTitle(goodBean.getTitle());
                beanList.add(cartBean);
                updateView();
            }

        }

    }


    public void updateView() {
        adapter = new ConfimAdapter(this, beanList);
        recyclerView.setAdapter(adapter);

        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < beanList.size(); i++) {
            total = BigDecimalUtils.add(total, BigDecimalUtils.mul(new BigDecimal(beanList.get(i).getSellPrice()), new BigDecimal(beanList.get(i).getGoodNumber())));
        }
        text_total_next.setText("￥" + BigDecimalUtils.round(total, 0).toPlainString());
        text_total.setText("￥" + BigDecimalUtils.round(total, 0).toPlainString());
        text_price.setText(BigDecimalUtils.round(total, 0).toPlainString());
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.ll_address:
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.text_sumber:
                checkOrder();
                pay();
                break;
            case R.id.text_alipay:
                isPay = 2;
                text_alipay.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_ailipay, 0, R.mipmap.ic_choose_un, 0);
                text_wechat.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_wehcat, 0, R.mipmap.ic_choose, 0);
                break;
            case R.id.text_wechat:
                isPay = 1;
                text_wechat.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_wehcat, 0, R.mipmap.ic_choose_un, 0);
                text_alipay.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_ailipay, 0, R.mipmap.ic_choose, 0);
                break;
        }
    }

    /******检测订单配置******/
    private void checkOrder() {
        String address = text_address.getText().toString();
        if (Utility.isEmpty(address)) {
            ToastUtil.showToast("收货地址不能为空");
            return;
        }
        String shoppingids = "";
        for (int i = 0; i < beanList.size(); i++) {
            if (i == 0) {
                shoppingids = beanList.get(i).getId();
            } else {
                shoppingids = shoppingids + "," + beanList.get(i).getId();
            }
        }
        query(shoppingids);
    }


    /******生成订单*****/
    public void query(String shoppingids) {
        showProgressDialog(this, false);
        String remark = text_remark.getText().toString();
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId();
        if (!Utility.isEmpty(remark)) {
            sign = sign + "&message=" + remark;
        }
        sign = sign + "&partnerid=" + Constants.PARTNERID + "&receiveAddress=" + receiveAddress + "&receiveMobile=" + receiveMobile + "&receiveName=" + receiveName + "&shoppingids=" + shoppingids + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        if (!Utility.isEmpty(remark)) {
            params.put("message", remark);
        }
        params.put("partnerid", Constants.PARTNERID);
        params.put("receiveAddress", receiveAddress);
        params.put("receiveMobile", receiveMobile);
        params.put("receiveName", receiveName);

        params.put("shoppingids", shoppingids);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.post(Api.MallGood_PAY_SAVE, params, Api.MallGood_PAY_SAVE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.MallGood_PAY_SAVE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        payBean = JsonParse.getPayJson(object);
                        if (payBean != null) {
                            pay();
                        }
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        } else {
            ToastUtil.showToast(commonality.getErrorDesc());
        }
        stopProgressDialog();
    }


    String receiveName, receiveAddress, receiveMobile;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101 && data != null) {
            AddressBean addressBean = (AddressBean) data.getSerializableExtra("addressBean");
            if (addressBean != null) {
                text_def.setVisibility(View.GONE);
                receiveAddress = addressBean.getProvince() + addressBean.getCity() + addressBean.getArea() + addressBean.getAddress();
                receiveName = addressBean.getReceivename();
                receiveMobile = addressBean.getMobile();
                text_address.setText(receiveAddress);
                text_mobile.setText(receiveName + " " + receiveMobile);
            }
        }
    }

    /******微信支付*****/
    private void pay() {
        if (isPay == 1) {
            PayUtils.wechatPay(this, payBean, api);
        } else {
            PayUtils.AliPay(this, mHandler, "");
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


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            if (msg.what == SDK_PAY_FLAG) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    LogUtils.e("支付成功" + payResult);
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    LogUtils.e("支付失败" + payResult);
                    startActivity(new Intent(ConfirmActivity.this, OrderPayActivity.class));
                }
            }
        }

        ;
    };


}
