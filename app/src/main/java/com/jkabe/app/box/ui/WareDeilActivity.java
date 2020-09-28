package com.jkabe.app.box.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.jkabe.app.box.banner.Banner;
import com.jkabe.app.box.banner.BannerConfig;
import com.jkabe.app.box.banner.Transformer;
import com.jkabe.app.box.banner.listener.OnBannerListener;
import com.jkabe.app.box.base.BaseActivity1;
import com.jkabe.app.box.bean.BannerVo;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.weight.MyLoader1;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: zt
 * @date: 2020/9/23
 * @name:商品详情
 */
public class WareDeilActivity extends BaseActivity1 implements OnBannerListener {
    private Banner banner;
    private List<BannerVo> banners = new ArrayList<>();
    private long mDay = 1;// 天
    private long mHour = 1;//小时,
    private long mMin = 56;//分钟,
    private long mSecond = 32;//秒
    private Timer mTimer = new Timer();
    private String str;
    private TextView text_date,text_num;
    private ImageView iv_left;
    private TextView text_pay;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                str = mDay + "天" + SystemTools.getTv(mHour) + "时" + SystemTools.getTv(mMin) + "分" + SystemTools.getTv(mSecond) + "秒";
                text_date.setText(str);
                if (mSecond == 0 && mDay == 0 && mHour == 0 && mMin == 0) {
                    mTimer.cancel();
                }
            }
        }
    };


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_waredeil);
    }

    @Override
    protected void initView() {
        text_pay = getView(R.id.text_pay);
        text_num = getView(R.id.text_num);
        iv_left = getView(R.id.iv_left);
        banner = getView(R.id.banner);
        text_date = getView(R.id.text_date);
        iv_left.setOnClickListener(this);
        text_pay.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        startRun();
        updateView();
    }


    /******开始倒计时******/
    private void startRun() {
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }


    public void updateView() {
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i1/2423612906/O1CN01ZdaDVp1XKzaVMxYOA_!!2423612906.jpg"));
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01Z36kSU1XKzaYaBtHb_!!2423612906.jpg"));
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01z935sk1XKzaYvBd0d_!!2423612906.jpg"));
        banners.add(new BannerVo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01d8H5WT1XKzaYvAtHU_!!2423612906.jpg"));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader1());
        banner.setImages(banners);
        text_num.setText("1/"+banners.size());
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
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                text_num.setText((position+1)+"/"+banners.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void OnBannerClick(int position) {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.text_pay:
                startActivity(new Intent(this,ConfirmActivity.class));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(0);
        mTimer.cancel();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                    if (mDay < 0) {
                        // 倒计时结束
                        mDay = 0;
                        mHour = 0;
                        mMin = 0;
                        mSecond = 0;
                    }
                }
            }
        }
    }
}
