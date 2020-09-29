package com.jkabe.app.box.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: zt
 * @date: 2020/9/29
 * @name:ENoticeView
 */
public class ENoticeView  extends View {
    private List<String> data; //显示文字的数据源
    private int mIndex = 0; //当前的数据下标
    private int mDuration = 400; //文字从出现到显示消失的时间
    private int mInterval = 3000; //文字停留在中间的时长切换的间隔
    private boolean isMove = false; //文字是否移动
    private boolean isStart = false; //是否开始
    private int mY = 0; //文字的初始Y坐标
    private int nY = 0; //文字的Y坐标
    private Paint textPaint;
    private final int TEXT_COLOR = 0xff333333;
    private final int TEXT_SIZE = 13;
    private Rect mRect;
    private TimerTask timerTask;

    public ENoticeView(Context context) {
        super(context);
        init();
    }

    public ENoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ENoticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setColor(TEXT_COLOR);
        DisplayMetrics metrics =  new DisplayMetrics();
        metrics.setToDefaults();
        textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,TEXT_SIZE,metrics));
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        if(data != null){
            if(mY == 0&&!isMove){
                mRect = new Rect(20, 20, getMeasuredWidth()-20, getMeasuredHeight()-20);
                Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
                mY = (mRect.bottom + mRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                nY = mY;
            }
            String text =  data.get(mIndex);
            canvas.drawText(text,mRect.left, nY, textPaint);
            if(!isStart){
                isStart = true;
                Timer timer = new Timer();
                countingDown = mInterval+mDuration;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        countingDown -=20;
                        if(countingDown<=0) {
                            countingDown = mInterval + mDuration;
                            isMove = true;
                        }
                        if(countingDown<=mInterval-40&&countingDown>0){
                            isMove = false;
                            drawTextStill();
                        }
                        if(isMove) {
                            drawTextMove();
                        }
                    }
                },mInterval,20);
            }
        }
    }
    private long countingDown = 0;

    private void drawTextStill(){
        nY = mY;
        postInvalidate();
    }
    private void drawTextMove(){
        nY -=  getMeasuredHeight()/(mDuration/20);
        if(nY<0){
            mIndex++;
            if(mIndex == data.size())
                mIndex = 0;
            nY = getMeasuredHeight();
        }
        postInvalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (onClickListener != null) {
                    onClickListener.onClick(data.get(mIndex));
                }
                break;
        }
        return true;
    }
    public interface OnNoticeClickListener {
        public void onClick(String item);
    }

    private OnNoticeClickListener onClickListener;

    public void setOnNoticeClickListener(OnNoticeClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    /**
     * 设置数据源
     * */
    public void setData(List<String> data) {
        this.data = data;
        invalidate();
    }
    /**
     * 设置数据源,并且重置
     * */
    public void setData(List<String> data,boolean isReStart) {
        this.data = data;
        if (isReStart)
            mIndex = 0;
        invalidate();
    }

    /**
     * 设置广告文字的停顿时间
     * */
    public void setIntervalTime(int mInterval) {
        this.mInterval = mInterval;
    }

    /**
     * 设置文字从出现到消失的时长
     * */
    public void setDurationTime(int mDuration) {
        this.mDuration = mDuration;
    }

    /**
     * 设置文字颜色
     * */
    public void setNoticeColor(int mFrontColor) {
        textPaint.setColor(mFrontColor);
    }
}
