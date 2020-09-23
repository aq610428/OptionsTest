package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.os.Handler;
import com.jkabe.app.box.banner.Banner;
import com.jkabe.app.box.banner.BannerConfig;
import com.jkabe.app.box.banner.Transformer;
import com.jkabe.app.box.banner.listener.OnBannerListener;
import com.jkabe.app.box.base.BaseActivity1;
import com.jkabe.app.box.bean.BannerVo;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.weight.MyLoader;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;
import cc.ibooker.zcountdownviewlib.CountDownView;

/**
 * @author: zt
 * @date: 2020/9/23
 * @name:商品详情
 */
public class WareDeilActivity extends BaseActivity1 implements OnBannerListener {
    private Banner banner;
    private List<BannerVo> banners = new ArrayList<>();
    private CountDownView countDownView;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_waredeil);
    }

    @Override
    protected void initView() {
        banner = getView(R.id.banner);
        countDownView = getView(R.id.countdownView);
        updateView();
    }

    @Override
    protected void initData() {
        countDownView.setCountTime(111111) // 设置倒计时时间戳
                .setHourTvBackgroundRes(R.drawable.bg_shape_read)
                .setHourTvTextColorHex("#FFFFFF")
                .setHourTvGravity(CountDownView.CountDownViewGravity.GRAVITY_CENTER)
                .setHourTvTextSize(21)

                .setHourColonTvBackgroundColorHex("#00FFFFFF")
                .setHourColonTvSize(18, 0)
                .setHourColonTvTextColorHex("#FF7198")
                .setHourColonTvGravity(CountDownView.CountDownViewGravity.GRAVITY_CENTER)
                .setHourColonTvTextSize(21)

                .setMinuteTvBackgroundRes(R.drawable.bg_shape_read)
                .setMinuteTvTextColorHex("#FFFFFF")
                .setMinuteTvTextSize(21)

                .setMinuteColonTvSize(18, 0)
                .setMinuteColonTvTextColorHex("#FF7198")
                .setMinuteColonTvTextSize(21)

                .setSecondTvBackgroundRes(R.drawable.bg_shape_read)
                .setSecondTvTextColorHex("#FFFFFF")
                .setSecondTvTextSize(21)
                .startCountDown();
        countDownView.setCountDownEndListener(new CountDownView.CountDownEndListener() {
            @Override
            public void onCountDownEnd() {
                LogUtils.e("倒计时结束");
            }
        });


    }


    @Override
    protected void onPause() {
        super.onPause();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownView.stopCountDown();
            }
        }, 2000);
    }

    public void updateView() {
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i1/2423612906/O1CN01ZdaDVp1XKzaVMxYOA_!!2423612906.jpg"));
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01Z36kSU1XKzaYaBtHb_!!2423612906.jpg"));
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01z935sk1XKzaYvBd0d_!!2423612906.jpg"));
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01d8H5WT1XKzaYvAtHU_!!2423612906.jpg"));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        banner.setImages(banners);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Accordion);
        banner.setTitleView(true);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.RIGHT)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
