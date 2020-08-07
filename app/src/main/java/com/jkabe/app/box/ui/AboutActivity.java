package com.jkabe.app.box.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.Verison;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.RuntimeRationale;
import com.jkabe.app.box.weight.UpdateManager;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/27
 * @name:关于我们
 */
public class AboutActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_edition;
    private RelativeLayout rl_edition, rl_tab,rl_tab1;
    private Verison verison;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
        BaseApplication.activityTaskManager.putActivity("AboutActivity", this);
    }

    @Override
    protected void initView() {
        rl_tab1= getView(R.id.rl_tab1);
        text_edition = getView(R.id.text_edition);
        rl_tab = getView(R.id.rl_tab);
        rl_edition = getView(R.id.rl_edition);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("关于我们");
        rl_edition.setOnClickListener(this);
        rl_tab.setOnClickListener(this);
        text_edition.setText("当前版本 v" + SystemTools.getAppVersionName(this));
        rl_tab1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.rl_edition:
                query();
                break;
            case R.id.rl_tab1:
                Intent intent=new Intent(this, PreviewActivity.class);
                intent.putExtra("name","用户协议");
                intent.putExtra("url","http://kb.jkabe.com/resource/useragreement");
                startActivity(intent);
                break;
        }
    }

    /*****检测是否具有读写权限******/
    public void applyPermission() {
        AndPermission.with(this).runtime().permission(Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        UpdateVerison();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(AboutActivity.this, permissions)) {
                            showSettingDialog(AboutActivity.this, permissions);
                        }
                    }
                })
                .start();
    }


    /*****检测是否具有安装未知来源的权限******/
    public void UpdateVerison() {
        new UpdateManager(this).checkForceUpdate(verison);
    }



    @Override
    protected void initData() {

    }

    /*******查询首页数据
     * @param ********/
    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_INTERGRAL_VERSION, params, Api.GET_INTERGRAL_VERSION_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_INTERGRAL_VERSION_ID:
                        verison = JsonParse.getVerisonUserInfo(object);
                        if (verison != null) {
                            int code = SystemTools.getAppVersionCode(this);
                            if (!Utility.isEmpty(verison.getVersionIndex())) {
                                int versionCode = Integer.parseInt(verison.getVersionIndex());
                                if (versionCode > code) {
                                    applyPermission();
                                } else {
                                    ToastUtil.showToast("暂无新版本");
                                }
                            }

                        }
                        break;
                }
            }
        }
        stopProgressDialog();
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
