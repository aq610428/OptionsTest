package com.jkabe.app.box.box;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.Md5Util;
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
    private TextView title_text_tv, title_left_btn,text_ensure;
    private ClearEditText et_num;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_defi);
    }

    @Override
    protected void initView() {
        et_num= getView(R.id.et_num);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        text_ensure = getView(R.id.text_ensure);
        title_left_btn.setOnClickListener(this);
        text_ensure.setOnClickListener(this);
        title_text_tv.setText("投保");
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_ensure:

                break;
        }
    }

    @Override
    protected void initData() {

    }



    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_LIST, params, Api.GET_COINS_LIST_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_COINS_LIST_ID:

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
