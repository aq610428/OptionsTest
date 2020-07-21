package com.jkabe.app.android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.android.adapter.LicenseAdapter;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.bean.CarRulesItemVO;
import com.jkabe.app.android.bean.CarRulesVO;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.DateUtils;
import com.jkabe.app.android.util.JsonParse;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.Utility;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/16
 * @name:违章查询
 */
public class DrivingLicenseActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_sumber;
    private ListView swipe_target;
    private CarRulesVO rulesVO;
    private LicenseAdapter adapter;
    private RelativeLayout searchLicenseView;
    private TextView one_count_id, two_count_id, three_count_id, search_time_message_id;
    private LinearLayout carDetailsLayoutId;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_drivinglicense);
    }

    @Override
    protected void initView() {
        text_sumber = getView(R.id.text_sumber);
        search_time_message_id = getView(R.id.search_time_message_id);
        carDetailsLayoutId = getView(R.id.car_details_layout_id);
        three_count_id = getView(R.id.three_count_id);
        two_count_id = getView(R.id.two_count_id);
        one_count_id = getView(R.id.one_count_id);
        searchLicenseView = getView(R.id.search_license_car_id);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_sumber.setOnClickListener(this);
        title_text_tv.setText("违章查询");
    }

    @Override
    protected void initData() {

    }


    private void query() {
        String sign = "deviceid=" + SaveUtils.getCar().getId() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("deviceid", SaveUtils.getCar().getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_TRACK_TRIPOL, params, Api.GET_TRACK_TRIPOL_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_TRACK_TRIPOL_ID:
                        rulesVO = JsonParse.getCarRulesItemVOJson(object);
                        if (rulesVO != null) {
                            setAdapter();
                        }
                        break;
                }
            } else {
                searchLicenseView.setVisibility(View.GONE);
                carDetailsLayoutId.setVisibility(View.VISIBLE);
                search_time_message_id.setText("查询时间:" + DateUtils.DateToStr(new Date()));
            }
        }
        stopProgressDialog();
    }


    private void setAdapter() {
        if (null != rulesVO) {
            one_count_id.setText(rulesVO.getVio_total());
            two_count_id.setText(rulesVO.getFind_total());
            three_count_id.setText(rulesVO.getScore_total());
            if (null != rulesVO.getLists() && rulesVO.getLists().size() > 0) {
                searchLicenseView.setVisibility(View.GONE);
                carDetailsLayoutId.setVisibility(View.GONE);
                adapter = new LicenseAdapter(this, rulesVO.getLists());
                swipe_target.setAdapter(adapter);
            } else {
                searchLicenseView.setVisibility(View.GONE);
                carDetailsLayoutId.setVisibility(View.VISIBLE);
                search_time_message_id.setText("查询时间:" + DateUtils.DateToStr(new Date()));
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_sumber:
                query();
                break;
        }
    }


}
