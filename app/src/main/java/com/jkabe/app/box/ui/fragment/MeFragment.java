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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jkabe.app.box.adapter.MeAdapter;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.Block;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.Massage;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.ui.AboutActivity;
import com.jkabe.app.box.ui.ActivationActivity;
import com.jkabe.app.box.ui.AddressActivity;
import com.jkabe.app.box.ui.InvitationActivity;
import com.jkabe.app.box.ui.LoginActivity;
import com.jkabe.app.box.ui.MainActivity;
import com.jkabe.app.box.ui.MsgActivity;
import com.jkabe.app.box.ui.PassworadActivity;
import com.jkabe.app.box.ui.PreviewActivity;
import com.jkabe.app.box.ui.SalesActivity;
import com.jkabe.app.box.ui.UserActivity;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.MarqueeTextView;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.box.R;
import com.lihang.ShadowLayout;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/7/2
 * @name:我的
 */
public class MeFragment extends BaseFragment implements View.OnClickListener, NetWorkListener {
    private View rootView;
    private TextView text_name, text_edit, text_invitation, text_means, text_key, text_team;
    private UserInfo info;
    private ImageView icon_head;
    private RecyclerView recyclerView;
    private List<Block> array = new ArrayList<>();
    private MeAdapter adapter;
    private List<String> items = new ArrayList<>();
    private ShadowLayout rl_note;
    private MarqueeTextView marqueeTextView;


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
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#5476FF"));
        StatusUtil.setSystemStatus(getActivity(), false, false);
        info = SaveUtils.getSaveInfo();
        if (Utility.isEmpty(info.getNickname())) {
            text_name.setText(info.getMobile());
        } else {
            text_name.setText(info.getNickname());
        }
        if (!Utility.isEmpty(info.getUsericon())) {
            GlideUtils.CreateImageCircular(info.getUsericon(), icon_head, 5);
        }
        query();
    }

    private void initView() {
        rl_note = getView(rootView, R.id.rl_note);
        marqueeTextView = getView(rootView, R.id.mENoticeView);
        recyclerView = getView(rootView, R.id.recyclerView);
        text_key = getView(rootView, R.id.text_key);
        text_means = getView(rootView, R.id.text_means);
        text_team = getView(rootView, R.id.text_team);
        icon_head = getView(rootView, R.id.icon_head);
        text_name = getView(rootView, R.id.text_name);
        text_edit = getView(rootView, R.id.text_edit);
        text_invitation = getView(rootView, R.id.text_invitation);
        text_edit.setOnClickListener(this);
        text_invitation.setOnClickListener(this);
        text_edit.setOnClickListener(this);
        text_team.setOnClickListener(this);
        text_means.setOnClickListener(this);
        text_key.setOnClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(manager);
        array = SaveUtils.getArray();
        adapter = new MeAdapter(getContext(), array);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = array.get(position).getName();
                switch (name) {
                    case "交易密码":
                        startActivity(new Intent(getContext(), SalesActivity.class));
                        break;
                    case "流量查询":
                        startActivity(new Intent(getContext(), PassworadActivity.class));
                        break;
                    case "联系我们":
                        Intent intent = new Intent(getContext(), PreviewActivity.class);
                        intent.putExtra("name", "加入社群");
                        intent.putExtra("url", "http://openapi.jkabe.com/golo/about");
                        startActivity(intent);
                        break;
                    case "设备解除":
                        showBindDialog();
                        break;
                    case "关于我们":
                        startActivity(new Intent(getContext(), AboutActivity.class));
                        break;
                    case "消息中心":
                        startActivity(new Intent(getContext(), MsgActivity.class));
                        break;
                    case "退出登录":
                        showDialog();
                        break;
                    case "添加地址":
                        startActivity(new Intent(getContext(), AddressActivity.class));

                        break;
                }
            }
        });


    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
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
                intent.putExtra("url", "http://kb.jkabe.com/box/myteam?friendcode=" + SaveUtils.getSaveInfo().getRmcode() + "&memberid=" + SaveUtils.getSaveInfo().getId() + "&apptype=" + Constants.TYPE);
                startActivity(intent);
                break;
            case R.id.text_invitation:
                startActivity(new Intent(getContext(), InvitationActivity.class));
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
                String mobile = PreferenceUtils.getPrefString(getContext(), Constants.MOBILE, "");
                String password = PreferenceUtils.getPrefString(getContext(), Constants.PASSWORD, "");
                final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("north", Context.MODE_PRIVATE);
                PreferenceUtils.clearPreference(getContext(), sharedPreferences);
                PreferenceUtils.setPrefString(getContext(), Constants.MOBILE, mobile);
                PreferenceUtils.setPrefString(getContext(), Constants.PASSWORD, password);
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


    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&type=2" + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("type", "2");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MOBILCODE_NOTE, params, Api.GET_UPDATE_ID, this);
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
                    case Api.GET_UPDATE_ID:
                        Massage massage = JsonParse.getEarlyInfoJson2(object);
                        updateView(massage);
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    /********公告******/
    private void updateView(Massage massage) {
        if (massage != null ) {
            rl_note.setVisibility(View.VISIBLE);
            items.add(massage.getTitle()+":"+massage.getRemark());
            marqueeTextView.setText(massage.getTitle()+":"+massage.getRemark());
            marqueeTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(),MsgActivity.class);
                    intent.putExtra("index",2);
                    startActivity(intent);
                }
            });
        } else {
            rl_note.setVisibility(View.GONE);
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
