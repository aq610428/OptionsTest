package com.jkabe.app.android.weight;

import android.graphics.Canvas;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * @author: zt
 * @date: 2020/7/15
 * @name:CustomXAxisRenderer
 */
public class CustomXAxisRenderer extends XAxisRenderer {
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
