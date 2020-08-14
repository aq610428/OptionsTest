package com.jkabe.app.box.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/7/15
 * @name:HistogramView
 */
public abstract  class HistogramView extends View {

    private Context mContext;
    //画笔
    private Paint mPaint;

    //视图的宽
    public int width;
    //视图的高
    public int height;

    //原始起点的X,Y坐标值
    public int originalX = 80;
    public int originalY = 500;


    //X,Y轴等份划分
    public int axisDividedSizeX;
    public int axisDividedSizeY;

    //第一个维度为值,第二个维度为颜色
    public float[] columnInfo;

    //图表标题
    private String mGraphTitle;
    //X轴Name
    private String mXAxisName;
    //Y轴Name
    private String mYAxisName;

    //坐标轴上字体的大小
    private float mAxisTextSize;
    //坐标轴字体的颜色
    public int mAxisTextColor;

    public HistogramView(Context context) {
        this(context, null);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    /**
     * 设置X轴的刻度份数
     *
     * @param axisDividedSizeX
     */
    public void setAxisDividedSizeX(int axisDividedSizeX) {
        this.axisDividedSizeX = axisDividedSizeX;
    }

    /**
     * 设置Y轴的刻度份数
     *
     * @param axisDividedSizeY
     */
    public void setAxisDividedSizeY(int axisDividedSizeY) {
        this.axisDividedSizeY = axisDividedSizeY;
    }

    /**
     * 设置条形图的数值和颜色
     *
     * @param columnInfo
     */
    public void setColumnInfo(float[] columnInfo) {
        this.columnInfo = columnInfo;
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        //获取自定义样式
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChartStyle);

        //取出自定义的设置
        mGraphTitle = typedArray.getString(R.styleable.ChartStyle_graphTitle);
        mXAxisName = typedArray.getString(R.styleable.ChartStyle_xAxisName);
        mYAxisName = typedArray.getString(R.styleable.ChartStyle_yAxisName);

        mAxisTextColor = typedArray.getColor(R.styleable.ChartStyle_axisTextColor, context.getResources().getColor(android.R.color.black));
        mAxisTextSize = typedArray.getDimension(R.styleable.ChartStyle_axisTextSize, 12);

        //若不为null
        if (typedArray != null) {
            //回收
            typedArray.recycle();
        }

        //初始化画笔
        initPaint();

    }

    //初始化画笔
    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            //防抖动
            mPaint.setDither(true);
            //去锯齿
            mPaint.setAntiAlias(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //视图的宽 为 屏幕的宽 - 起始位置
        width = getWidth() - originalX - 80;
        //视图的高度 为 若原始位置超过屏幕高度 则设置 屏幕高度为视图高度  否则 设置原始位置为视图高度
        height = (originalY > getHeight() ? getHeight() : originalY) - 100;


        //画X轴
        drawXAxis(canvas, mPaint);
        //画Y轴
        drawYAxis(canvas, mPaint);
        //画标题
        drawTitle(canvas, mPaint);
        //画X刻度
        drawXAxisScale(canvas, mPaint);
        //画X刻度值
        drawXAxisScaleValue(canvas, mPaint);
        //画Y刻度
        drawYAxisScale(canvas, mPaint);
        //画Y刻度值
        drawYAxisScaleValue(canvas, mPaint);
        //画X箭头
        drawXAxisArrow(canvas, mPaint);
        //画Y箭头
        drawYAxisArrow(canvas, mPaint);
        //画柱形图
        drawColumn(canvas, mPaint);
        //画柱形图上的值
        drawColumnValue(canvas, mPaint);

    }

    /**
     * 画柱形图上的值
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawColumnValue(Canvas canvas, Paint mPaint);

    /**
     * 画柱形条
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawColumn(Canvas canvas, Paint mPaint);

    /**
     * 画Y轴的箭头
     *
     * @param canvas
     * @param mPaint
     */
    private void drawYAxisArrow(Canvas canvas, Paint mPaint) {

        Path mPathY = new Path();
        //画法介绍:画一个三角形,将箭头顶点路径移动到 Y轴顶点-30的位置(), 然后X轴左右各+-10 封闭起来
        mPathY.moveTo(originalX, originalY - height - 30);
        mPathY.lineTo(originalX - 10, originalY - height);
        mPathY.lineTo(originalX + 10, originalY - height);

        mPathY.close();
        mPaint.setColor(mAxisTextColor);
        canvas.drawPath(mPathY, mPaint);
        canvas.drawText(mYAxisName, originalX - 50, originalY - height - 30, mPaint);
    }

    /**
     * 画X轴的箭头
     *
     * @param canvas
     * @param mPaint
     */
    private void drawXAxisArrow(Canvas canvas, Paint mPaint) {
        Path mPathX = new Path();
        //画法介绍:其实就是画一个三角形,将箭头顶点路径移动到 X轴顶点+30的位置, 然后Y轴上下各+-10 封闭起来
        mPathX.moveTo(originalX + width + 30, originalY);
        mPathX.lineTo(originalX + width, originalY + 10);
        mPathX.lineTo(originalX + width, originalY - 10);

        mPathX.close();
        mPaint.setColor(mAxisTextColor);
        canvas.drawPath(mPathX, mPaint);
        canvas.drawText(mXAxisName, originalX + width, originalY + 50, mPaint);
    }

    /**
     * 画Y轴的刻度值
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxisScaleValue(Canvas canvas, Paint mPaint);

    /**
     * 画Y轴的刻度
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxisScale(Canvas canvas, Paint mPaint);

    /**
     * 画X轴的刻度值
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxisScaleValue(Canvas canvas, Paint mPaint);

    /**
     * 画X轴刻度
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxisScale(Canvas canvas, Paint mPaint);

    /**
     * 画图表标题
     *
     * @param canvas
     * @param mPaint
     */
    private void drawTitle(Canvas canvas, Paint mPaint) {

        if (!TextUtils.isEmpty(mGraphTitle)) {
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setColor(mAxisTextColor);
//            mPaint.setFakeBoldText(true);//粗体

            //要求文字宽度的中点 在其屏幕横向的中点
            canvas.drawText(mGraphTitle,
                    (getWidth() / 2) - (mPaint.measureText(mGraphTitle)) / 2,
                    originalY + 70, mPaint);
        }
    }

    /**
     * 画Y轴
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxis(Canvas canvas, Paint mPaint);

    /**
     * 画X轴
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxis(Canvas canvas, Paint mPaint);
}


