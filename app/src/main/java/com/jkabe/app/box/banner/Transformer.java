package com.jkabe.app.box.banner;


import androidx.viewpager.widget.ViewPager;

import com.jkabe.app.box.banner.transformer.AccordionTransformer;
import com.jkabe.app.box.banner.transformer.BackgroundToForegroundTransformer;
import com.jkabe.app.box.banner.transformer.CubeInTransformer;
import com.jkabe.app.box.banner.transformer.CubeOutTransformer;
import com.jkabe.app.box.banner.transformer.DefaultTransformer;
import com.jkabe.app.box.banner.transformer.DepthPageTransformer;
import com.jkabe.app.box.banner.transformer.FlipHorizontalTransformer;
import com.jkabe.app.box.banner.transformer.FlipVerticalTransformer;
import com.jkabe.app.box.banner.transformer.ForegroundToBackgroundTransformer;
import com.jkabe.app.box.banner.transformer.RotateDownTransformer;
import com.jkabe.app.box.banner.transformer.RotateUpTransformer;
import com.jkabe.app.box.banner.transformer.ScaleInOutTransformer;
import com.jkabe.app.box.banner.transformer.StackTransformer;
import com.jkabe.app.box.banner.transformer.TabletTransformer;
import com.jkabe.app.box.banner.transformer.ZoomInTransformer;
import com.jkabe.app.box.banner.transformer.ZoomOutSlideTransformer;
import com.jkabe.app.box.banner.transformer.ZoomOutTranformer;


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
