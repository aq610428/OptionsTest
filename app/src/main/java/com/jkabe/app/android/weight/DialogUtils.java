package com.jkabe.app.android.weight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.android.ui.LicenseActivity;


public class DialogUtils {

    public static void showDialog(final String phoneNum, Activity activity) {
        final Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.title)).setText("温馨提示");
        ((TextView) view.findViewById(R.id.message)).setText("确定退出惠保养?");
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    public static void showTipDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.title)).setText("温馨提示");
        ((TextView) view.findViewById(R.id.message)).setText(msg);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LicenseActivity.class);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
