/**
 * 项目名称: CoreLibrary
 * 文件名称: BaseActivity.java
 * 创建时间: 2014/3/28
 * Copyright: 2014 www.fantasee.cn Inc. All rights reserved.
 */
package com.jkabe.app.box.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jkabe.box.R;
import com.jkabe.app.box.weight.BaseProgressDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

import crossoverone.statuslib.StatusUtil;


/**
 * 所有页面activity的抽象基类D
 *
 * @author zt </br> Create at 2018年2月19日 下午5:31:35
 * @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
    private BaseProgressDialog mProgressDialog = null;
    public static final int REQUEST_CODE_SCAN = 0x0000;
    public static final String DECODED_CONTENT_KEY = "codedContent";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCreate(savedInstanceState);
        //基类设置默认值,这里是非沉浸,状态栏颜色值#878787,字体颜色为黑色。
        setStatusColor();
        setSystemInvadeBlack();
        initView();
        initData();
    }

    protected void setStatusColor() {
        StatusUtil.setUseStatusBarColor(this, Color.parseColor("#FFFFFF"));
    }

    protected void setSystemInvadeBlack() {
        // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtil.setSystemStatus(this, false, true);
    }


    protected abstract void initCreate(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void showProgressDialog(Activity activity, BaseProgressDialog.OnCancelListener cancelListener, boolean cancelable, String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        mProgressDialog = new BaseProgressDialog(activity, msg);
        if (cancelListener != null) {
            mProgressDialog.setOnCancelListener(cancelListener);
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.show();
    }

    public void showProgressDialog(Activity activity, boolean cancelable, String msg) {
        showProgressDialog(activity, null, cancelable, msg);
    }

    public void showProgressDialog(Activity activity, boolean cancelable) {
        showProgressDialog(activity, cancelable, "");
    }


    public void stopProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.stop();
        }
        mProgressDialog = null;
    }


    /***
     * 控件获取
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int resId) {
        return (T) this.findViewById(resId);
    }

    /***
     * 控件获取
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    public void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed);
        new AlertDialog.Builder(context).setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }


    /**
     * Set permissions.
     */
    private static final int REQUEST_CODE_SETTING = 1;
    public void setPermission() {
        AndPermission.with(this).runtime().setting().start(REQUEST_CODE_SETTING);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SETTING: {
                Toast.makeText(this, R.string.message_setting_comeback, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

}
