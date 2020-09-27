package com.jkabe.app.box.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.fragment.app.FragmentTabHost;
import com.jkabe.app.box.base.BaseActivity1;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.app.box.bean.Verison;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.ui.fragment.CarFragment;
import com.jkabe.app.box.ui.fragment.LeftFragment;
import com.jkabe.app.box.ui.fragment.MeFragment;
import com.jkabe.app.box.ui.fragment.OreFragment;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.app.box.weight.DialogUtils;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.app.box.weight.RuntimeRationale;
import com.jkabe.app.box.weight.UpdateManager;
import com.jkabe.box.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import cn.jpush.android.api.JPushInterface;

/*****
 *
 * 首页
 *
 */
public class MainActivity extends BaseActivity1 implements NetWorkListener {
    //    private Class fragments[] = {CarFragment.class, CarLeftFragment.class, RecordFragment.class, MineFragment.class};
    private Class fragments[] = {CarFragment.class, LeftFragment.class, OreFragment.class, MeFragment.class};
    private int drawables[] = {R.drawable.book_drawable, R.drawable.shelf_drawable, R.drawable.chosen_drawable, R.drawable.me_drawable};
    private String textviewArray[] = {"我的车", "车生活", "矿池", "我的"};
    public FragmentTabHost mTabHost;
    private Verison verison;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        BaseApplication.activityTaskManager.putActivity("MainActivity", this);
    }

    @Override
    protected void initView() {
        mTabHost = getView(R.id.mTabHost);
        queryPush();
        query();
        queryUser();
    }

    @Override
    protected void initData() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.mFrameLayout);
        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textviewArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragments[i], null);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.colorAccent);
        }
        TabHost.OnTabChangeListener l = tabId -> {
            try {
                if (tabId.equals(textviewArray[0])) {
                    clearViewColor();
                    TextView tv_home = mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.textview);
                    tv_home.setTextColor(Color.parseColor("#3F69F4"));
                }
                if (tabId.equals(textviewArray[1])) {
                    clearViewColor();
                    TextView tv_order = mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.textview);
                    tv_order.setTextColor(Color.parseColor("#3F69F4"));
                }

                if (tabId.equals(textviewArray[2])) {
                    clearViewColor();
                    TextView tv_mine = mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.textview);
                    tv_mine.setTextColor(Color.parseColor("#3F69F4"));
                }

                if (tabId.equals(textviewArray[3])) {
                    clearViewColor();
                    TextView tv_mine = mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.textview);
                    tv_mine.setTextColor(Color.parseColor("#3F69F4"));
                }

            } catch (Exception e) {
            }
        };
        setCurrentTab(0);
        mTabHost.setOnTabChangedListener(l);
        mTabHost.getTabWidget().setDividerDrawable(null);
    }


    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
        ImageView imageView = view.findViewById(R.id.imageview);
        imageView.setImageResource(drawables[index]);
        TextView textView = view.findViewById(R.id.textview);
        textView.setText(textviewArray[index]);
        return view;
    }


    public void setCurrentTab(int index) {
        mTabHost.setCurrentTab(index);
        clearViewColor();
        TextView tv_order = mTabHost.getTabWidget().getChildAt(index).findViewById(R.id.textview);
        tv_order.setTextColor(Color.parseColor("#3F69F4"));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OnEventExit();
    }
    /**
     * 退出应用
     */
    public void OnEventExit() {
        try {
            BaseApplication.activityTaskManager.closeAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
            moveTaskToBack(false);
        } catch (Exception e) {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }


    /******
     * 清除所有的颜色
     */
    public void clearViewColor() {
        TextView tv_home = mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.textview);
        tv_home.setTextColor(Color.parseColor("#666666"));
        TextView tv_order = mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.textview);
        tv_order.setTextColor(Color.parseColor("#666666"));
        TextView tv_mine2 = mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.textview);
        tv_mine2.setTextColor(Color.parseColor("#666666"));

        TextView tv_mine3 = mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.textview);
        tv_mine3.setTextColor(Color.parseColor("#666666"));
    }

    /*******绑定Jpush
     * @param ********/
    public void queryPush() {
        String registrationID = JPushInterface.getRegistrationID(this);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&registerid=" + registrationID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("memberid", SaveUtils.getSaveInfo().getId() + "");
        params.put("registerid", registrationID + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_PUSH_VERSION, params, Api.GET_PUSH_VERSION_ID, this);
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



    /******查询个人资料*****/
    public void queryUser() {
        showProgressDialog(this, false);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MEID_USER, params, Api.GET_MEID_USER_ID, this);
    }


    /******异地登录*****/
    public void queryToken() {
        showProgressDialog(this, false);
        String token=PreferenceUtils.getPrefString(this,Constants.TOKEN,"");
        String sign = "loginname=" + SaveUtils.getSaveInfo().getLoginname()+ "&partnerid=" + Constants.PARTNERID + "&token=" + token  + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("loginname", SaveUtils.getSaveInfo().getLoginname());
        params.put("token", token);
        params.put("partnerid", Constants.PARTNERID);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.RESET_TOKEN, params, Api.RESET_TOKEN_ID, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        queryToken();
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_PUSH_VERSION_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;
                    case Api.GET_INTERGRAL_VERSION_ID:
                        verison = JsonParse.getVerisonUserInfo(object);
                        if (verison != null) {
                            int code = SystemTools.getAppVersionCode(this);
                            if (!Utility.isEmpty(verison.getVersionIndex())) {
                                int versionCode = Integer.parseInt(verison.getVersionIndex());
                                if (versionCode > code) {
                                    applyPermission();
                                }
                            }

                        }
                        break;
                    case Api.GET_MEID_USER_ID:
                        UserInfo info = JsonParse.getUserInfo(object);
                        if (info != null) {
                            SaveUtils.saveInfo(info);
                        }
                        break;

                }
            } else {
                if (id==Api.RESET_TOKEN_ID){
                    DialogUtils.showLogin(this,commonality.getErrorDesc());
                }else{
                    ToastUtil.showToast(commonality.getErrorDesc());
                }

                }

        }
        stopProgressDialog();
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
                        if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, permissions)) {
                            showSettingDialog(MainActivity.this, permissions);
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
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }
}
