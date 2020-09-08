package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.icadBean;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author: zt
 * @date: 2020/9/8
 * @name:PassworadActivity
 */
public class PassworadActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private icadBean bean;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_passworad);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("流量查询");
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
    protected void initData() {
        CarInfo carInfo = SaveUtils.getCar();
        if (carInfo != null && !Utility.isEmpty(carInfo.getIccid())) {
            query();
        } else {
            ToastUtil.showToast("您的流量卡不存在，快去绑定吧~");
        }
    }


    public void query() {
        String sign = "iccid=" + SaveUtils.getCar().getIccid() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("iccid", SaveUtils.getCar().getIccid());
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.RESET_FLOW_DEVICE, params, Api.RESET_FLOW_DEVICEE_ID, this);
    }


    public void queryList() {
        String sign = "iccid=" + SaveUtils.getCar().getIccid() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("iccid", SaveUtils.getCar().getIccid());
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.RESET_FLOW_List, params, Api.RESET_FLOW_List_ID, this);
    }


    public void queryCode() {
        String sign = "iccid=" + SaveUtils.getCar().getIccid() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("iccid", SaveUtils.getCar().getIccid());
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.RESET_FLOW_CODE, params, Api.RESET_FLOW_CODE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.RESET_FLOW_DEVICEE_ID:
                        bean = JsonParse.getJSONicon(object);
                        if (bean != null) {
                            updateView();
                        }
                        break;
                    case Api.RESET_FLOW_List_ID:

                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void updateView() {

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
