package com.jkabe.app.android.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.android.base.BaseActivity;
import com.jkabe.app.android.base.BaseApplication;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.glide.GlideUtils;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.LogUtils;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.ToastUtil;
import com.jkabe.app.android.weight.MediaLoader;
import com.jkabe.app.android.weight.RuntimeRationale;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author: zt
 * @date: 2020/7/8
 * @name:LicenseActivity
 */
public class LicenseActivity extends BaseActivity {
    private ImageView iv_photo;
    private RelativeLayout rl_photo;
    private TextView title_text_tv, title_left_btn;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_license);
        BaseApplication.activityTaskManager.putActivity("LicenseActivity", this);
    }

    @Override
    protected void initView() {
        iv_photo = getView(R.id.iv_photo);
        rl_photo = getView(R.id.rl_photo);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("驾驶证");
        rl_photo.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.rl_photo:
                request();
                break;
        }
    }

    private void request() {
        AndPermission.with(this).runtime().permission(Permission.CAMERA)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        selectPhoto();
                    }
                })
                .onDenied(permissions -> {
                    if (AndPermission.hasAlwaysDeniedPermission(LicenseActivity.this, permissions)) {
                        showSettingDialog(LicenseActivity.this, permissions);
                    }
                })
                .start();
    }


    private void selectPhoto() {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(new MediaLoader())
                .build());
        ArrayList<AlbumFile> albumFiles = new ArrayList<>();
        Album.image(this) // Image selection.
                .multipleChoice()
                .camera(true)
                .columnCount(1)
                .selectCount(1)
                .checkedList(albumFiles)
                .onResult(result -> {
                    if (result != null && result.size() > 0) {
                        String path = result.get(0).getPath();
                        initLuban(path);
                    }
                })
                .onCancel(result -> LogUtils.e("已"))
                .start();
    }

    /*****图片压缩*****/
    private void initLuban(String path) {
        Luban.with(this).load(new File(path)).ignoreBy(100)
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        if (file != null) {
                            upLoad(file);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
    }


    String result;

    private void upLoad(File file) {
        String sign = "id=" + SaveUtils.getCar().getId() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("id",SaveUtils.getCar().getId());
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        OkGo.<String>post(Api.GET_UPLOAD_IMG).isMultipart(true).tag(BaseApplication.getContext()).params(params).params("file", file).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        LogUtils.e(jsonObject);
                        result = jsonObject.optString("result");
                        GlideUtils.CreateImageRound(result,iv_photo,5);
                        ToastUtil.showToast(jsonObject.optString("message")+"");
                        SaveUtils.getCar().setIsreal(2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                stopProgressDialog();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                stopProgressDialog();
            }
        });
    }


}
