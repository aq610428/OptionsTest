package com.jkabe.app.box.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.MediaLoader;
import com.jkabe.app.box.weight.RuntimeRationale;
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
 * @date: 2020/7/7
 * @name:UserActivity
 */
public class UserActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_nick;
    private RelativeLayout rl_nick, rl_mobile, rl_password, rl_icon;
    private int type = 0;
    private String nickname;
    private UserInfo info;
    private ImageView iv_logo;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user);
        info = SaveUtils.getSaveInfo();
        BaseApplication.activityTaskManager.putActivity("UserActivity", this);
    }

    @Override
    protected void initView() {
        iv_logo = getView(R.id.iv_logo);
        rl_icon = getView(R.id.rl_icon);
        text_nick = getView(R.id.text_nick);
        rl_mobile = getView(R.id.rl_mobile);
        rl_password = getView(R.id.rl_password);
        rl_nick = getView(R.id.rl_nick);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("个人中心");
        rl_nick.setOnClickListener(this);
        rl_password.setOnClickListener(this);
        rl_mobile.setOnClickListener(this);
        rl_icon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        queryUser();
    }


    /******查询个人资料*****/
    public void queryUser() {
        showProgressDialog(this, false);
        String sign = "memberid=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", info.getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MEID_USER, params, Api.GET_MEID_USER_ID, this);
    }


    /******修改个人资料*****/
    public void query() {
        showProgressDialog(this, false);
        String sign = "";
        Map<String, String> params = okHttpModel.getParams();
        if (type == 2) {
            sign = "memberid=" + info.getId() + "&nickname=" + nickname + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
            params.put("nickname", nickname);
        } else {
            sign = "memberid=" + info.getId() + "&mobile=" + nickname + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
            params.put("mobile", nickname);
        }
        params.put("apptype", Constants.TYPE);
        params.put("memberid", info.getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_INTERGRAL_USER, params, Api.GET_INTERGRAL_USER_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_INTERGRAL_USER_ID:
                        if (type == 2) {
                            text_nick.setText(nickname);
                            info.setNickname(nickname);
                        } else {
                            info.setMobile(nickname);
                        }
                        SaveUtils.saveInfo(info);
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                    case Api.GET_MEID_USER_ID:
                        info = JsonParse.getUserInfo(object);
                        if (info != null) {
                            SaveUtils.saveInfo(info);
                            text_nick.setText(info.getNickname());
                            if (!Utility.isEmpty(info.getUsericon())){
                                GlideUtils.CreateImageCircular(info.getUsericon(), iv_logo, 5);
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


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.rl_nick:
                type = 2;
                showDialog();
                break;
            case R.id.rl_mobile:
                type = 1;
                showDialog();
                break;
            case R.id.rl_password:
                Intent intent = new Intent(this, ForgetActivity.class);
                intent.putExtra("name", "修改密码");
                startActivity(intent);
                break;
            case R.id.rl_icon:
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
                    if (AndPermission.hasAlwaysDeniedPermission(UserActivity.this, permissions)) {
                        showSettingDialog(UserActivity.this, permissions);
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
                .columnCount(4)
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
        Luban.with(this).load(new File(path)).ignoreBy(50)
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
        String sign = "memberid=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("memberid", info.getId());
        params.put("sign", Md5Util.encode(sign));
        OkGo.<String>post(Api.GET_UPLOAD_USER).isMultipart(true).tag(BaseApplication.getContext()).params(params).params("file", file).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        LogUtils.e(jsonObject);
                        result = jsonObject.optString("result");
                        iv_logo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        GlideUtils.CreateImageCircular(result, iv_logo, 5);
                        info.setUsericon(result);
                        SaveUtils.saveInfo(info);
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


    public void showDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_user, null);
        EditText et_name = view.findViewById(R.id.et_name);
        if (type == 2) {
            et_name.setHint("请输入昵称");
            et_name.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            et_name.setHint("请输入新的手机号码");
            et_name.setInputType(InputType.TYPE_CLASS_PHONE | InputType.TYPE_CLASS_NUMBER);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = et_name.getText().toString();
                if (Utility.isEmpty(nickname)) {
                    if (type == 2) {
                        ToastUtil.showToast("昵称不能为空");
                    } else {
                        ToastUtil.showToast("手机号不能为空");
                    }
                    return;
                }
                query();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
