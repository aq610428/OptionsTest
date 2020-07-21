package com.jkabe.app.android.banner;


import androidx.viewpager.widget.ViewPager;

import com.jkabe.app.android.banner.transformer.AccordionTransformer;
import com.jkabe.app.android.banner.transformer.BackgroundToForegroundTransformer;
import com.jkabe.app.android.banner.transformer.CubeInTransformer;
import com.jkabe.app.android.banner.transformer.CubeOutTransformer;
import com.jkabe.app.android.banner.transformer.DefaultTransformer;
import com.jkabe.app.android.banner.transformer.DepthPageTransformer;
import com.jkabe.app.android.banner.transformer.FlipHorizontalTransformer;
import com.jkabe.app.android.banner.transformer.FlipVerticalTransformer;
import com.jkabe.app.android.banner.transformer.ForegroundToBackgroundTransformer;
import com.jkabe.app.android.banner.transformer.RotateDownTransformer;
import com.jkabe.app.android.banner.transformer.RotateUpTransformer;
import com.jkabe.app.android.banner.transformer.ScaleInOutTransformer;
import com.jkabe.app.android.banner.transformer.StackTransformer;
import com.jkabe.app.android.banner.transformer.TabletTransformer;
import com.jkabe.app.android.banner.transformer.ZoomInTransformer;
import com.jkabe.app.android.banner.transformer.ZoomOutSlideTransformer;
import com.jkabe.app.android.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends ViewPager.PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
