package com.jkabe.app.box.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jkabe.app.box.adapter.ConfimAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.app.box.bean.PayBean;
import com.jkabe.app.box.box.OrderPayActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.PayUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.box.R;
import com.jkabe.box.alipay.AuthResult;
import com.jkabe.box.alipay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/9/17
 * @name:ConfirmActivity
 */
public class ConfirmActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_sumber, text_price,text_total_next;
    private RecyclerView recyclerView;
    private List<ImageInfo> list = new ArrayList<>();
    private ConfimAdapter adapter;
    private RelativeLayout ll_address;
    private TextView text_wechat, text_alipay;
    private IWXAPI api;
    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_AUTH_FLAG = 2;
    private int isPay=1;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_confirm);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
    }

    @Override
    protected void initView() {
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
        list = Constants.getDate();

        adapter = new ConfimAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
                pay();
                break;
            case R.id.text_alipay:
                isPay=2;
                text_alipay.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_ailipay, 0, R.mipmap.ic_choose_un, 0);
                text_wechat.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_wehcat, 0, R.mipmap.ic_choose, 0);
                break;
            case R.id.text_wechat:
                isPay=1;
                text_wechat.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_wehcat, 0, R.mipmap.ic_choose_un, 0);
                text_alipay.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_ailipay, 0, R.mipmap.ic_choose, 0);
                break;
        }
    }

    /******微信支付*****/
    private void pay() {
        String str = "{\"appid\":\"wxb4ba3c02aa476ea1\",\"partnerid\":\"1900006771\",\"package\":\"Sign=WXPay\",\"noncestr\":\"c7886c4ed157f38fdd3ae0baad9726ac\",\"timestamp\":1602224908,\"prepayid\":\"wx091428285854906d23ae226a0647d50000\",\"sign\":\"1A835FCA527CB435098C04F0CB288EE3\"}";
        PayBean payBean = JsonParse.getPayJson(str);
        if (isPay==1){
            PayUtils.wechatPay(this,payBean,api);
        }else {
            PayUtils.AliPay(this,mHandler,"");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        String pay=PreferenceUtils.getPrefString(this,"pay","1002");
        switch (pay){
            case "0":
                ToastUtil.showToast("支付正常");
                break;
            case "-1":
                ToastUtil.showToast("签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。");
                break;
            case "-2":
                ToastUtil.showToast("用户取消");
                break;
            default:
                PreferenceUtils.setPrefString(this,"pay","1002");
        }
    }



    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            if (msg.what==SDK_PAY_FLAG){
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    LogUtils.e("支付成功"+payResult);
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    LogUtils.e("支付失败"+payResult);
                }
            }
        };
    };



}
