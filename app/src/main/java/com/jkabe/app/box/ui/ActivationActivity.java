package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:ActivationActivity
 */
public class ActivationActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_key;
    private TextView text_usd;
    UsdtBean usdtBean;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_activation);
    }

    @Override
    protected void initView() {
        text_usd = getView(R.id.text_usd);
        text_key = getView(R.id.text_key);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_key.setOnClickListener(this);
        title_text_tv.setText("激活挖矿");
        TypefaceUtil tfUtil = new TypefaceUtil(this, "OpenSans-Light.ttf");
        tfUtil.setTypeface(text_usd, false);
        query();
    }


    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MINING_BAL_BOX, params, Api.MINING_BAL_BOX_ID, this);
    }


    /*****激活*****/
    public void activation() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ACTIVE_BOX, params, Api.GET_ACTIVE_BOX_ID, this);
    }


    /******查询个人资料*****/
    public void queryUser() {
        showProgressDialog(this, false);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MEID_USER, params, Api.GET_MEID_USER_ID, this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_key:
                activation();
                break;
        }
    }


    @Override
    protected void initData() {
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_ACTIVE_BOX_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        queryUser();
                        break;
                    case Api.MINING_BAL_BOX_ID:
                        usdtBean = JsonParse.getJSONObjectUsdtBean(object);
                        if (usdtBean != null) {
                            updateView();
                        }
                        break;
                    case Api.GET_MEID_USER_ID:
                        UserInfo info = JsonParse.getUserInfo(object);
                        if (info != null) {
                            SaveUtils.saveInfo(info);
                        }
                        finish();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void updateView() {
        if (usdtBean.getUsdt() != null) {
            text_usd.setText("可用USDT:" + usdtBean.getUsdt().getUserable() + "");
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
