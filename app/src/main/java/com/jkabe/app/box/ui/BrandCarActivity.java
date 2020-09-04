package com.jkabe.app.box.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.weight.RuntimeRationale;
import com.jkabe.app.box.weight.VehicleKeyboardHelper;
import com.jkabe.app.box.zxing.android.CaptureActivity;
import com.jkabe.box.R;
import com.jkabe.app.box.adapter.BrandAdapter;
import com.jkabe.app.box.adapter.InventoryAdapter;
import com.jkabe.app.box.adapter.RightAdapter;
import com.jkabe.app.box.adapter.RightAdapter2;
import com.jkabe.app.box.adapter.RightBrandAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.Brand;
import com.jkabe.app.box.bean.BrandVo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.YearCar;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.CharacterParser;
import com.jkabe.app.box.weight.PinyinComparator;
import com.jkabe.app.box.weight.SideBar;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

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
                text_brandNmae.setText(list.get(position).getFullName());
                factory = list.get(position).getFullName();//厂商
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

    /**
     * @author: zt
     * @date: 2020/7/17
     * @name:绑定车辆
     */
    public static class BindActivity extends BaseActivity implements NetWorkListener {
        private TextView title_text_tv, title_left_btn, text_bind;
        private TextView text_brand;
        private ImageView iv_code;
        private EditText cardName, cardNum, et_code, et_course, et_discern, et_engine;


        @Override
        protected void initCreate(Bundle savedInstanceState) {
            setContentView(R.layout.activity_bind);
        }

        @Override
        protected void initView() {
            et_engine = getView(R.id.et_engine);
            et_discern = getView(R.id.et_discern);
            et_course = getView(R.id.et_course);
            et_code = getView(R.id.et_code);
            text_bind = getView(R.id.text_bind);
            cardNum = getView(R.id.et_cardnum);
            cardName = getView(R.id.et_cardName);
            iv_code = getView(R.id.iv_code);
            text_brand = getView(R.id.text_brand);
            title_text_tv = getView(R.id.title_text_tv);
            title_left_btn = getView(R.id.title_left_btn);
            title_left_btn.setOnClickListener(this);
            text_brand.setOnClickListener(this);
            iv_code.setOnClickListener(this);
            text_bind.setOnClickListener(this);
            title_text_tv.setText("绑定车辆");
            VehicleKeyboardHelper.bind(cardNum, this);
        }

        @Override
        protected void initData() {

        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            VehicleKeyboardHelper.hideCustomInput(cardNum);
            switch (v.getId()) {
                case R.id.title_left_btn:
                    finish();
                    break;
                case R.id.text_brand:
                    Intent intent = new Intent(this, BrandCarActivity.class);
                    startActivityForResult(intent, 100);
                    break;
                case R.id.iv_code:
                    checkPermission();
                    break;
                case R.id.text_bind:
                    checkData();
                    break;
            }
        }


        private String yearmodelid, factory, model, yearmodel;

        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 100 && data != null) {
                yearmodelid = data.getStringExtra("business");
                factory = data.getStringExtra("factory");
                model = data.getStringExtra("model");
                yearmodel = data.getStringExtra("yearmodel");
                text_brand.setText(factory + model + yearmodel);
            } else if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                if (!Utility.isEmpty(content)) {
                    String str[] = content.split("#");
                    if (str != null && str.length == 2) {
                        cardName.setText(str[0]);
                        et_code.setText(str[1]);
                    }else if (str!=null&&str.length==1){
                        cardName.setText(str[0]);
                        et_code.setText("1234");
                    }
                }

                LogUtils.e("解码结果： \n" + content);
            }
        }


        private void checkData() {
            String imeicode = cardName.getText().toString().trim();//设备号
            String carcard = cardNum.getText().toString().trim();//车牌
            String activecode = et_code.getText().toString().trim();//激活码
            String yearmodelid = text_brand.getText().toString().trim();//车辆品牌
            String initmileage = et_course.getText().toString().trim();
            String vinno = et_discern.getText().toString().trim();
            String engineno = et_engine.getText().toString().trim();

            if (Utility.isEmpty(imeicode)) {
                ToastUtil.showToast("设备编号不能为空");
                return;
            }
            if (Utility.isEmpty(carcard)) {
                ToastUtil.showToast("车牌号不能为空");
                return;
            }
            if (!SystemTools.isCarnumberNO(carcard)) {
                ToastUtil.showToast("车牌号不合法,请重新输入");
                return;
            }

            if (Utility.isEmpty(activecode)) {
                ToastUtil.showToast("设备激活码不能为空");
                return;
            }

            if (Utility.isEmpty(yearmodelid)) {
                ToastUtil.showToast("车辆品牌不能为空");
                return;
            }

            if (Utility.isEmpty(initmileage)) {
                ToastUtil.showToast("车辆里程不能为空");
                return;
            }


            if (Utility.isEmpty(vinno)) {
                ToastUtil.showToast("车辆车辆识别代码不能为空");
                return;
            }


            if (Utility.isEmpty(engineno)) {
                ToastUtil.showToast("车辆发动机号不能为空");
                return;
            }
            query();
        }


        private void query() {
            String imeicode = cardName.getText().toString().trim();//设备号
            String carcard = cardNum.getText().toString().trim();//车牌
            String activecode = et_code.getText().toString().trim();//激活码
            String initmileage = et_course.getText().toString().trim();
            String vinno = et_discern.getText().toString().trim();
            String engineno = et_engine.getText().toString().trim();
            String sign = "activecode=" + activecode + "&carcard=" + carcard + "&engineno=" + engineno + "&imeicode=" + imeicode
                    + "&initmileage=" + initmileage + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID
                    + "&vinno=" + vinno + "&yearmodelid=" + yearmodelid + Constants.SECREKEY;
            showProgressDialog(this, false);
            Map<String, String> params = okHttpModel.getParams();
            params.put("activecode", activecode + "");
            params.put("carcard", carcard + "");
            params.put("engineno", engineno + "");
            params.put("imeicode", imeicode + "");
            params.put("initmileage", initmileage + "");
            params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
            params.put("partnerid", Constants.PARTNERID);
            params.put("vinno", vinno + "");
            params.put("yearmodelid", yearmodelid + "");
            params.put("apptype", Constants.TYPE);
            params.put("sign", Md5Util.encode(sign));
            okHttpModel.get(Api.GET_MODEL_BIND, params, Api.GET_MODEL_BIND_ID, this);
        }


        @Override
        public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
            if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
                if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                    switch (id) {
                        case Api.GET_MODEL_BIND_ID:
                            ToastUtil.showToast(commonality.getErrorDesc());
                            finish();
                            break;
                    }
                } else {
                    ToastUtil.showToast(commonality.getErrorDesc());
                }
            }
            stopProgressDialog();
        }


        private void checkPermission() {
            AndPermission.with(this).runtime().permission(Permission.CAMERA)
                    .rationale(new RuntimeRationale())
                    .onGranted(permissions -> {
                        Intent intent = new Intent(BindActivity.this, CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                    })
                    .onDenied(permissions -> {
                        if (AndPermission.hasAlwaysDeniedPermission(BindActivity.this, permissions)) {
                            showSettingDialog(BindActivity.this, permissions);
                        }
                    })
                    .start();
        }


        @Override
        public void onFail() {

        }

        @Override
        public void onError(Exception e) {

        }
    }
}
