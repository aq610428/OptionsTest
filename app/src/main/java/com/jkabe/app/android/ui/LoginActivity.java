package com.jkabe.app.android.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.base.BaseApplication;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.bean.UserInfo;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.JsonParse;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.SystemTools;
import com.jkabe.app.android.util.ToastUtil;
import com.jkabe.app.android.util.Utility;
import org.json.JSONObject;
import java.util.Map;

/**
 * @author: ong
 * @date: 2020/5/12
 * @name:loginActivity
 */
public class LoginActivity extends BaseActivity implements NetWorkListener {
    private Button btn_next;
    private EditText et_username, et_password;
    private TextView text_register, btn_forget;
    private LinearLayout ll_agreement;
    private View et_line_1,et_line_2;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        BaseApplication.activityTaskManager.putActivity("LoginActivity", this);
    }

    @Override
    protected void initView() {
        et_line_2= getView(R.id.et_line_2);
        et_line_1= getView(R.id.et_line_1);
        ll_agreement= getView(R.id.ll_agreement);
        btn_forget = getView(R.id.btn_forget);
        text_register = getView(R.id.btn_register);
        btn_next = getView(R.id.btn_next);
        et_password = getView(R.id.et_password);
        et_username = getView(R.id.et_username);
        btn_next.setOnClickListener(this);
        text_register.setOnClickListener(this);
        btn_forget.setOnClickListener(this);
        ll_agreement.setOnClickListener(this);
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Utility.isEmpty(et_username.getText().toString())){
                    et_line_1.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }else{
                    et_line_1.setBackgroundColor(Color.parseColor("#3F80F4"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Utility.isEmpty(et_password.getText().toString())){
                    et_line_2.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }else{
                    et_line_2.setBackgroundColor(Color.parseColor("#3F80F4"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_next:
                checkPhone();
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_forget:
                startActivity(new Intent(this, ForgetActivity.class));
                break;
            case R.id.ll_agreement:
                startActivity(new Intent(this, PreviewActivity.class));
                break;
        }
    }


    private void checkPhone() {
        String phone = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (Utility.isEmpty(phone)) {
            ToastUtil.showToast("手机号不能为空");
        } else if (!SystemTools.isMobileNumber(phone)) {
            ToastUtil.showToast("手机号码不合法，请重新输入");
        } else if (Utility.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空");
        } else {
            query();
        }
    }


    public void query() {
        String mobile = et_username.getText().toString();
        String password = et_password.getText().toString();
        String code = et_password.getText().toString();
        if (Utility.isEmpty(mobile)) {
            ToastUtil.showToast("手机号不能为空");
        } else if (!SystemTools.isMobileNumber(mobile)) {
            ToastUtil.showToast("手机号码不合法，请重新输入");
        } else if (Utility.isEmpty(mobile)) {
            ToastUtil.showToast("验证码不能为空");
        } else if (Utility.isEmpty(code)) {
            ToastUtil.showToast("验证码不能为空");
        } else if (Utility.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空");
        } else {
            String sign = "loginname=" + mobile + "&partnerid=" + Constants.PARTNERID + "&password=" + Md5Util.encode(password) + Constants.SECREKEY;
            showProgressDialog(this, false);
            Map<String, String> params = okHttpModel.getParams();
            params.put("loginname", mobile);
            params.put("apptype", Constants.TYPE);
            params.put("partnerid", Constants.PARTNERID);
            params.put("sign", Md5Util.encode(sign));
            params.put("password", Md5Util.encode(password));
            okHttpModel.get(Api.GET_LOGIN, params, Api.GET_LOGIN_ID, this);
        }
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_LOGIN_ID:
                        UserInfo info = JsonParse.getUserInfo(object);
                        if (info != null) {
                            ToastUtil.showToast("登录成功");
                            SaveUtils.saveInfo(info);
                            startActivity(new Intent(this, MainActivity.class));
                            finish();
                        }
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
}
