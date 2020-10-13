package com.jkabe.app.box.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.AddressBean;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.lljjcoder.Interface.OnCityItemClickListener;
import com.jkabe.app.box.lljjcoder.bean.CityBean;
import com.jkabe.app.box.lljjcoder.bean.DistrictBean;
import com.jkabe.app.box.lljjcoder.bean.ProvinceBean;
import com.jkabe.app.box.lljjcoder.citywheel.CityConfig;
import com.jkabe.app.box.lljjcoder.style.citypickerview.CityPickerView;
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
 * @date: 2020/9/30
 * @name:AddressActivity
 */
public class AddressActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_address;
    private CityPickerView mCityPickerView = new CityPickerView();
    private TextView text_save;
    private EditText et_name, et_moblie, et_address;
    private ImageView text_cheack;
    private int setdefault = 0;
    private AddressBean addressBean;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_address_add);
        mCityPickerView.init(this);
    }

    @Override
    protected void initView() {
        et_name = getView(R.id.et_name);
        et_moblie = getView(R.id.et_moblie);
        et_address = getView(R.id.et_address);

        text_cheack = getView(R.id.text_cheack);
        text_save = getView(R.id.text_save);
        text_address = getView(R.id.text_address);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_address.setOnClickListener(this);
        text_save.setOnClickListener(this);
        text_cheack.setOnClickListener(this);
        title_text_tv.setText("新建收货人");
    }

    @Override
    protected void initData() {
        addressBean = (AddressBean) getIntent().getSerializableExtra("addressBean");
        if (addressBean != null) {
            et_name.setText(addressBean.getReceivename());
            et_moblie.setText(addressBean.getMobile());
            text_address.setText(addressBean.getProvince() + addressBean.getCity() + addressBean.getArea());
            et_address.setText(addressBean.getAddress());
            if (addressBean.getSetdefault() == 1) {
                text_cheack.setImageResource(R.mipmap.ic_togglebutton_toggle_open);
            } else {
                text_cheack.setImageResource(R.mipmap.ic_togglebutton_toggle_close);
            }
            province=addressBean.getProvince();
            city=addressBean.getCity();
            area=addressBean.getArea();
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_address:
                SystemTools.hideInput(this);
                showAddress();
                break;
            case R.id.text_save:
                saveDialog();
                break;
            case R.id.text_cheack:
                if (setdefault == 0) {
                    text_cheack.setImageResource(R.mipmap.ic_togglebutton_toggle_open);
                    setdefault = 1;
                } else {
                    setdefault = 0;
                    text_cheack.setImageResource(R.mipmap.ic_togglebutton_toggle_close);
                }
                break;
        }
    }


    public void query() {
        String name = et_name.getText().toString();
        String moblie = et_moblie.getText().toString();
        String address = et_address.getText().toString();
        String pcd = text_address.getText().toString();


        if (Utility.isEmpty(name)) {
            ToastUtil.showToast("收货人姓名不能为空");
            return;
        }

        if (Utility.isEmpty(moblie)) {
            ToastUtil.showToast("收货人手机号码不能为空");
            return;
        }

        if (moblie.length() > 11 || moblie.length() < 11) {
            ToastUtil.showToast("手机号码不合法");
            return;
        }


        if (moblie.length() > 11 || moblie.length() < 11) {
            ToastUtil.showToast("手机号码不合法");
            return;
        }

        if (Utility.isEmpty(pcd)) {
            ToastUtil.showToast("省市区不能为空");
            return;
        }

        if (Utility.isEmpty(address)) {
            ToastUtil.showToast("详细地址不能为空");
            return;
        }


        showProgressDialog(this, false);
        String sign = "address=" + address + "&area=" + area + "&city=" + city;
        if (addressBean != null) {
            sign = sign + "&id=" + addressBean.getId();
        }

        sign = sign + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&mobile=" + moblie + "&partnerid=" + Constants.PARTNERID + "&province=" + province
                + "&receivename=" + name + "&setdefault=" + setdefault
                + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();

        params.put("address", address);
        params.put("area", area);
        params.put("city", city);

        if (addressBean != null) {
            params.put("id", addressBean.getId());
        }
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("mobile", moblie);
        params.put("partnerid", Constants.PARTNERID);
        params.put("province", province);
        params.put("receivename", name);
        params.put("setdefault", setdefault + "");

        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MallGood_ADDRESS, params, Api.MallGood_ADDRESS_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.MallGood_ADDRESS_ID:
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

    }

    @Override
    public void onError(Exception e) {

    }


    public void saveDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_bgt, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                query();
            }
        });
        dialog.show();
    }

    String province, city, area;

    private void showAddress() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")
                .visibleItemsCount(5)
                .province("广东省")
                .city("深圳市")
                .district("南山区")
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setShowGAT(true)
                .build();
        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean provinceBean, CityBean cityBean, DistrictBean districtBean) {
                province = provinceBean.getName();
                city = cityBean.getName();
                area = districtBean.getName();
                text_address.setText(province + city + area);
            }

            @Override
            public void onCancel() {
                ToastUtil.showToast("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }

}
