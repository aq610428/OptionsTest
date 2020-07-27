package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.box.R;
import com.jkabe.app.box.adapter.TraverAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.DateUtils;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.PreferenceUtils;
import org.joda.time.DateTime;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;

/**
 * @author: zt
 * @date: 2020/7/15
 * @name:行车轨迹
 */
public class TravelActivity extends BaseActivity implements NetWorkListener{
    private TextView title_text_tv, title_left_btn,text_mileage,text_oil,text_houl,txt_rmb;
    private WeekCalendar weekCalendar;
    private String selectTime;
    private List<Travrt> travrts = new ArrayList<>();
    private RecyclerView recyclerView;
    private TraverAdapter traverAdapter;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_travel);
    }

    @Override
    protected void initView() {
        text_mileage= getView(R.id.text_mileage);
        text_oil= getView(R.id.text_oil);
        text_houl= getView(R.id.text_houl);
        txt_rmb= getView(R.id.txt_rmb);
        recyclerView = getView(R.id.recyclerView);
        weekCalendar = getView(R.id.weekCalendar);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("行车轨迹");
        selectTime=DateUtils.getNextTime1();
        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {
                selectTime =DateUtils.getTimes(dateTime.toDate());
                query();
            }

        });
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        query();
    }


    public void query() {
        String sign = "endtime=" + DateUtils.getNextTime1() + "&imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&starttime=" + selectTime + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("endtime", DateUtils.getNextTime1());
        params.put("imeicode", SaveUtils.getCar().getImeicode());
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("starttime", selectTime);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_TRACK_TRIP, params, Api.GET_TRACK_TRIP_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_TRACK_TRIP_ID:
                        travrts = JsonParse.getTraverJson(object);
                        if (travrts != null && travrts.size() > 0) {
                            updateView(travrts);
                        }else{
                            if (traverAdapter!=null){
                                ToastUtil.showToast("暂无行车轨迹");
                                recyclerView.setVisibility(View.GONE);
                                text_mileage.setText("0KM");
                                text_oil.setText("0L");
                                text_houl.setText("0H");
                                txt_rmb.setText("0元");
                            }
                        }
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void updateView(List<Travrt> travrts) {
        float allOil = 0;
        recyclerView.setVisibility(View.VISIBLE);
        traverAdapter = new TraverAdapter(this, travrts);
        recyclerView.setAdapter(traverAdapter);


        Travrt itemVO;
        int mileage = 0;
        Integer oilTrip = 0;
        int allTime = 0;
        for (int i = 0, len = travrts.size(); i < len; i++) {
            itemVO = travrts.get(i);
            mileage = mileage + Integer.valueOf(itemVO.getTripmileage());
            oilTrip = oilTrip + Integer.valueOf(itemVO.getTripoil());
            allTime = allTime + Integer.valueOf(itemVO.getTriptime());
        }
        text_mileage.setText(SystemTools.mathKmOne(mileage)+"KM");
        text_oil.setText(SystemTools.mathKmTwo(oilTrip)+"L");
        text_houl.setText(SystemTools.mathMinute(allTime));
        allOil = Float.valueOf(SystemTools.mathKmTwo(oilTrip));
        txt_rmb.setText(SystemTools.cutOutTwo(remarkHistory() * allOil)+"元");//"¥"+
    }


    private float remarkHistory(){
        return Float.parseFloat(PreferenceUtils.getPrefString(getBaseContext(), Constants.OIL,"0.0"));
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
