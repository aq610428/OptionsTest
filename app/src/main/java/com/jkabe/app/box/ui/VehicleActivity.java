package com.jkabe.app.box.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/17
 * @name:车辆详情
 */
public class VehicleActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, title_right_btn, text_authentication, text_move, text_sim, text_num, text_brand;
    private CarInfo carInfo;
    private EditText text_card, text_mode, text_mileage, text_frame;
    private TextView text_enginee;
    private Button btn_next;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vehicle);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        title_right_btn = getView(R.id.title_right_btn);
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
        text_brand.setOnClickListener(this);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_authentication.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        title_text_tv.setText("车辆详情");
    }

    @Override
    protected void initData() {
        qury();
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


    private void query() {
        String imeicode = text_move.getText().toString().trim();//设备号
        String carcard = text_card.getText().toString().trim();//车牌
        String initmileage = text_mileage.getText().toString().trim().replaceAll("KM", "");
        String vinno = text_frame.getText().toString().trim();
        String engineno = text_enginee.getText().toString().trim();


        String sign = "carcard=" + carcard + "&engineno=" + engineno + "&id=" + carInfo.getId() + "&imeicode=" + imeicode
                + "&initmileage=" + initmileage + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID
                + "&vinno=" + vinno;
        if (!Utility.isEmpty(yearmodelid)) {
            sign = sign + "&yearmodelid=" + carInfo.getYearmodel() + Constants.SECREKEY;
        } else {
            sign = sign + Constants.SECREKEY;
        }
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("carcard", carcard + "");
        params.put("engineno", engineno + "");
        params.put("imeicode", imeicode + "");
        params.put("id", carInfo.getId() + "");
        params.put("initmileage", initmileage + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("vinno", vinno + "");
        if (!Utility.isEmpty(yearmodelid)) {
            params.put("yearmodelid", yearmodelid + "");
        }

        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MODEL_BIND, params, Api.GET_MODEL_BIND_ID, this);
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
                    case Api.GET_MODEL_BIND_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
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
        if (carInfo.getIsreal() == 0) {
            text_authentication.setText("请上传驾驶证");
            text_authentication.setEnabled(true);
            enableView();
        } else if (carInfo.getIsreal() == 1) {
            text_authentication.setText("已认证");
            text_authentication.setEnabled(false);
            goView();
        } else if (carInfo.getIsreal() == 2) {
            text_authentication.setText("审核中");
            text_authentication.setEnabled(false);
            goView();
        } else {
            text_authentication.setText("请上传驾驶证");
            text_authentication.setEnabled(true);
            enableView();
        }
        text_mileage.setText(carInfo.getInitmileage() + "KM");
        text_sim.setText(carInfo.getSimcode() + "");
        text_frame.setText(carInfo.getVinno() + "");

        text_num.setText(carInfo.getTripcard() + "");
        text_enginee.setText(carInfo.getEngineno() + "");
        text_move.setText(carInfo.getImeicode() + "");
    }

    private String yearmodelid, factory, model, yearmodel;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            yearmodelid = data.getStringExtra("business");
            factory = data.getStringExtra("factory");
            model = data.getStringExtra("model");
            yearmodel = data.getStringExtra("yearmodel");
            text_brand.setText(factory + model + yearmodel);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        int isreal = SaveUtils.getCar().getIsreal();
        if (isreal == 2) {
            text_authentication.setText("审核中");
            text_authentication.setEnabled(true);
        }

    }

    public void goView() {
        btn_next.setVisibility(View.GONE);
        text_brand.setEnabled(false);
        text_mode.setEnabled(false);
        text_mileage.setEnabled(false);
        text_sim.setEnabled(false);
        text_frame.setEnabled(false);
        text_card.setEnabled(false);
        text_num.setEnabled(false);
        text_enginee.setEnabled(false);
        text_move.setEnabled(false);
    }

    public void enableView() {
        text_brand.setEnabled(true);
        text_mode.setEnabled(true);
        text_mileage.setEnabled(true);
        text_sim.setEnabled(true);
        text_frame.setEnabled(true);
        text_card.setEnabled(true);
        text_num.setEnabled(true);
        text_enginee.setEnabled(true);
        text_move.setEnabled(true);
        btn_next.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.btn_next:
                query();
                break;
            case R.id.text_authentication:
                startActivity(new Intent(this, LicenseActivity.class));
                break;
            case R.id.text_brand:
                Intent intent = new Intent(this, BrandCarActivity.class);
                startActivityForResult(intent, 100);
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
