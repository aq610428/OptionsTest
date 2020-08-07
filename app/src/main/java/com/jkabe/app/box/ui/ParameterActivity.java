package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.LocationVO;
import com.jkabe.app.box.bean.OdbAndLocationVO;
import com.jkabe.app.box.bean.OdbVO;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import org.json.JSONObject;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/17
 * @name:ParameterActivity
 */
public class ParameterActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    TextView parameter1Id,parameter2Id,parameter3Id;
    TextView parameter4Id;
    TextView parameter5Id;
    TextView parameter6Id;
    TextView parameter7Id;
    TextView parameter8Id;
    TextView parameter9Id;
    TextView parameter10Id;
    TextView parameter11Id;
    TextView parameter12Id;
    TextView parameter13Id;
    TextView parameter14Id;
    TextView parameter16Id;
    TextView parameter17Id;
    TextView parameter18Id;
    TextView parameter19Id;
    TextView parameter20Id;
    TextView parameter21Id;
    TextView parameter22Id;
    TextView parameter23Id;
    TextView parameter24Id;
    TextView parameter25Id;
    TextView parameter26Id;
    TextView parameter27Id;
    TextView parameter28Id;
    TextView parameter29Id;
    TextView parameter30Id;
    TextView parameter31Id;
    TextView parameter32Id;
    TextView parameter33Id;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_parameter);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("行车参数");
        parameter1Id = getView(R.id.parameter_1_id);
        parameter2Id = getView((R.id.parameter_2_id));
        parameter3Id = getView((R.id.parameter_3_id));
        parameter4Id = getView(R.id.parameter_4_id);
        parameter5Id = getView(R.id.parameter_5_id);
        parameter6Id = getView(R.id.parameter_6_id);
        parameter7Id = getView(R.id.parameter_7_id);
        parameter8Id = getView(R.id.parameter_8_id);
        parameter9Id = getView(R.id.parameter_9_id);
        parameter10Id = getView(R.id.parameter_10_id);
        parameter11Id = getView(R.id.parameter_11_id);
        parameter12Id = getView(R.id.parameter_12_id);
        parameter13Id = getView(R.id.parameter_13_id);
        parameter14Id = getView(R.id.parameter_14_id);
        parameter16Id = getView(R.id.parameter_16_id);
        parameter17Id = getView(R.id.parameter_17_id);
        parameter18Id = getView(R.id.parameter_18_id);
        parameter19Id = getView(R.id.parameter_19_id);
        parameter20Id = getView(R.id.parameter_20_id);
        parameter21Id = getView(R.id.parameter_21_id);
        parameter22Id = getView(R.id.parameter_22_id);
        parameter23Id = getView(R.id.parameter_23_id);
        parameter24Id = getView(R.id.parameter_24_id);
        parameter25Id = getView(R.id.parameter_25_id);
        parameter26Id = getView(R.id.parameter_26_id);
        parameter27Id = getView(R.id.parameter_27_id);
        parameter28Id = getView(R.id.parameter_28_id);
        parameter29Id = getView(R.id.parameter_29_id);
        parameter30Id = getView(R.id.parameter_30_id);
        parameter31Id = getView(R.id.parameter_31_id);
        parameter32Id = getView(R.id.parameter_32_id);
        parameter33Id = getView(R.id.parameter_33_id);
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
        query();
    }

    private void query() {
        String sign = "imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_AGE_DEVICE1, params, Api.GET_AGE_DEVICE1_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_AGE_DEVICE1_ID:
                        OdbAndLocationVO vo = JsonParse.buildNonDefaultMapper(object);
                        if (vo != null) {
                            updateView(vo);
                        }
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void updateView(OdbAndLocationVO vo) {
        parameter1Id.setText( vo.getCarcard());
        if(null != vo.getObddata()) {
            OdbVO odbVO = vo.getObddata();
            if(!Utility.isEmpty(odbVO.getEngine_speed())){
                parameter4Id.setText(odbVO.getEngine_speed() + "rmp");
            }
            if(!Utility.isEmpty(odbVO.getCoolant_temperature())){
                parameter5Id.setText(odbVO.getCoolant_temperature() + "°c");
            }
            if(!Utility.isEmpty(odbVO.getAir_flow_rate())){
                parameter6Id.setText(odbVO.getAir_flow_rate() + "  g/s");//air_flow_rate
            }
            if(!Utility.isEmpty(odbVO.getIntake_temperature())){
                parameter7Id.setText(odbVO.getIntake_temperature() + "°c");
            }
            if(!Utility.isEmpty(odbVO.getAbsolute_pressure_intake_manifold())){
                parameter8Id.setText(odbVO.getAbsolute_pressure_intake_manifold() + "pka");//absolute_pressure_intake_manifold
            }

            if(!Utility.isEmpty(odbVO.getLoad_calculation_value())){
                parameter10Id.setText(odbVO.getLoad_calculation_value() + "%");//absolute_pressure_intake_manifold
            }

            if(!Utility.isEmpty(odbVO.getAbsolute_throttle_position())){
                parameter11Id.setText(odbVO.getAbsolute_throttle_position() + "%");//absolute_pressure_intake_manifold
            }
            if(!Utility.isEmpty(odbVO.getIgnition_dvance_angle())){
                parameter12Id.setText(odbVO.getIgnition_dvance_angle());//absolute_pressure_intake_manifold
            }

            if(!Utility.isEmpty(odbVO.getInstantaneous_fuel_consumption())){
                parameter13Id.setText(odbVO.getInstantaneous_fuel_consumption());//absolute_pressure_intake_manifold
            }

            if(!Utility.isEmpty(odbVO.getControl_module_voltage())){
                parameter14Id.setText(odbVO.getControl_module_voltage()+ "V");//absolute_pressure_intake_manifold
            }

            if(!Utility.isEmpty(odbVO.getFuel_pressure())){
                parameter16Id.setText(odbVO.getFuel_pressure() + "L");//absolute_pressure_intake_manifold
            }

            if(!Utility.isEmpty(odbVO.getOutput_torque())){
                parameter17Id.setText(odbVO.getOutput_torque() + "%");//
            }
            if(!Utility.isEmpty(odbVO.getFriction_torque())){
                parameter18Id.setText(odbVO.getFriction_torque() + "%");//
            }
            if(!Utility.isEmpty(odbVO.getScr_up_nox_value())){
                parameter20Id.setText(odbVO.getScr_up_nox_value() + "ppm");//
            }

            if(!Utility.isEmpty(odbVO.getScr_down_nox_value())){
                parameter21Id.setText(odbVO.getScr_down_nox_value() + "ppm");//
            }

            if(!Utility.isEmpty(odbVO.getReactant_margin())){
                parameter22Id.setText(odbVO.getReactant_margin() + "%");//
            }

            if(!Utility.isEmpty(odbVO.getScr_inlet_temperature())){
                parameter24Id.setText(odbVO.getScr_inlet_temperature() + "%");//
            }
            if(!Utility.isEmpty(odbVO.getScr_outlet_temperature())){
                parameter25Id.setText(odbVO.getScr_outlet_temperature() + "deg");//
            }
            if(!Utility.isEmpty(odbVO.getDpf_differential_pressure())){
                parameter26Id.setText(odbVO.getDpf_differential_pressure() + "kPa");//
            }

        }


        if(null != vo.getLocationdata()) {
            LocationVO v = vo.getLocationdata();
            parameter9Id.setText(Utility.judgeStrState(SystemTools.mathKmOne(v.getMileage()) + "KM", "--"));
            parameter2Id.setText(Utility.judgeStrState(v.getGpstime(), "--"));
            parameter3Id.setText(Utility.judgeStrState(v.getSpeed() + "km/h", "--"));
            parameter28Id.setText("1");
            if(null != v.getLng()) {
                String j = SystemTools.fomatData(Float.valueOf(v.getLng()),"0.00000");
                parameter29Id.setText(Utility.judgeStrState(j , "--"));
            }
            if(null != v.getLat()) {
                String w = SystemTools.fomatData(Float.valueOf(v.getLat()),"0.00000");
                parameter30Id.setText(Utility.judgeStrState(w , "--"));
            }
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
