package com.jkabe.app.box.box;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/31
 * @name:交易密码
 */
public class ResetActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, btn_code;
    private EditText et_mobile, e_password, et_code;
    private Button btn_next;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reset);
    }

    @Override
    protected void initView() {
        et_code = getView(R.id.et_code);
        btn_code = getView(R.id.btn_code);
        e_password = getView(R.id.e_password);
        et_mobile = getView(R.id.et_mobile);
        btn_next = getView(R.id.btn_next);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("重置交易密码");
        btn_next.setOnClickListener(this);
        btn_code.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.btn_next:
                checkData();
                break;
            case R.id.btn_code:
                check();
                break;
        }
    }

    private void check() {
        String phone = et_mobile.getText().toString().trim();
        if (Utility.isEmpty(phone)) {
            ToastUtil.showToast("手机号码不能为空");
        } else if (!SystemTools.isMobileNumber(phone)) {
            ToastUtil.showToast("手机号码不合法");
        }else{
            queryCode();
        }
    }




    @Override
    protected void initData() {

    }


    private void checkData() {
        String phone = et_mobile.getText().toString().trim();
        String password = e_password.getText().toString().trim();
        String code = et_code.getText().toString().trim();
        if (Utility.isEmpty(phone)) {
            ToastUtil.showToast("手机号码不能为空");
        } else if (Utility.isEmpty(password)) {
            ToastUtil.showToast("交易密码不能为空");
        }  else if (Utility.isEmpty(code)) {
            ToastUtil.showToast("验证码不能为空");
        }else {
            query();
        }
    }



    public void query() {
        String mobile = et_mobile.getText().toString().trim();
        String password = e_password.getText().toString().trim();
        String code = et_code.getText().toString().trim();
        String sign = "loginname=" + mobile + "&partnerid=" + Constants.PARTNERID +"&password="+ Md5Util.encode(password)+"&vcode="+code+ Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("loginname", mobile);
        params.put("partnerid", Constants.PARTNERID);
        params.put("password",  Md5Util.encode(password));
        params.put("vcode", code);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.FORGOETG_PASS_BOX, params, Api.FORGOETG_PASS_BOX_ID, this);
    }


    public void queryCode() {
        String mobile = et_mobile.getText().toString();
        String sign = "mobile=" + mobile + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("mobile", mobile);
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MOBILCODE, params, Api.GET_MOBILCODE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.FORGOETG_PASS_BOX_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        finish();
                        break;
                    case Api.GET_MOBILCODE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        countDown();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }

    /**
     * 倒计时显示
     */
    CountDownTimer timer;
    private void countDown() {
        timer = new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_code.setEnabled(false);
                btn_code.setText("已发送(" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                btn_code.setEnabled(true);
                btn_code.setText("重新获取验证码");

            }
        }.start();
    }
}
