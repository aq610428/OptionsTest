package com.jkabe.app.box.box;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
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
public class ChangeActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private EditText e_password_old, e_password;
    private Button btn_next;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_change);
        BaseApplication.activityTaskManager.putActivity("ChangeActivity", this);
    }

    @Override
    protected void initView() {
        e_password_old = getView(R.id.e_password_old);
        e_password = getView(R.id.e_password);
        btn_next = getView(R.id.btn_next);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("重置交易密码");
        btn_next.setOnClickListener(this);
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

        }
    }


    @Override
    protected void initData() {

    }


    private void checkData() {
        String password_old = e_password_old.getText().toString().trim();
        String password = e_password.getText().toString().trim();
        if (Utility.isEmpty(password_old)) {
            ToastUtil.showToast("旧的交易密码不能为空");
        } else if (Utility.isEmpty(password)) {
            ToastUtil.showToast("新的交易密码不能为空");
        }else {
            query();
        }
    }


    public void query() {
        String password = e_password.getText().toString().trim();
        String password_old = e_password_old.getText().toString().trim();
        String sign ="memberid="+SaveUtils.getSaveInfo().getId()+"&newpassword="+ Md5Util.encode(password)+ "&oldpassword=" + Md5Util.encode(password_old) + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("oldpassword", Md5Util.encode(password_old));
        params.put("partnerid", Constants.PARTNERID);
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("newpassword",  Md5Util.encode(password));
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.RESET_PASS_BOX, params, Api.RESET_PASS_BOX_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.RESET_PASS_BOX_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        finish();
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
