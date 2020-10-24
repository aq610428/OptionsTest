package com.jkabe.app.box.weight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.box.OrderDetileActivity;
import com.jkabe.app.box.box.OrderDetileActivity1;
import com.jkabe.app.box.box.TransactionActivity;
import com.jkabe.app.box.box.fragement.AllFragment;
import com.jkabe.app.box.box.fragement.CompletedFragment;
import com.jkabe.app.box.box.fragement.PayFragment;
import com.jkabe.app.box.ui.ActivationActivity;
import com.jkabe.app.box.ui.BindActivity;
import com.jkabe.app.box.ui.BrandCarActivity;
import com.jkabe.app.box.ui.LoginActivity;
import com.jkabe.app.box.ui.MainActivity;
import com.jkabe.box.R;
import com.jkabe.app.box.ui.LicenseActivity;


public class DialogUtils {


    /******取消订单****/
    public static void showDelete(OrderDetileActivity activity, String msg,String id) {
        Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg);
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
                activity.delete(id);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /******取消订单****/
    public static void showConfirm(CompletedFragment fragment, String msg,String id) {
        Dialog dialog = new Dialog(fragment.getContext());
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg);
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
                fragment.payConfirm(id);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /******去人订单****/
    public static void showConfirm(OrderDetileActivity1 activity, String msg,String id) {
        Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg);
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
                activity.payConfirm(id);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /******取消订单****/
    public static void showConfirm(OrderDetileActivity activity, String msg) {
        Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg);
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
                if (activity.orderBean!=null&&activity.orderBean.getOrderinfo()!=null){
                    activity.payConfirm(activity.orderBean.getOrderinfo().getId());
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /******取消订单****/
    public static void showOrder(OrderDetileActivity activity, String msg) {
        Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg);
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
                if (activity.orderBean!=null&&activity.orderBean.getOrderinfo()!=null){
                    activity.cancelOrder(activity.orderBean.getOrderinfo().getId());
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /******取消订单****/
    public static void showOrder(AllFragment fragment, String msg,String orderId) {
        Dialog dialog = new Dialog(fragment.getContext());
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg);
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
                fragment.cancelOrder(orderId);
                dialog.dismiss();
            }
        });
        dialog.show();
    }





    /******取消订单****/
    public static void showOrder(PayFragment fragment, String msg, String orderId) {
        Dialog dialog = new Dialog(fragment.getContext());
        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg);
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
                fragment.cancelOrder(orderId);
                dialog.dismiss();
            }
        });
        dialog.show();
    }







    public static void showBind(int type, Activity activity) {
        Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        if (type == 1) {
            text_name.setText("您尚未购买卡贝车宝矿机，快去购买吧");
        } else {
            text_name.setText("您尚未激活box,快去激活吧~");
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
                if (type == 1) {
                     if (activity!=null){
                         MainActivity activity1= (MainActivity) activity;
                         if (activity1!=null){
                             activity1.setCurrentTab(1);
                         }
                     }
                } else {
                    activity.startActivity(new Intent(activity, ActivationActivity.class));
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    public static void showPay(Context context) {
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText("您当前的挖矿已结束，是否要再次激活？");
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
                context.startActivity(new Intent( context, ActivationActivity.class));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showPassword(Context context) {
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText("您尚未设置交易密码，快去设置吧~");
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
                context.startActivity(new Intent(context, TransactionActivity.class));
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public static void showPassword1(Activity activity) {
        Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText("您的账户已锁仓");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setVisibility(View.GONE);
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

    public static void showLogin(Context context, String msg) {
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_ming, null);
        TextView text_name = view.findViewById(R.id.text_name);
        text_name.setText(msg + "");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setVisibility(View.GONE);
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.activityTaskManager.closeAllActivityExceptOne("LoginActivity");
                context.startActivity(new Intent(context, LoginActivity.class));
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public static void showDialog(final String phoneNum, Activity activity) {
        final Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_layout1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.title)).setText("温馨提示");
        ((TextView) view.findViewById(R.id.message)).setText("确定退出卡贝车保?");
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
