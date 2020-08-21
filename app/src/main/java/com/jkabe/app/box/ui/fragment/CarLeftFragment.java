package com.jkabe.app.box.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.box.AdvertActivity;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.box.R;
import com.jkabe.app.box.adapter.LeftAdapter;
import com.jkabe.app.box.banner.Banner;
import com.jkabe.app.box.banner.BannerConfig;
import com.jkabe.app.box.banner.Transformer;
import com.jkabe.app.box.banner.listener.OnBannerListener;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.BannerVo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.LeftVo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.ui.PreviewActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.MyLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crossoverone.statuslib.StatusUtil;


/**
 * @author: zt
 * @date: 2020/7/2
 * @name:车生活
 */
public class CarLeftFragment extends BaseFragment implements View.OnClickListener, OnBannerListener, NetWorkListener, OnRefreshListener {
    private View rootView;
    private Banner banner;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private List<BannerVo> banners = new ArrayList<>();
    private List<LeftVo> voList = new ArrayList<>();
    private LeftAdapter leftAdapter;
    public UserInfo info;
    private TextView text_advent;
    private int TVADWITH = 111;
    private int TVADPOSITION = 0;
    private List<String> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_car_left, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
        queryUser();
        TextViewRollAd();
    }

    @Override
    public void onPause() {
        super.onPause();
        TVADPOSITION = 0;
        mHandler.removeMessages(TVADWITH);
    }

    private void initView() {
        text_advent = getView(rootView, R.id.text_advent);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        recyclerView = getView(rootView, R.id.recyclerView);
        banner = getView(rootView, R.id.banner);
        swipeToLoadLayout.setOnRefreshListener(this);
        text_advent.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        list.add("七夕浪漫势头难挡，情人节当天再掀购花狂潮。");
        list.add("学习同行业的经验叫创新.学习其他行业的经验叫革命");
        list.add("无边落木萧萧下，不尽长江滚滚来。");
        list.add("万里悲秋常作客，百年多病独登台。");
        list.add("会当凌绝顶，一览众山小。");
        list.add("此曲只应天上有，人间能得几回闻。");
        list.add("国破山河在，城春草木深。感时花溅泪，恨别鸟惊心。");
        list.add("安得广厦千万间，大庇天下寒士俱欢颜，风雨不动安如山！");
        list.add("曾经沧海难为水，除却巫山不是云");

        query();
        queryList();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TVADWITH) {
                TvAnimation();
                TextViewRollAd();
            }
        }
    };


    /*******开始滚动******/
    private void TextViewRollAd() {
        TVADPOSITION++;
        mHandler.removeMessages(TVADWITH);
        mHandler.sendEmptyMessageDelayed(TVADWITH, 3000);
    }


    public void updateView() {
        //设置图片网址或地址的集合
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        banner.setImages(banners);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
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
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_advent:
                startActivity(new Intent(getContext(), AdvertActivity.class));
                break;
        }
    }


    /******广告*****/
    public void query() {
        showProgressDialog(getActivity(), false);
        String sign = "advertType=" + Constants.ADVER + "&pagecount=4" + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("advertType", Constants.ADVER);
        params.put("pagecount", "4");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVERT, params, Api.GET_ADVERT_ID, this);
    }

    /******车生活三方服务列表信息*****/
    public void queryList() {
        showProgressDialog(getActivity(), false);
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVERT_TAG, params, Api.GET_ADVERT_TAG_ID, this);
    }


    /******查询个人资料*****/
    public void queryUser() {
        showProgressDialog(getActivity(), false);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MEID_USER, params, Api.GET_MEID_USER_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_ADVERT_ID:
                        banners = JsonParse.getBannerListJson(object);
                        if (banner != null && banners.size() > 0) {
                            updateView();
                        }
                        break;
                    case Api.GET_ADVERT_TAG_ID:
                        voList = JsonParse.getTagJson(object);
                        if (voList != null && voList.size() > 0) {
                            setAdapter();
                        }
                        break;
                    case Api.GET_MEID_USER_ID:
                        info = JsonParse.getUserInfo(object);
                        break;

                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    private void setAdapter() {
        leftAdapter = new LeftAdapter(this, voList);
        recyclerView.setAdapter(leftAdapter);
    }

    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void OnBannerClick(int position) {
        String url = banners.get(position).getUrl();
        if (!Utility.isEmpty(url) && !url.equals("www.jkabe.com")) {
            Intent intent = new Intent(getContext(), PreviewActivity.class);
            intent.putExtra("name", banners.get(position).getAdvertName());
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        queryList();
        query();
    }


    private void TvAnimation() {
        TranslateAnimation downTranslateAnimation = new TranslateAnimation(0, 0, 0, -text_advent.getHeight());
        downTranslateAnimation.setDuration(500);
        downTranslateAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(500);

        AnimationSet animationSetOut = new AnimationSet(true);
        animationSetOut.addAnimation(downTranslateAnimation);
        animationSetOut.addAnimation(alphaAnimation);
        text_advent.startAnimation(animationSetOut);

        animationSetOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int iii = TVADPOSITION % list.size();
                text_advent.setText(list.get(iii));
                topTranslateAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void topTranslateAnimation() {
        TranslateAnimation topTranslateAnimation = new TranslateAnimation(0, 0, text_advent.getHeight(), 0);
        topTranslateAnimation.setDuration(500);
        topTranslateAnimation.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);

        AnimationSet animationSetIn = new AnimationSet(true);
        animationSetIn.addAnimation(topTranslateAnimation);
        animationSetIn.addAnimation(alphaAnimation);
        text_advent.startAnimation(animationSetIn);
        animationSetIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
    }
}
