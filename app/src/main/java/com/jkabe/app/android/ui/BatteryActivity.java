package com.jkabe.app.android.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jkabe.box.R;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.base.BaseApplication;
import com.jkabe.app.android.bean.Battery;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.DateUtils;
import com.jkabe.app.android.util.JsonParse;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.ToastUtil;
import com.jkabe.app.android.util.Utility;
import com.jkabe.app.android.weight.ChartView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/10
 * @name:电瓶检测
 */
public class BatteryActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_voltage, text_surface;
    private List<Battery> batteries = new ArrayList<>();
    private LineChart lineChart;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_battery);
        BaseApplication.activityTaskManager.putActivity("BatteryActivity", this);
    }

    @Override
    protected void initView() {
        text_surface = getView(R.id.text_surface);
        text_voltage = getView(R.id.text_voltage);
        lineChart = getView(R.id.spread_line_chart);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("电瓶检测");
        qury();
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

    }


    private void qury() {
        String sign = "imeicode=" + SaveUtils.getCar().getImeicode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVANCE_DEVICE, params, Api.GET_ADVANCE_DEVICE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_ADVANCE_DEVICE_ID:
                        batteries = JsonParse.getBatterieJson(object);
                        if (batteries != null && batteries.size() > 0) {
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


    private float yminValue;
    private float ymaxValue;
    private boolean isGasolineCar = false;

    private void updateView() {
        ArrayList<Entry> values1 = new ArrayList<>();
        String[] xLableList = new String[batteries.size()];

        for (int i = 0; i < batteries.size(); i++) {
            float a = batteries.get(i).getVoltage();
            values1.add(new Entry(i + 1, a));
            xLableList[i] = getChangeTime(batteries.get(i).getGpstime());
        }

        if (batteries.get(0).getVoltage() < 24f) {
            yminValue = 10f;
            ymaxValue = 13.5f;
            isGasolineCar = true;
        } else {
            yminValue = 26.5f;
            ymaxValue = 30f;
            isGasolineCar = false;
        }

        if (isGasolineCar) {
            if (batteries.get(0).getVoltage() < 10.5) {//亏电
                text_voltage.setTextColor(ContextCompat.getColor(this, R.color.battery_color_kuidian));
                text_surface.setTextColor(ContextCompat.getColor(this, R.color.battery_color_kuidian));
                text_voltage.setText("亏电");
                text_surface.setText(batteries.get(0).getVoltage() + "V");
            } else if (batteries.get(0).getVoltage() > 11.6 || batteries.get(0).getVoltage() == 11.6) {
                text_voltage.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                text_surface.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                text_voltage.setText("正常");
                text_surface.setText(batteries.get(0).getVoltage() + "V");
            } else {
                text_voltage.setTextColor(ContextCompat.getColor(this, R.color.battery_color_nozhengchang));
                text_surface.setTextColor(ContextCompat.getColor(this, R.color.battery_color_nozhengchang));
                text_voltage.setText("低压");
                text_surface.setText(batteries.get(0).getVoltage() + "V");
            }
        } else {
            text_voltage.setTextColor(ContextCompat.getColor(this, R.color.battery_color_nozhengchang));
            text_surface.setTextColor(ContextCompat.getColor(this, R.color.battery_color_nozhengchang));
            text_voltage.setText("正常");
            text_surface.setText(batteries.get(0).getVoltage() + "V");
        }


        LineDataSet set1 = new LineDataSet(values1, "");
        set1.setColor(Color.parseColor("#036eb7"));
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);//设置线宽
        set1.setCircleRadius(3f);//设置焦点圆心的大小
        set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
        set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
        set1.setHighlightEnabled(true);//是否禁用点击高亮线
        set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
        set1.setValueTextSize(9f);//设置显示值的文字大小
        set1.setFillColor(Color.BLACK);

        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(true);
        set1.setDrawCircles(false);
        set1.setHighLightColor(Color.parseColor("#036eb7"));

        //保存LineDataSet集合
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the datasets
        //创建LineData对象 属于LineChart折线图的数据集合
        LineData data = new LineData(dataSets);
        // 添加到图表中
        lineChart.setData(data);

        lineChart.setDrawGridBackground(false);
        lineChart.setMaxHighlightDistance(300);
        lineChart.setPinchZoom(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        set1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + "";
            }


        });
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value < xLableList.length) {
                    return xLableList[((int) value % xLableList.length) - 1];
                }
                return xLableList[xLableList.length - 1];

            }
        };

        lineChart.setExtraBottomOffset(2 * 9f);
        xAxis.setTextSize(9);
        xAxis.setLabelCount(xLableList.length - 1);
        lineChart.setXAxisRenderer(new CustomXAxisRenderer(lineChart.getViewPortHandler(), lineChart.getXAxis(), lineChart.getTransformer(YAxis.AxisDependency.LEFT)));

        xAxis.setValueFormatter(valueFormatter);
        //绘制图表

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(12f);

        yAxis.setAxisMinimum(yminValue);
        yAxis.setAxisMaximum(ymaxValue);
        YAxis rightAxis = lineChart.getAxisRight();

        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);

        Legend l = lineChart.getLegend();//图例
        l.setForm(Legend.LegendForm.LINE);//正方形，圆形或线
        l.setFormSize(0f);
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);
        lineChart.invalidate();
    }


    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }

    private String getChangeTime(String time) {
        String str = time.substring(5, 10);
        String str1 = time.substring(10, 15);
        return str + "\n" + str1;
    }


    class CustomXAxisRenderer extends XAxisRenderer {

        public CustomXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
            super(viewPortHandler, xAxis, trans);
        }

        @Override
        protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
            String[] lines = formattedLabel.split("\n");
            for (int i = 0; i < lines.length; i++) {
                float vOffset = i * mAxisLabelPaint.getTextSize();
                Utils.drawXAxisValue(c, lines[i], x, y + vOffset, mAxisLabelPaint, anchor, angleDegrees);
            }

        }
    }
}
