package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.lljjcoder.Interface.OnCityItemClickListener;
import com.jkabe.app.box.lljjcoder.bean.CityBean;
import com.jkabe.app.box.lljjcoder.bean.DistrictBean;
import com.jkabe.app.box.lljjcoder.bean.ProvinceBean;
import com.jkabe.app.box.lljjcoder.citywheel.CityConfig;
import com.jkabe.app.box.lljjcoder.style.citypickerview.CityPickerView;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/9/30
 * @name:AddressActivity
 */
public class AddressActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_address;
    private CityPickerView mCityPickerView = new CityPickerView();

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_address_add);
        mCityPickerView.init(this);
    }

    @Override
    protected void initView() {
        text_address = getView(R.id.text_address);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_address.setOnClickListener(this);
        title_text_tv.setText("新建收货人");
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_address:
                showAddress();
                break;
        }
    }


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
                String province = provinceBean.getName();
                String city = cityBean.getName();
                String area = districtBean.getName();
                text_address.setText(province+city+area);
            }

            @Override
            public void onCancel() {
                ToastUtil.showToast("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }
}
