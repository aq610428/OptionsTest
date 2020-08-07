package com.jkabe.app.box.box;

import android.os.Bundle;
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
public class TransactionActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private EditText et_mobile, e_password;
    private Button btn_next;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_transaction);
    }

    @Override
    protected void initView() {
        e_password = getView(R.id.e_password);
        et_mobile = getView(R.id.et_mobile);
        btn_next = getView(R.id.btn_next);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("交易密码");
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
        String phone = et_mobile.getText().toString().trim();
        String password = e_password.getText().toString().trim();
        if (Utility.isEmpty(phone)||Utility.isEmpty(password)) {
            ToastUtil.showToast("交易密码不能为空");
        } else if (!phone.equals(password)) {
            ToastUtil.showToast("交易密码输入不一致");
        } else {
            query();
        }
    }



    public void query() {
        String paypassword = et_mobile.getText().toString().trim();
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID+"&paypassword="+Md5Util.encode(paypassword) + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("paypassword", Md5Util.encode(paypassword));
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MINING_PASS_BOX, params, Api.MINING_PASS_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.MINING_PASS_BOX_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        UserInfo info=SaveUtils.getSaveInfo();
                        info.setIsPayPassword("1");
                        SaveUtils.saveInfo(info);
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
