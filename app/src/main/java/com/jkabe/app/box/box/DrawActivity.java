package com.jkabe.app.box.box;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.Typeitems;
import com.jkabe.app.box.bean.Usdinfo;
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.app.box.weight.RuntimeRationale;
import com.jkabe.app.box.zxing.android.CaptureActivity;
import com.jkabe.box.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:充币
 */
public class DrawActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_copy, text_dig, text_service, text_num_box, text_service_box, text_user;
    private UsdtBean usdtBean;
    private String coinTypeId = "2";
    private EditText text_address, et_num, et_password;
    private List<Typeitems> typeitems = new ArrayList<>();
    private List<Usdinfo> usdinfos = new ArrayList<>();
    private ImageView iv_code;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_draw);
    }


    @Override
    protected void initView() {
        iv_code = getView(R.id.iv_code);
        et_password = getView(R.id.et_password);
        text_user = getView(R.id.text_user);
        text_num_box = getView(R.id.text_num_box);
        text_service_box = getView(R.id.text_service_box);
        text_address = getView(R.id.text_address);
        et_num = getView(R.id.et_num);
        text_service = getView(R.id.text_service);
        text_dig = getView(R.id.text_dig);
        text_copy = getView(R.id.text_copy);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_copy.setOnClickListener(this);
        title_text_tv.setText("提币");
        text_dig.setOnClickListener(this);
        iv_code.setOnClickListener(this);
        coinTypeId = getIntent().getStringExtra("coinTypeId");
        usdtBean = (UsdtBean) getIntent().getSerializableExtra("usdtBean");
        if (usdtBean != null) {
            if (coinTypeId.equals(usdtBean.getBox().getCoinTypeId())) {
                text_user.setText("可用" + usdtBean.getBox().getUserable());
                text_dig.setText(usdtBean.getBox().getCoinTypeName());
                text_num_box.setText(usdtBean.getBox().getCoinTypeName());
                text_service_box.setText(usdtBean.getBox().getCoinTypeName());
            } else {
                text_user.setText("可用" + usdtBean.getUsdt().getUserable());
                text_dig.setText(usdtBean.getUsdt().getCoinTypeName());
                text_num_box.setText(usdtBean.getUsdt().getCoinTypeName());
                text_service_box.setText(usdtBean.getUsdt().getCoinTypeName());
            }
        }
    }

    @Override
    protected void initData() {
        query();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_dig:
                showUsdt();
                break;
            case R.id.text_copy:
                save();
                break;
            case R.id.iv_code:
                checkPermission();
                break;
        }
    }

    private void checkPermission() {
        AndPermission.with(this).runtime().permission(Permission.CAMERA)
                .rationale(new RuntimeRationale())
                .onGranted(permissions -> {
                    Intent intent = new Intent(DrawActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                })
                .onDenied(permissions -> {
                    if (AndPermission.hasAlwaysDeniedPermission(DrawActivity.this, permissions)) {
                        showSettingDialog(DrawActivity.this, permissions);
                    }
                })
                .start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            String content = data.getStringExtra(DECODED_CONTENT_KEY);
            if (!Utility.isEmpty(content)) {
                text_address.setText(content + "");
            }
        }
    }


    public void save() {
        String address = text_address.getText().toString();
        String balance = et_num.getText().toString();
        String password = et_password.getText().toString();

        if (Utility.isEmpty(address)) {
            ToastUtil.showToast("提币地址不能为空");
            return;
        }
        if (Utility.isEmpty(balance)) {
            ToastUtil.showToast("提币数量不能为空");
            return;
        }
        double num = Double.parseDouble(balance);
        if (num < minBalance) {
            ToastUtil.showToast("提币数量不能小于" + minBalance);
            return;
        }
        if (num > maxBalance) {
            ToastUtil.showToast("提币数量不能大于" + maxBalance);
            return;
        }
        if (num > userable) {
            ToastUtil.showToast("余额不足");
            return;
        }


        if (Utility.isEmpty(password)) {
            ToastUtil.showToast("支付密码不能为空");
            return;
        }
        String sign = "address=" + address + "&balance=" + balance + "&coinTypeId=" + coinTypeId + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID
                + "&paypassword=" + Md5Util.encode(password)
                + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("address", address);
        params.put("balance", balance);
        params.put("coinTypeId", coinTypeId);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("paypassword", Md5Util.encode(password));
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.post(Api.MINING_WITH_BOX, params, Api.MINING_WITH_BOX_ID, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("0".equals(SaveUtils.getSaveInfo().getIsPayPassword() + "")) {//未设置
            DialogUtils.showPassword(this);
        }
    }

    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.MINING_DDL_BOX, params, Api.MINING_DDL_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.MINING_WITH_BOX_ID:
                        et_num.setText("");
                        text_address.setText("");
                        ToastUtil.showToast(commonality.getErrorDesc() + "");
                        finish();
                        break;
                    case Api.MINING_DDL_BOX_ID:
                        typeitems = JsonParse.getATypeitems(object);
                        usdinfos = JsonParse.getATypeitemsUsdt(object);
                        if (typeitems != null && typeitems.size() > 0) {
                            update();
                        }
                        if (usdinfos != null && usdinfos.size() > 0) {
                            updateView();
                        }
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        } else {
            ToastUtil.showToast(commonality.getErrorDesc());
        }
        stopProgressDialog();
    }

    /*****余额*****/
    double userable, serviceFee;
    double minBalance, maxBalance;

    private void updateView() {
        for (int i = 0; i < usdinfos.size(); i++) {
            Usdinfo bean = usdinfos.get(i);
            if (coinTypeId.equals(bean.getCoinTypeId())) {
                userable = bean.getUserable();
                text_user.setText("可用" + bean.getUserable() + "");
            }
        }
    }

    private void update() {
        for (int i = 0; i < typeitems.size(); i++) {
            Typeitems bean = typeitems.get(i);
            if (coinTypeId.equals(bean.getCoinTypeId())) {
                serviceFee = bean.getServiceFee();
                minBalance = bean.getMinBalance();
                maxBalance = bean.getMaxBalance();
                text_service.setText(bean.getServiceFee() + "");
                et_num.setHint("最小提币数" + minBalance);
            }
        }
    }

    public void showUsdt() {
        Dialog dialog = new Dialog(this, R.style.dialog_bottom_full);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_map, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(view);
        view.findViewById(R.id.text_usd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usdtBean != null && usdtBean.getUsdt() != null) {
                    coinTypeId = usdtBean.getUsdt().getCoinTypeId();
                    text_dig.setText(usdtBean.getUsdt().getCoinTypeName());
                    text_num_box.setText(usdtBean.getUsdt().getCoinTypeName());
                    text_service_box.setText(usdtBean.getUsdt().getCoinTypeName());
                    updateView();
                    update();
                }
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.text_bc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usdtBean != null && usdtBean.getBox() != null) {
                    coinTypeId = usdtBean.getBox().getCoinTypeId();
                    text_dig.setText(usdtBean.getBox().getCoinTypeName());
                    text_num_box.setText(usdtBean.getBox().getCoinTypeName());
                    text_service_box.setText(usdtBean.getBox().getCoinTypeName());
                    updateView();
                    update();
                }
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
