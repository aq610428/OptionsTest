package com.jkabe.app.box.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jkabe.app.box.ui.BindActivity;
import com.jkabe.app.box.ui.BrandCarActivity;
import com.jkabe.box.R;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.ui.AboutActivity;
import com.jkabe.app.box.ui.InvitationActivity;
import com.jkabe.app.box.ui.LoginActivity;
import com.jkabe.app.box.ui.PreviewActivity;
import com.jkabe.app.box.ui.UserActivity;
import com.jkabe.app.box.ui.VehicleActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.LogUtils;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.app.box.weight.RuntimeRationale;
import com.jkabe.app.box.zxing.android.CaptureActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.json.JSONObject;

import java.util.Map;

import crossoverone.statuslib.StatusUtil;

import static android.app.Activity.RESULT_OK;

/**
 * @author: zt
 * @date: 2020/7/2
 * @name:我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener, NetWorkListener {
    private View rootView;
    private TextView text_name, text_edit, text_invitation, text_code, text_contacts, text_bind, text_car, text_about, text_out;
    private UserInfo info;
    private ImageView icon_head;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);
            initView();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
        info = SaveUtils.getSaveInfo();
        if (Utility.isEmpty(info.getNickname())) {
            text_name.setText(info.getMobile());
        } else {
            text_name.setText(info.getNickname());
        }
        GlideUtils.CreateImageCircular(info.getUsericon(), icon_head, 5);
    }


    private void initView() {
        icon_head = getView(rootView, R.id.icon_head);
        text_name = getView(rootView, R.id.text_name);
        text_edit = getView(rootView, R.id.text_edit);
        text_invitation = getView(rootView, R.id.text_invitation);
        text_code = getView(rootView, R.id.text_code);
        text_contacts = getView(rootView, R.id.text_contacts);
        text_bind = getView(rootView, R.id.text_bind);
        text_car = getView(rootView, R.id.text_car);
        text_about = getView(rootView, R.id.text_about);
        text_out = getView(rootView, R.id.text_out);
        text_out.setOnClickListener(this);
        text_edit.setOnClickListener(this);
        text_car.setOnClickListener(this);
        text_invitation.setOnClickListener(this);
        text_code.setOnClickListener(this);
        text_contacts.setOnClickListener(this);
        text_about.setOnClickListener(this);
        text_edit.setOnClickListener(this);
        text_bind.setOnClickListener(this);

        CarInfo carInfo = SaveUtils.getCar();
        if (carInfo != null) {
            text_bind.setVisibility(View.VISIBLE);
        } else {
            text_bind.setVisibility(View.GONE);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_edit:
                startActivity(new Intent(getContext(), UserActivity.class));
                break;
            case R.id.text_car:
                if (SaveUtils.getCar() != null && !Utility.isEmpty(SaveUtils.getCar().getSimcode())) {
                    startActivity(new Intent(getContext(), VehicleActivity.class));
                } else {
                    startActivity(new Intent(getContext(), BindActivity.class));
                }
                break;

            case R.id.text_bind:
                if (SaveUtils.getCar() != null && !Utility.isEmpty(SaveUtils.getCar().getSimcode())){
                    showBindDialog();
                }else{
                    ToastUtil.showToast("您未绑定设备，快去绑定吧~");
                }

                break;

            case R.id.text_invitation:
                startActivity(new Intent(getContext(), InvitationActivity.class));
                break;
            case R.id.text_code:
                checkPermission();
                break;
            case R.id.text_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.text_contacts:
                Intent intent = new Intent(getContext(), PreviewActivity.class);
                intent.putExtra("name", "加入社群");
                intent.putExtra("url", "http://openapi.jkabe.com/golo/about");
                startActivity(intent);
                break;
            case R.id.text_out:
                showDialog();
                break;
        }
    }

    private void checkPermission() {
        AndPermission.with(this).runtime().permission(Permission.CAMERA)
                .rationale(new RuntimeRationale())
                .onGranted(permissions -> {
                    Intent intent = new Intent(getContext(), CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                })
                .onDenied(permissions -> {
                    if (AndPermission.hasAlwaysDeniedPermission(getContext(), permissions)) {
                        showSettingDialog(getContext(), permissions);
                    }
                })
                .start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                LogUtils.e("解码结果： \n" + content);
            }
        }
    }


    public void showDialog() {
        Dialog dialog = new Dialog(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout_mine, null);

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
                PreferenceUtils.setPrefString(getContext(), Constants.TOKEN, "");
                SaveUtils.clealCacheDisk();
                final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("north", Context.MODE_PRIVATE);
                PreferenceUtils.clearPreference(getContext(), sharedPreferences);
                BaseApplication.activityTaskManager.closeAllActivityExceptOne("LoginActivity");
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void showBindDialog() {
        Dialog dialog = new Dialog(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout_bind, null);
        EditText et_code = view.findViewById(R.id.et_code);
        TextView btn_code = view.findViewById(R.id.btn_code);
        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryCode();
                countDown(btn_code);
            }
        });

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
                String code = et_code.getText().toString();
                if (Utility.isEmpty(code)) {
                    ToastUtil.showToast("验证码不能为空");
                    return;
                }
                bind(code);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void bind(String code) {
        String sign = "id=" + SaveUtils.getCar().getId() + "&imeicode=" + SaveUtils.getCar().getImeicode() + "&loginname=" +
                SaveUtils.getSaveInfo().getLoginname()
                + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&vcode=" + code + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("id", SaveUtils.getCar().getId() + "");
        params.put("imeicode", SaveUtils.getCar().getImeicode() + "");
        params.put("loginname", SaveUtils.getSaveInfo().getLoginname() + "");
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("vcode", code + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_UNMODEL_BIND, params, Api.GET_UNMODEL_BIND_ID, this);
    }

    public void queryCode() {
        String sign = "mobile=" + SaveUtils.getSaveInfo().getMobile() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("mobile", SaveUtils.getSaveInfo().getMobile());
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MOBILCODE, params, Api.GET_MOBILCODE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_UNMODEL_BIND_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        SaveUtils.saveCar(null);
                        break;
                    case Api.GET_MOBILCODE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
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

    CountDownTimer timer;

    private void countDown(TextView btn_code) {
        timer = new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_code.setEnabled(false);
                btn_code.setText("已发送(" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                btn_code.setEnabled(true);
                btn_code.setText("重新获取验证码");

            }
        }.start();
    }

}
