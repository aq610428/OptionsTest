package com.jkabe.app.box.box;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.AddressInfo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UsdtBean;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.ClipboardUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.QRCodeUtil;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/7/21
 * @name:充币
 */
public class RechargeActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_style, text_copy, text_dig;
    private ImageView iv_code;
    private UsdtBean usdtBean;
    private List<AddressInfo> infos = new ArrayList<>();
    private String coinTypeId = "2";


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    protected void initView() {
        text_dig = getView(R.id.text_dig);
        text_copy = getView(R.id.text_copy);
        iv_code = getView(R.id.iv_code);
        text_style = getView(R.id.text_style);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("充币");
        text_copy.setOnClickListener(this);
        text_dig.setOnClickListener(this);
        iv_code.setOnClickListener(this);
        usdtBean = (UsdtBean) getIntent().getSerializableExtra("usdtBean");
        coinTypeId=getIntent().getStringExtra("coinTypeId");
        if (!Utility.isEmpty(coinTypeId)){
            if ("1".equals(coinTypeId)){
                text_dig.setText(usdtBean.getUsdt().getCoinTypeName());
            }else{
                text_dig.setText(usdtBean.getBox().getCoinTypeName());
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
            case R.id.text_copy:
                String text = text_style.getText().toString();
                ClipboardUtils.copyText(text);
                ToastUtil.showToast("复制成功");
                break;
            case R.id.text_dig:
                showUsdt();
                break;
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
        okHttpModel.get(Api.MINING_ADDRESS_BOX, params, Api.MINING_ADDRESS_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.MINING_ADDRESS_ID:
                        infos = JsonParse.getAddressInfo(object);
                        if (infos != null && infos.size() > 0) {
                            updateView();
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
    }

    private void updateView() {
        for (int i = 0; i < infos.size(); i++) {
            if (coinTypeId.equals(infos.get(i).getCoinTypeId() + "")) {
                text_style.setText(infos.get(i).getAddress()+"");
                Bitmap mBitmap = QRCodeUtil.createQRCode(infos.get(i).getAddress(), 600);
                iv_code.setImageBitmap(mBitmap);
            }
        }
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
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
                    updateView();
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
                    updateView();
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
}
