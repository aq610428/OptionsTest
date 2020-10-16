package com.jkabe.box.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.jkabe.app.box.box.OrderPayActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.weight.ActivityTaskManager;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.box.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.e("errCode = " + resp.errCode);


        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int result = 0;
            switch (resp.errCode) {
                case 0:
                    LogUtils.e("支付成功执行--------" + resp.errCode);
                    ToastUtil.showToast("支付成功");
                    result=2;
                    break;
                case -1:
                    ToastUtil.showToast("签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。");
                    result=1;
                    break;
                case -2:
                    ToastUtil.showToast("用户取消支付");
                    result=1;
                    break;
            }
            Intent intent = new Intent(this, OrderPayActivity.class);
            intent.putExtra("position", result);
            startActivity(intent);
            ActivityTaskManager.removeActivity("ConfirmActivity");
            finish();
        }
    }
}