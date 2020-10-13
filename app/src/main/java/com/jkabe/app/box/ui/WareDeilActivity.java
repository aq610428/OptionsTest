package com.jkabe.app.box.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jkabe.app.box.adapter.GoodAdapter;
import com.jkabe.app.box.banner.Banner;
import com.jkabe.app.box.banner.BannerConfig;
import com.jkabe.app.box.banner.Transformer;
import com.jkabe.app.box.banner.listener.OnBannerListener;
import com.jkabe.app.box.base.BaseActivity1;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.GoodBean;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.MyLoader1;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/9/23
 * @name:商品详情
 */
public class WareDeilActivity extends BaseActivity1 implements OnBannerListener, NetWorkListener {
    private Banner banner;
    private TextView text_bat, text_num, text_name, text_time, text_cart_share, text_num_cart;
    private ImageView iv_left;
    private GoodBean goodBean;
    private WebView mWebView;
    private RecyclerView recyclerView;
    private GoodAdapter goodAdapter;
    private TextView text_cart_add, text_cart, text_pay, text_number;
    private LinearLayout ll_redue, ll_add;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_waredeil);
    }


    @Override
    protected void initView() {
        text_num_cart = getView(R.id.text_num_cart);
        ll_add = getView(R.id.ll_add);
        ll_redue = getView(R.id.ll_redue);
        text_number = getView(R.id.text_number);
        text_cart = getView(R.id.text_cart);
        text_cart_add = getView(R.id.text_cart_add);
        text_cart_share = getView(R.id.text_cart_share);
        recyclerView = getView(R.id.recyclerView);
        mWebView = getView(R.id.mWebView);
        text_time = getView(R.id.text_time);
        text_name = getView(R.id.text_name);
        text_pay = getView(R.id.text_pay);
        text_num = getView(R.id.text_num);
        iv_left = getView(R.id.iv_left);
        banner = getView(R.id.banner);
        text_bat = getView(R.id.text_bat);
        iv_left.setOnClickListener(this);
        text_pay.setOnClickListener(this);
        ll_add.setOnClickListener(this);
        text_cart.setOnClickListener(this);
        ll_redue.setOnClickListener(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setDefaultFontSize(18);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小，我设的是8M
        String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        mWebView.getSettings().setAppCachePath(appCacheDir);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        text_cart_share.setOnClickListener(this);
        text_cart_add.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        goodBean = (GoodBean) getIntent().getSerializableExtra("goodBean");
        String goodId = getIntent().getStringExtra("goodId");
        if (goodBean != null) {
            goodId=goodBean.getId();
        }
        query(goodId);
    }


    public void update() {
        updateView(goodBean.getGoodsImageList());
        text_bat.setText(goodBean.getSellPrice() + "");
        text_name.setText(goodBean.getTitle());
        text_time.setText(goodBean.getCategoryAname() + "、" + goodBean.getCategoryBname() + "、" + goodBean.getCategoryCname());
        if (!Utility.isEmpty(goodBean.getDetails())) {
            String body = "<p><img src=" + SystemTools.getNewData(goodBean.getDetails()) + " title=1602469358609072298.jpg alt=保迪斯煎锅.jpg/></p>";
            mWebView.loadDataWithBaseURL("", SystemTools.getNewDataView(body, this), "text/html", "utf-8", null);
        }
        goodBean.setGoodNumber(1);
        if (goodBean.getGoodsSpecList() != null && goodBean.getGoodsSpecList().size() > 0) {
            goodAdapter = new GoodAdapter(this, goodBean.getGoodsSpecList());
            recyclerView.setAdapter(goodAdapter);
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.text_pay:
                intent = new Intent(this, ConfirmActivity.class);
                intent.putExtra("goodBean", goodBean);
                startActivity(intent);
                break;
            case R.id.text_cart_share:
                if (Utility.isEmpty(goodBean.getFollowid())) {
                    queryLove();
                } else {
                    ToastUtil.showToast("不能重复关注");
                }
                break;
            case R.id.ll_redue:
                redueNum();
                break;
            case R.id.text_cart_add:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("index", 3);
                startActivity(intent);
                finish();
                break;

            case R.id.ll_add:
                addNum();
                break;
            case R.id.text_cart:
                if (goodBean != null) {
                    addCart();
                }
                break;

        }
    }

    //商品关注
    private void queryLove() {
        String sign = "goodid=" + goodBean.getId() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("goodid", goodBean.getId());
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_ORDER_LOVE, params, Api.PAY_ORDER_LOVE_ID, this);
    }


    /********商品详情
     * @param goodId*******/
    private void query(String goodId) {
        String sign = "id=" + goodId + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", goodId);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MallGood, params, Api.MallGood_ID, this);
    }


    /********加入购物车*******/
    private void addCart() {
        String sign = "goodid=" + goodBean.getId() + "&goodNumber=" + text_number.getText().toString() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("goodid", goodBean.getId());
        params.put("goodNumber", text_number.getText().toString());
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GOODD_MALL, params, Api.GOODD_MALL_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GOODD_MALL_ID:
                        addnum();
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                    case Api.MallGood_ID:
                        goodBean = JsonParse.getgoodBean(object);
                        if (goodBean != null) {
                            update();
                        }
                        break;
                    case Api.PAY_ORDER_LOVE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;

                }
            }

        }
        stopProgressDialog();
    }


    private void addnum() {
        text_num_cart.setVisibility(View.VISIBLE);
        String num = text_number.getText().toString();
        BigDecimal count = BigDecimalUtils.add(new BigDecimal(text_num_cart.getText().toString()), new BigDecimal(num));
        text_num_cart.setText(count.toPlainString());
        goodBean.setGoodNumber(count.intValue());
    }


    /******商品数量减******/
    private void redueNum() {
        String num = text_number.getText().toString();
        BigDecimal count = BigDecimalUtils.sub(new BigDecimal(num), BigDecimal.ONE);
        if (count.doubleValue() > 1) {
            text_number.setText(count.toPlainString());
            goodBean.setGoodNumber(count.intValue());
        } else {
            text_number.setText("1");
            goodBean.setGoodNumber(1);
        }

    }

    /******商品数量加******/
    private void addNum() {
        String num = text_number.getText().toString();
        BigDecimal count = BigDecimalUtils.add(new BigDecimal(num), BigDecimal.ONE);
        text_number.setText(count.toPlainString());
        goodBean.setGoodNumber(count.intValue());
    }


    public void updateView(List<GoodBean.GoodsImageListBean> banners) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader1());
        banner.setImages(banners);
        text_num.setText("1/" + banners.size());
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
                text_num.setText((position + 1) + "/" + banners.size());
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
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }
}
