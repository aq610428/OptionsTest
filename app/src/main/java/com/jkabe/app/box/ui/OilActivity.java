package com.jkabe.app.box.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.Oil;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.DateUtils;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.CustomXAxisRenderer;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/7/14
 * @name:OilActivity
 */
public class OilActivity extends BaseActivity implements NetWorkListener {
    private TextView text_date, text_develop, text_tel, text_cost, text_mean, text_week;
    private TextView title_text_tv, title_left_btn;
    private BarChart mBarChart;
    private String starttime;
    private String endtime;
    private List<String> data = new ArrayList<>();
    private Oil oil;
    private EditText text_price;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_oil);
    }

    @Override
    protected void initView() {
        text_price = getView(R.id.text_price);
        text_week = getView(R.id.text_week);
        mBarChart = getView(R.id.bar_chart);
        text_date = getView(R.id.text_date);
        text_develop = getView(R.id.text_develop);
        text_tel = getView(R.id.text_tel);
        text_cost = getView(R.id.text_cost);
        text_mean = getView(R.id.text_mean);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_week.setOnClickListener(this);
        title_text_tv.setText("油耗分析");
        endtime = DateUtils.DateToStr1(DateUtils.getAddDay(new Date(), -1));
        starttime = DateUtils.DateToStr1(DateUtils.getAddDay(new Date(), -7));
        String oil = PreferenceUtils.getPrefString(this, Constants.OIL, "");
        if (!Utility.isEmpty(oil)) {
            text_price.setText(oil + "元");
        }
        text_date.setText(starttime + "至" + endtime);
        text_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String price = text_price.getText().toString();
                if (!Utility.isEmpty(price)) {
                    price=price.replaceAll("元","");
                    PreferenceUtils.setPrefString(OilActivity.this, Constants.OIL, price);
                    if (oil != null) {
                        updateView();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        qury();
    }


    @Override
    protected void initData() {
        data.clear();
        data.add("最近一天的耗油分析");
        data.add("最近一周的耗油分析");
        initTime();
    }


    private void qury() {
        String sign = "endtime=" + endtime + "&imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&starttime=" + starttime + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("endtime", endtime + "");
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("starttime", starttime + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_OIL_DEVICE, params, Api.GET_OIL_DEVICE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_OIL_DEVICE_ID:
                        oil = JsonParse.getOilJson(object);
                        if (oil != null) {
                            updateView();
                        }
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void updateView() {
        text_develop.setText(oil.getMileage() + "公里");
        text_mean.setText(oil.getAvgoil());
        String price = PreferenceUtils.getPrefString(OilActivity.this, Constants.OIL, "").replaceAll("元","");
        if (!Utility.isEmpty(price)) {
            if ("0".equals(oil.getOils()+"")){
                text_tel.setText("0元");
                text_cost.setText("0元/公里");
            }else{
                String spend = BigDecimalUtils.mul(new BigDecimal(oil.getOils()), new BigDecimal(price.replaceAll("元","'"))).toPlainString();
                text_tel.setText(spend + "元");
                BigDecimal spen = BigDecimalUtils.div(new BigDecimal(oil.getOils()), new BigDecimal(oil.getMileage()), 2);
                text_cost.setText(BigDecimalUtils.mul(spen, new BigDecimal(price)).toPlainString() + "元/公里");
            }
        }

        ArrayList<BarEntry> yValues = new ArrayList<>();
        ArrayList<BarEntry> yValues2 = new ArrayList<>();
        yValues.add(new BarEntry(0, Float.valueOf(oil.getIdling())));
        yValues2.add(new BarEntry(0, (float) oil.getPkidling()));

        yValues.add(new BarEntry(1, Float.valueOf(oil.getAcce())));

        yValues2.add(new BarEntry(1, (float) oil.getPkacce()));

        yValues.add(new BarEntry(2, Float.valueOf(oil.getDece())));

        yValues2.add(new BarEntry(2, (float) oil.getPkdece()));

        yValues.add(new BarEntry(3, Float.valueOf(oil.getSpeedavg())));

        yValues2.add(new BarEntry(3, oil.getSpeedavg()));

        // y 轴数据集
        BarDataSet barDataSet = new BarDataSet(yValues, "我的水平");
        barDataSet.setBarShadowColor(R.color.blue03);
        // y2 轴数据集
        BarDataSet barDataSet2 = new BarDataSet(yValues2, "同城车的水平");
        barDataSet2.setColor(R.color.blue03);

        BarData mBarData = new BarData(barDataSet, barDataSet2);
        //设置动画效果
        mBarChart.animateY(1000, Easing.Linear);
        mBarChart.animateX(1000, Easing.Linear);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(true);
        barDataSet2.setDrawValues(true);

        float groupSpace = 0.3f;
        float barSpace = 0.05f;
        float barWidth = 0.3f;

        // 设置 柱子宽度
        mBarChart.setData(mBarData);
        mBarData.setBarWidth(barWidth);
        mBarChart.groupBars(0f, groupSpace, barSpace);
        Description des = new Description();
        des.setText("");
        mBarChart.setDescription(des);
        mBarChart.setDrawGridBackground(true);


        // 获取 x 轴
        XAxis xAxis = mBarChart.getXAxis();
        // 设置 x 轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 取消 垂直 网格线
        xAxis.setDrawGridLines(true);
        xAxis.setLabelCount(4, false);

        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(4f);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value == 0.0) {
                    return "怠速" + "\n" + "(%)";
                }
                if (value == 1.0) {
                    return "急踩油门次" + "\n" + "(十公里)";
                }
                if (value == 2.0) {
                    return "急踩刹车次" + "\n" + "(十公里)";
                }
                if (value == 3.0) {
                    return "平均车速" + "\n" + "(小时)";
                }
                return super.getFormattedValue(value);
            }
        };
        xAxis.setValueFormatter(valueFormatter);


        mBarChart.setExtraBottomOffset(2 * 9f);
        xAxis.setTextSize(10);

        YAxis rLefteft = mBarChart.getAxisRight();
        rLefteft.setEnabled(false);
        rLefteft.setDrawGridLines(true);

        YAxis lLefteft = mBarChart.getAxisLeft();
        lLefteft.setLabelCount(4, false);
        lLefteft.setAxisMinimum(0f);
        lLefteft.setGranularity(30f);

        mBarChart.setXAxisRenderer(new CustomXAxisRenderer(mBarChart.getViewPortHandler(), mBarChart.getXAxis(),
                mBarChart.getTransformer(YAxis.AxisDependency.LEFT)));

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_week:
                pickerView.show();
                break;
            case R.id.title_left_btn:
                finish();
                break;
        }
    }


    private OptionsPickerView pickerView;

    protected void initTime() {
        pickerView = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String str = data.get(options1);
            if ("最近一天的耗油分析".equals(str)) {
                endtime = DateUtils.DateToStr1(DateUtils.getAddDay(new Date(), -1));
                starttime = DateUtils.DateToStr1(DateUtils.getAddDay(new Date(), -1));
                text_date.setText(starttime);
            } else {
                endtime = DateUtils.DateToStr1(DateUtils.getAddDay(new Date(), -1));
                starttime = DateUtils.DateToStr1(DateUtils.getAddDay(new Date(), -7));
                text_date.setText(starttime + "至" + starttime);
            }
            text_week.setText(str);
            qury();
        }).setCancelText("取消")
                .setSubmitText("确定")
                .setSubmitColor(Color.parseColor("#3F80F4"))
                .setCancelColor(Color.parseColor("#3F80F4"))
                .build();
        pickerView.setPicker(data);
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
