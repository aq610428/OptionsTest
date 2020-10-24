package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.adapter.ActiiveAdapter;
import com.jkabe.app.box.adapter.ActiiveAdapter1;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.ActiveBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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
        BaseApplication.activityTaskManager.putActivity("AboutActivity", this);
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
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_exchange:

                break;

        }
    }


    @Override
    protected void initData() {
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
        okHttpModel.get(Api.PAY_REMOVE_MALL, params, Api.PAY_REMOVE_MALL_ID, this);
    }

    /*****去兑换*****/
    public void query1() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_REMOVE_MALL, params, Api.PAY_REMOVE_MALL_ID, this);
    }



    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.PAY_REMOVE_MALL_ID:
                        updateView(object);
                        break;
                }
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
        text_userableNum.setText("可用消费额度：" + consumeAmount + "元");
        text_usedNum.setText("1. 消费额度满"+oneParam+"元即可兑换一次150U挖矿机会 \n"+"2. 消费额度满"+twoParam+"元即可兑换一次750U挖矿机会"+"\n"+"3. 挖矿机会暂时不支持转让");
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
