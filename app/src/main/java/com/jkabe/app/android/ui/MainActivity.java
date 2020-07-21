package com.jkabe.app.android.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.fragment.app.FragmentTabHost;
import com.jkabe.box.R;
import com.jkabe.app.android.base.BaseActivity1;
import com.jkabe.app.android.base.BaseApplication;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.config.Api;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.app.android.config.okHttpModel;
import com.jkabe.app.android.ui.fragment.CarFragment;
import com.jkabe.app.android.ui.fragment.CarLeftFragment;
import com.jkabe.app.android.ui.fragment.MineFragment;
import com.jkabe.app.android.ui.fragment.RecordFragment;
import com.jkabe.app.android.util.Constants;
import com.jkabe.app.android.util.Md5Util;
import com.jkabe.app.android.util.SaveUtils;
import com.jkabe.app.android.util.ToastUtil;
import com.jkabe.app.android.util.Utility;
import org.json.JSONObject;
import java.util.Map;
import cn.jpush.android.api.JPushInterface;

/*****
 *
 * 首页
 *
 */
public class MainActivity extends BaseActivity1 implements NetWorkListener {
    private Class fragments[] = {CarFragment.class, CarLeftFragment.class, RecordFragment.class, MineFragment.class};
    private int drawables[] = {R.drawable.book_drawable, R.drawable.chosen_drawable,  R.drawable.shelf_drawable,R.drawable.me_drawable};
    private String textviewArray[] = {"我的车", "车生活", "资产", "我的"};
    public FragmentTabHost mTabHost;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        BaseApplication.activityTaskManager.putActivity("MainActivity", this);
    }

    @Override
    protected void initView() {
        mTabHost = getView(R.id.mTabHost);
        queryPush();
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
                    tv_home.setTextColor(Color.parseColor("#3E80F3"));
                }
                if (tabId.equals(textviewArray[1])) {
                    clearViewColor();
                    TextView tv_order = mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.textview);
                    tv_order.setTextColor(Color.parseColor("#3E80F3"));
                }

                if (tabId.equals(textviewArray[2])) {
                    clearViewColor();
                    TextView tv_mine = mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.textview);
                    tv_mine.setTextColor(Color.parseColor("#3E80F3"));
                }

                if (tabId.equals(textviewArray[3])) {
                    clearViewColor();
                    TextView tv_mine = mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.textview);
                    tv_mine.setTextColor(Color.parseColor("#3E80F3"));
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
        tv_order.setTextColor(Color.parseColor("#3E80F3"));
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
        params.put("memberid",  SaveUtils.getSaveInfo().getId() + "");
        params.put("registerid", registrationID + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_PUSH_VERSION, params, Api.GET_PUSH_VERSION_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_PUSH_VERSION_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        break;

                }
            }else{
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
