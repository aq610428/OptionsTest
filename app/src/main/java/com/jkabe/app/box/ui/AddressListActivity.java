package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.AddressAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.lljjcoder.Interface.OnCityItemClickListener;
import com.jkabe.app.box.lljjcoder.bean.CityBean;
import com.jkabe.app.box.lljjcoder.bean.DistrictBean;
import com.jkabe.app.box.lljjcoder.bean.ProvinceBean;
import com.jkabe.app.box.lljjcoder.citywheel.CityConfig;
import com.jkabe.app.box.lljjcoder.style.citypickerview.CityPickerView;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressListActivity
 */
public class AddressListActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    private TextView title_text_tv, title_left_btn;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<String> array = new ArrayList<>();
    private AddressAdapter adapter;
    private TextView text_add;
    private CityPickerView mCityPickerView = new CityPickerView();


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_address);
        mCityPickerView.init(this);
    }

    @Override
    protected void initView() {
        text_add = getView(R.id.text_add);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        text_add.setOnClickListener(this);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("收货地址");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_add:

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

            }

            @Override
            public void onCancel() {
                ToastUtil.showToast("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }


    @Override
    protected void initData() {
        array.add("1");
        array.add("1");
        array.addAll(array);
        adapter = new AddressAdapter(this, array);
        swipe_target.setAdapter(adapter);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
