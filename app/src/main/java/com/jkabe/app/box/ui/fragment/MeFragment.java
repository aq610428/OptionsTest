package com.jkabe.app.box.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.box.ChangeActivity;
import com.jkabe.app.box.box.ResetActivity;
import com.jkabe.app.box.box.TransactionActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.ui.AboutActivity;
import com.jkabe.app.box.ui.ActivationActivity;
import com.jkabe.app.box.ui.InvitationActivity;
import com.jkabe.app.box.ui.LoginActivity;
import com.jkabe.app.box.ui.MainActivity;
import com.jkabe.app.box.ui.PreviewActivity;
import com.jkabe.app.box.ui.UserActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.Map;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/7/2
 * @name:我的
 */
public class MeFragment extends BaseFragment implements View.OnClickListener, NetWorkListener {
    private View rootView;
    private TextView text_name, text_edit, text_invitation, text_contacts, text_about, text_out, text_means, text_key, text_password;
    private UserInfo info;
    private ImageView icon_head;
    private TextView text_team, text_Reset, text_change;
    private View tab1, tab2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_me, container, false);
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
        info = SaveUtils.getSaveInfo();
        if (Utility.isEmpty(info.getNickname())) {
            text_name.setText(info.getMobile());
        } else {
            text_name.setText(info.getNickname());
        }
        if (!Utility.isEmpty(info.getUsericon())) {
            GlideUtils.CreateImageCircular(info.getUsericon(), icon_head, 5);
        }
        if ("1".equals(info.getIsPayPassword())) {
            text_password.setVisibility(View.GONE);
            text_Reset.setVisibility(View.VISIBLE);
            text_change.setVisibility(View.VISIBLE);
            tab1.setVisibility(View.VISIBLE);
            tab2.setVisibility(View.VISIBLE);
        }
    }


    private void initView() {
        tab1 = getView(rootView, R.id.tab1);
        tab2 = getView(rootView, R.id.tab2);
        text_change = getView(rootView, R.id.text_change);
        text_Reset = getView(rootView, R.id.text_Reset);
        text_password = getView(rootView, R.id.text_password);
        text_key = getView(rootView, R.id.text_key);
        text_means = getView(rootView, R.id.text_means);
        text_team = getView(rootView, R.id.text_team);
        icon_head = getView(rootView, R.id.icon_head);
        text_name = getView(rootView, R.id.text_name);
        text_edit = getView(rootView, R.id.text_edit);
        text_invitation = getView(rootView, R.id.text_invitation);
        text_contacts = getView(rootView, R.id.text_contacts);
        text_about = getView(rootView, R.id.text_about);
        text_out = getView(rootView, R.id.text_out);
        text_out.setOnClickListener(this);
        text_edit.setOnClickListener(this);
        text_invitation.setOnClickListener(this);
        text_contacts.setOnClickListener(this);
        text_about.setOnClickListener(this);
        text_edit.setOnClickListener(this);
        text_team.setOnClickListener(this);
        text_means.setOnClickListener(this);
        text_key.setOnClickListener(this);
        text_password.setOnClickListener(this);
        text_Reset.setOnClickListener(this);
        text_change.setOnClickListener(this);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.text_change:
                startActivity(new Intent(getContext(), ChangeActivity.class));
                break;
            case R.id.text_password:
                startActivity(new Intent(getContext(), TransactionActivity.class));
                break;
            case R.id.text_Reset:
                startActivity(new Intent(getContext(), ResetActivity.class));
                break;
            case R.id.text_key:
                startActivity(new Intent(getContext(), ActivationActivity.class));
                break;

            case R.id.text_means:
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.setCurrentTab(2);
                }
                break;
            case R.id.text_edit:
                startActivity(new Intent(getContext(), UserActivity.class));
                break;
            case R.id.text_team:
                intent = new Intent(getContext(), PreviewActivity.class);
                intent.putExtra("name", "我的团队");
                intent.putExtra("url", "http://kb.jkabe.com/box/myteam?friendcode=" + SaveUtils.getSaveInfo().getRmcode() + "&memberid=" + SaveUtils.getSaveInfo().getId()+"&apptype="+ Constants.TYPE);
                startActivity(intent);
                break;
            case R.id.text_invitation:
                startActivity(new Intent(getContext(), InvitationActivity.class));
                break;
            case R.id.text_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.text_contacts:
                intent = new Intent(getContext(), PreviewActivity.class);
                intent.putExtra("name", "加入社群");
                intent.putExtra("url", "http://openapi.jkabe.com/golo/about");
                startActivity(intent);
                break;
            case R.id.text_out:
                showDialog();
                break;
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
                SaveUtils.clealCacheDisk();
                PreferenceUtils.setPrefString(getContext(), Constants.TOKEN, "");
                String mobile=PreferenceUtils.getPrefString(getContext(),Constants.MOBILE,"");
                String password=PreferenceUtils.getPrefString(getContext(),Constants.PASSWORD,"");
                final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("north", Context.MODE_PRIVATE);
                PreferenceUtils.clearPreference(getContext(), sharedPreferences);
                PreferenceUtils.setPrefString(getContext(),Constants.MOBILE,mobile);
                PreferenceUtils.setPrefString(getContext(),Constants.PASSWORD,password);
                BaseApplication.activityTaskManager.closeAllActivityExceptOne("LoginActivity");
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void query() {
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
}
