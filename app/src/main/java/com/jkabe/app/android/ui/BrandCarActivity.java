package com.jkabe.app.android.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jkabe.box.R;
import com.jkabe.app.android.adapter.BrandAdapter;
import com.jkabe.app.android.adapter.InventoryAdapter;
import com.jkabe.app.android.adapter.RightAdapter;
import com.jkabe.app.android.adapter.RightAdapter2;
import com.jkabe.app.android.adapter.RightBrandAdapter;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.bean.Brand;
import com.jkabe.app.android.bean.BrandVo;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.bean.YearCar;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.JsonParse;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.ToastUtil;
import com.jkabe.app.android.util.Utility;
import com.jkabe.app.android.weight.CharacterParser;
import com.jkabe.app.android.weight.PinyinComparator;
import com.jkabe.app.android.weight.SideBar;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/6/17
 * @name:品牌型号
 */
public class BrandCarActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_brandNmae;
    private ListView mListView;
    private InventoryAdapter adapter;
    private BrandAdapter brandAdapter1;
    private List<Brand> brands = new ArrayList<>();
    private List<BrandVo> brandVoList = new ArrayList<>();
    private List<Brand> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private SideBar slideBar;
    private RecyclerView rv_right;
    private PinyinComparator pinyinComparator;
    private RightAdapter rightAdapter;
    private DrawerLayout mDrawerLayout;
    private List<YearCar> brandList = new ArrayList<>();
    private RightAdapter2 rightAdapter2;
    private RightBrandAdapter brandAdapter;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_brand_car);
    }

    @Override
    protected void initView() {
        mDrawerLayout = getView(R.id.mDrawerLayout);
        text_brandNmae = getView(R.id.text_brandNmae);
        rv_right = getView(R.id.rv_right);
        slideBar = getView(R.id.slideBar);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_text_tv.setText("品牌型号");
        title_left_btn.setOnClickListener(this);
        mListView = getView(R.id.mListView);
        View rootView = LayoutInflater.from(this).inflate(R.layout.header_listview, null, false);
        recyclerView = getView(rootView, R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        mListView.addHeaderView(rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_right.setLayoutManager(layoutManager);

    }

    @Override
    protected void initData() {
        pinyinComparator = new PinyinComparator();
        query();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
        }

    }


    /*******查询
     * @param ********/
    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_USER_BRAND, params, Api.GET_USER_BRAND_ID, this);
    }


    /*******查询厂商
     * @param ********/
    public void queryFactory(String id) {
        String sign = "modelid=" + id + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("modelid", id);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_USER_LISR, params, Api.GET_USER_LISR_ID, this);
    }


    /*******年款车型
     * @param ********/
    public void queryYear(String id) {
        String sign = "modelid=" + id + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("modelid", id);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_YEAR_LISR, params, Api.GET_YEAR_LISR_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_USER_BRAND_ID:
                        brands = JsonParse.getBespokebrandsJson(object);
                        if (brands != null && brands.size() > 0) {
                            setAdapter();
                        }
                        break;
                    case Api.GET_USER_LISR_ID:
                        brandVoList = JsonParse.getBespokebrandsJson1(object);
                        if (brandVoList != null && brandVoList.size() > 0) {
                            setRightAdapter();
                        }
                        break;

                    case Api.GET_YEAR_LISR_ID:
                        brandList = JsonParse.getbrandList(object);
                        if (brandList != null && brandList.size() > 0) {
                            brandListAdapter();
                        } else {
                            Intent intent = new Intent();
                            intent.putExtra("business", business);
                            intent.putExtra("factory", factory);
                            intent.putExtra("model", model);
                            intent.putExtra("yearmodel", yearmodel);
                            setResult(100, intent);
                            finish();
                        }
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    /******年型列表******/
    private String business, factory, model, yearmodel;

    private void brandListAdapter() {
        brandAdapter = new RightBrandAdapter(this, brandList);
        rv_right.setAdapter(brandAdapter);
        brandAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yearmodel = brandList.get(position).getModelName();
                business= brandList.get(position).getId();
                Intent intent = new Intent();
                intent.putExtra("business",  brandList.get(position).getId());
                intent.putExtra("factory", factory);
                intent.putExtra("model", model);
                intent.putExtra("yearmodel", yearmodel);
                setResult(100, intent);
                finish();
            }
        });
    }

    /******厂商列表******/
    public void setRightAdapter() {
        rightAdapter = new RightAdapter(this, brandVoList);
        rv_right.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                business = brandVoList.get(position).getBusiness().getFullName();//厂商
                setBrandAdapter(brandVoList.get(position).getItems());
            }
        });
    }

    /******品牌列表******/
    public void setBrandAdapter(List<BrandVo.ItemsBean> itemsBeans) {
        rightAdapter2 = new RightAdapter2(this, itemsBeans);
        rv_right.setAdapter(rightAdapter2);
        rightAdapter2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model = itemsBeans.get(position).getModelName();//厂商
                queryYear(itemsBeans.get(position).getId());
            }
        });
    }


    /******品牌列表******/
    private void setAdapter() {
        brands = filledData();
        Collections.sort(brands, pinyinComparator);
        adapter = new InventoryAdapter(this, brands);
        mListView.setAdapter(adapter);
        slideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.openDrawer(Gravity.END);
                text_brandNmae.setText(brands.get(position - 1).getFullName());
                factory = brands.get(position - 1).getFullName();//厂商
                queryFactory(brands.get(position - 1).getId());
            }
        });


        for (int i = 0; i < brands.size(); i++) {
            if ("1".equals(brands.get(i).getIscommonuse() + "")) {
                list.add(brands.get(i));
            }
        }
        brandAdapter1 = new BrandAdapter(this, list);
        recyclerView.setAdapter(brandAdapter1);
        brandAdapter1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.openDrawer(Gravity.END);
                text_brandNmae.setText(brands.get(position).getFullName());
                factory = brands.get(position).getFullName();//厂商
                queryFactory(list.get(position).getId());
            }
        });
    }


    /**
     * 为ListView填充数据
     *
     * @return
     */
    private List<Brand> filledData() {
        List<Brand> mSortList = new ArrayList<>();
        for (int i = 0; i < brands.size(); i++) {
            Brand brand = brands.get(i);
            String pinyin = CharacterParser.getSelling(brand.getModelName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                brand.setSortLetters(sortString.toUpperCase());
            } else {
                brand.setSortLetters("#");
            }
            mSortList.add(brand);
        }
        return mSortList;

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
