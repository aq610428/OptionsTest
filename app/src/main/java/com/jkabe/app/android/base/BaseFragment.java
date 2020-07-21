package com.jkabe.app.android.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.jkabe.box.R;
import com.jkabe.app.android.weight.BaseProgressDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by will wu on 2016/10/11.
 */

public abstract class BaseFragment extends Fragment {
    protected boolean isVisible;
    private BaseProgressDialog mProgressDialog = null;
    public static final int REQUEST_CODE_SCAN = 0x0000;
    public static final String DECODED_CONTENT_KEY = "codedContent";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
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
                Toast.makeText(getContext(), R.string.message_setting_comeback, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
