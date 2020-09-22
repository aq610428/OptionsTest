package com.jkabe.app.box.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.CarLiftAdapter;
import com.jkabe.app.box.adapter.WareAdapter;
import com.jkabe.app.box.banner.Banner;
import com.jkabe.app.box.banner.BannerConfig;
import com.jkabe.app.box.banner.Transformer;
import com.jkabe.app.box.banner.listener.OnBannerListener;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.BannerVo;
import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.app.box.bean.LeftVo;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.ui.BindActivity;
import com.jkabe.app.box.ui.LocationActivity;
import com.jkabe.app.box.ui.PreviewActivity;
import com.jkabe.app.box.ui.StoreDeilActivity;
import com.jkabe.app.box.ui.StoreListActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.app.box.weight.MyLoader;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/9/22
 * @name:车生活
 */
public class LeftFragment extends BaseFragment implements OnBannerListener, NetWorkListener, OnRefreshListener {
    private View rootView;
    private Banner banner;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView, recyclerView1;
    private List<BannerVo> banners = new ArrayList<>();
    private List<LeftVo> voList = new ArrayList<>();
    private List<LeftVo.ItemsBean> items = new ArrayList<>();
    private CarLiftAdapter leftAdapter;
    public UserInfo info;
    private WareAdapter wareAdapter;
    private List<ImageInfo> list = new ArrayList<>();


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
    }


    private void initView() {
        recyclerView1 = getView(rootView, R.id.recyclerView1);
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        recyclerView = getView(rootView, R.id.recyclerView);
        banner = getView(rootView, R.id.banner);
        swipeToLoadLayout.setOnRefreshListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(layoutManager);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(gridLayoutManager);

        query();
        queryList();
    }

    @Override
    protected void lazyLoad() {
        list.add(new ImageInfo("https://img30.360buyimg.com/n7/jfs/t1/33185/14/3374/269370/5cb53d5dEd76ae894/c76a6a6f83a3d9eb.jpg"));
        list.add(new ImageInfo("https://img30.360buyimg.com/n7/jfs/t1/126045/20/8880/167316/5f2b6de2Ef0d3179f/8b45a499638feee7.jpg"));
        list.add(new ImageInfo("https://img30.360buyimg.com/n7/jfs/t1/120102/7/3025/433649/5ecc6e7bE10054534/7dce38b9810457dc.jpg"));
        list.add(new ImageInfo("https://img30.360buyimg.com/n7/jfs/t1/134629/24/8373/92564/5f472824E0e5b7801/7e7c65d1de3a5223.jpg"));
        list.add(new ImageInfo("https://img30.360buyimg.com/n7/jfs/t1/124028/5/4166/161651/5ed9e96aE4ad3a955/625ad0c22be6af90.jpg"));
        list.add(new ImageInfo("https://img13.360buyimg.com/n7/jfs/t1/126342/35/10774/571936/5f44980cEd63eee15/af1b53fd9e6d9aeb.jpg"));
        list.add(new ImageInfo("https://img30.360buyimg.com/n2/jfs/t1/127385/11/9463/153193/5f338f0aEb1861c3a/e0698c1bd3c73bab.jpg"));
        list.add(new ImageInfo("https://img30.360buyimg.com/n7/jfs/t1/134750/21/3999/91346/5f0701daE7d786cac/acc053adda2fb5b9.jpg"));
        wareAdapter=new WareAdapter(getContext(),list);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(wareAdapter);
    }


    @Override
    public void onRefresh() {
        queryList();
        query();
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
        items.clear();
        for (int i = 0; i < voList.size(); i++) {
            items.addAll(voList.get(i).getItems());
        }
        leftAdapter = new CarLiftAdapter(getContext(), items);
        recyclerView.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = items.get(position).getName();
                Intent intent;
                switch (name) {
                    case "惠保养":
                        if (SaveUtils.getCar() != null && !Utility.isEmpty(SaveUtils.getCar().getImeicode())) {
                            if (info.getIsstore() == 2) {
                                intent = new Intent(getContext(), StoreDeilActivity.class);
                            } else {
                                intent = new Intent(getContext(), StoreListActivity.class);
                            }
                            startActivity(intent);
                        } else {
                            ToastUtil.showToast("您未绑定车辆，请绑定后再试");
                            getActivity().startActivity(new Intent(getContext(), BindActivity.class));
                        }
                        break;
                    case "惠购车":
                        String url = items.get(position).getUrl();
                        intent = new Intent(getContext(), PreviewActivity.class);
                        intent.putExtra("name", "惠购车");
                        intent.putExtra("url", url);
                        startActivity(intent);
                        break;
                    case "惠保险":
                        startActivity(new Intent(getContext(), LocationActivity.class));
                        break;
                    case "车油惠":
                        checkLogin();
                        break;
                    case "万车品":
                        intent = new Intent(getContext(), PreviewActivity.class);
                        intent.putExtra("name", "万车品");
                        intent.putExtra("url", items.get(position).getUrl() + getWcp());
                        startActivity(intent);
                        break;
                    case "一键救援":
                        intent = new Intent(getContext(), PreviewActivity.class);
                        intent.putExtra("name", "一键救援");
                        intent.putExtra("url", items.get(position).getUrl());
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    public void updateView() {
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
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    private void checkLogin() {
        CarInfo info = SaveUtils.getCar();
        if (info == null || Utility.isEmpty(info.getImeicode())) {
            ToastUtil.showToast("您未绑定车辆，请绑定后再试");
            getActivity().startActivity(new Intent(getContext(), BindActivity.class));
            return;
        }

        if ((info.getIsreal() == 0 || info.getIsreal() == 3)) {
            DialogUtils.showTipDialog(getContext(), "您的信息不全,请先认证");
        } else if ((info.getIsreal() == 2)) {
            ToastUtil.showToast("还在审核中");
        } else {
            Intent intent = new Intent(getContext(), PreviewActivity.class);
            intent.putExtra("name", "车油惠");
            intent.putExtra("url", Constants.oilUrl + "?memberid=" + SaveUtils.getSaveInfo().getId());
            startActivity(intent);
        }
    }

    private String getWcp() {
        String phone = SaveUtils.getSaveInfo().getMobile();
        String source = "king";
        String uid = SaveUtils.getSaveInfo().getId();
        long timeM = System.currentTimeMillis();
        String sign = Md5Util.encode(phone + uid + timeM + source + "wcp20200402");
        return "?phone=" + phone + "&source=" + source + "&uid=" + uid + "&timestamp=" + timeM + "&sign=" + sign;
    }
}
