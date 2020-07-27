package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/17
 * @name:车辆详情
 */
public class VehicleActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private CarInfo carInfo;
    private TextView text_card, text_brand, text_mode, text_authentication, text_mileage, text_sim, text_frame;
    private TextView text_num, text_enginee, text_move;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vehicle);
    }

    @Override
    protected void initView() {
        text_card = getView(R.id.text_card);
        text_brand = getView(R.id.text_brand);
        text_mode = getView(R.id.text_mode);
        text_authentication = getView(R.id.text_authentication);
        text_mileage = getView(R.id.text_mileage);
        text_sim = getView(R.id.text_sim);
        text_frame = getView(R.id.text_frame);
        text_num = getView(R.id.text_num);
        text_enginee = getView(R.id.text_enginee);
        text_move = getView(R.id.text_move);

        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("车辆详情");
    }


    private void qury() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_DECICE_VERSION, params, Api.GET_DECICE_VERSION_ID, this);
    }


    @Override
    protected void initData() {
        qury();
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_DECICE_VERSION_ID:
                        carInfo = JsonParse.getCarInfoJson(object);
                        updateView();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void updateView() {
        text_card.setText(carInfo.getCarcard());
        text_brand.setText(carInfo.getYearmodel());
        text_mode.setText(carInfo.getModel());
        if ("0".equals(carInfo.getIsreal())) {
            text_authentication.setText("未认证");
        } else if ("1".equals(carInfo.getIsreal())) {
            text_authentication.setText("已认证");
        } else if ("2".equals(carInfo.getIsreal())) {
            text_authentication.setText("审核中");
        } else {
            text_authentication.setText("审核失败");
        }
        if (!Utility.isEmpty(carInfo.getInitmileage())){
            text_mileage.setText(BigDecimalUtils.div(new BigDecimal(carInfo.getInitmileage()),new BigDecimal(1000),2).toPlainString()+"KM");
        }
        text_sim.setText(carInfo.getSimcode()+"");
        text_frame.setText(carInfo.getVinno()+"");

        text_num.setText(carInfo.getTripcard()+"");
        text_enginee.setText(carInfo.getEngineno()+"");
        text_move.setText(carInfo.getImeicode()+"");
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
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }
}
