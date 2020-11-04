package com.jkabe.app.box.box;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.TabBean;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.ClearEditText;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/10/22
 * @name:DefiActivity
 */
public class DefiActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_ensure, title_right_btn;
    private ClearEditText et_num;
    private TabBean tabBean;
    private TextView text_user, text_deif, text_pay;
    private String type;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_defi);
    }

    @Override
    protected void initView() {
        text_pay = getView(R.id.text_pay);
        text_deif = getView(R.id.text_deif);
        text_user = getView(R.id.text_user);
        title_right_btn = getView(R.id.title_right_btn);
        et_num = getView(R.id.et_num);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        text_ensure = getView(R.id.text_ensure);
        title_left_btn.setOnClickListener(this);
        text_ensure.setOnClickListener(this);
        title_text_tv.setText("投保");
        title_right_btn.setText("收益记录");
        title_right_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.title_right_btn:
                startActivity(new Intent(this, TabDefiActivity.class));
                break;
            case R.id.text_ensure:
                query();
                break;
        }
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        tabBean = (TabBean) getIntent().getSerializableExtra("tabBean");
        if (tabBean != null) {
            text_user.setText(tabBean.getManage_amount() + "");
            text_deif.setText(tabBean.getProfit_amount() + "");
            text_pay.setText(tabBean.getManage_num() + "");
        }
    }


    public void query() {
        String amount = et_num.getText().toString();
        if (Utility.isEmpty(amount)) {
            ToastUtil.showToast("理财金额不能为空");
            return;
        }
        String sign = "amount=" + amount + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&type=" + type + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("amount", amount + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("type", type + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.post(Api.LIST_MEMBER_BOX, params, Api.LIST_MEMBER_BOX_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.LIST_MEMBER_BOX_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                }
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
