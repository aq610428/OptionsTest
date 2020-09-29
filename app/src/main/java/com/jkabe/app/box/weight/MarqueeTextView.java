package com.jkabe.app.box.weight;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author: zt
 * @date: 2020/9/29
 * @name:MarqueeTextView
 */
public class MarqueeTextView extends androidx.appcompat.widget.AppCompatTextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused(){
        return true;
    }
}
