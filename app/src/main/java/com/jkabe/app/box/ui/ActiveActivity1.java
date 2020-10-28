package com.jkabe.app.box.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.box.OrderDetileActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/27
 * @name:赠送挖矿
 */
public class ActiveActivity1 extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private TextView text_userableNum, text_exchange, text_usedNum;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ctive);
        BaseApplication.activityTaskManager.putActivity("ActiveActivity1", this);
    }

    @Override
    protected void initView() {
        text_userableNum = getView(R.id.text_userableNum);
        text_exchange = getView(R.id.text_exchange);
        text_usedNum = getView(R.id.text_usedNum);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_exchange.setOnClickListener(this);
        title_text_tv.setText("消费额度");
        TypefaceUtil.setTextType(this, "DINOT-Bold.ttf", text_userableNum);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_exchange:
                checkData();
                break;

        }
    }

    @Override
    protected void initData() {
        query();
    }


    private int miningtype;

    private void checkData() {
        if (consumeAmount < oneParam) {
            ToastUtil.showToast("消费额度不足，无法兑换");
            return;
        }
        if (consumeAmount >= oneParam && consumeAmount < twoParam) {
            miningtype = 1;
            showDelete();
        } else if (consumeAmount == twoParam) {
            miningtype = 2;
            showDelete();
        } else if (consumeAmount > oneParam && consumeAmount > twoParam) {
            showDialog();
        }


    }


    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_MALL, params, Api.PAY_REMOVE_MALL_ID, this);
    }

    /*****去兑换*****/
    public void query1() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&miningtype=" + miningtype + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("miningtype", miningtype + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_CHANCE_MALL, params, Api.PAY_CHANCE_MALL_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.PAY_REMOVE_MALL_ID:
                        updateView(object);
                        break;
                    case Api.PAY_CHANCE_MALL_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        query();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    double consumeAmount, oneParam, twoParam;

    private void updateView(JSONObject object) {
        JSONObject jsonObject1 = object.optJSONObject("result");
        consumeAmount = jsonObject1.optDouble("consumeAmount");
        oneParam = jsonObject1.optDouble("oneParam");
        twoParam = jsonObject1.optDouble("twoParam");
        text_userableNum.setText("￥" + consumeAmount);
        text_usedNum.setText("1. 消费额度满" + oneParam + "元即可兑换一次150U挖矿机会 \n" + "2. 消费额度满" + twoParam + "元即可兑换一次750U挖矿机会" + "\n" + "3. 挖矿机会暂时不支持转让");
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }


    public void showDelete() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText("消费额度是否兑换？");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query1();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void showDialog() {
        Dialog dialog = new Dialog(this, R.style.dialog_bottom_full);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_pay1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miningtype = 1;
                dialog.dismiss();
                showDelete();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miningtype = 2;
                dialog.dismiss();
                showDelete();
            }
        });
        dialog.show();
    }

}
