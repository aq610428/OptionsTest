package com.jkabe.app.box.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import androidx.annotation.Nullable;
import com.jkabe.box.R;
import com.jkabe.app.box.bean.Battery;

import java.util.List;


/**
 * @author: zt
 * @date: 2020/7/15
 * @name:ChartView
 */
public class ChartView extends View implements View.OnClickListener {


    int width, height;
    Paint paintBar, paintText;
    float[] values = new float[15];
    float[] valuesTemp = new float[15];
    int colorBackground = R.color.white;//柱状背景
    int[] colorBar = new int[]{R.color.blue03, R.color.merchant_text_color, R.color.chant_text_color};//柱状颜色
    float maxY;
    int barMarginLeft = 7;
    int tagHeight = 45;//x和Y轴的数字
    boolean anim;
    boolean showLineXNums = true, showLineYNums = true;//展示X、Y轴的数字
    int lineYNums = 5;//Y轴展示的格数
    ValueAnimator valueAnimator;

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paintText = paintBar = new Paint();
        paintBar.setAntiAlias(true);
        paintText.setAntiAlias(true);
        paintText.setTextSize(25);
        paintText.setColor(getResources().getColor(colorBar[0]));

    }

    public void initValuesAndMaxY(List<Battery> batteries) {
        for (int i = 0; i < batteries.size(); i++) {
            values[i] = batteries.get(i).getVoltage();
        }
        for (float i : values) {
            maxY = maxY < i ? i : maxY;
        }
        setOnClickListener(this);
        paintText = paintBar = new Paint();
        paintBar.setAntiAlias(true);
        paintText.setAntiAlias(true);
        paintText.setTextSize(25);
        paintText.setColor(getResources().getColor(colorBar[0]));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        int barWidth = (width - tagHeight) / values.length;
        int j = 0;
        if (!anim)
            valuesTemp = values.clone();
        for (int i = 0; i < valuesTemp.length; i++) {
            RectF rect = new RectF();
            rect.left = tagHeight + i * barWidth + barMarginLeft;
            rect.top = (height - tagHeight) * (1 - 1.0f * valuesTemp[i] / maxY);
            rect.right = rect.left + barWidth - barMarginLeft;
            rect.bottom = height - tagHeight;
            //draw the barBackground
            paintBar.setColor(getResources().getColor(colorBackground));
            canvas.drawRect(rect.left, 0, rect.right, rect.bottom, paintBar);
            //paint the bar
            j = j > colorBar.length - 1 ? 0 : j;
            paintBar.setColor(getResources().getColor(colorBar[j++]));
            canvas.drawRect(rect, paintBar);
            if (showLineXNums) {
                //draw x-coordinate num
                float textWidth = paintText.measureText(String.valueOf(i));
                float textLeft = rect.left + rect.width() / 2 - textWidth / 2;
                canvas.drawText(String.valueOf(i), textLeft, height, paintText);
            }
        }
        //draw y-coordinate num
        if (showLineYNums) {
            int avgHeight = (height - tagHeight) / lineYNums;
            for (int i = 0; i < lineYNums; i++) {
                float x = 0;
                float y = (height - tagHeight) - avgHeight * (i + 1);
                int valueY = (int) (maxY * (i + 1) / lineYNums);
                // canvas写字是从x、y轴往右上写的
                canvas.drawText(String.valueOf(valueY), x, y + 30, paintText);
            }
        }
    }

    @Override
    public void onClick(View v) {
        anim = true;
        valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valuesTemp = values.clone();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                for (int i = 0; i < valuesTemp.length; i++) {
                    //update valuesTemps
                    float animatedValue = (float) animation.getAnimatedValue();
                    valuesTemp[i] = maxY * animatedValue < values[i] ? maxY * animatedValue : values[i];
                }
                invalidate();
            }
        });
        //落地回调效果
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(2000l);
        valueAnimator.start();
    }

}
