package com.jkabe.app.box.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import com.jkabe.box.R;
import com.jkabe.app.box.bean.OrderInfo;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.Utility;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/10
 * @name:ReceivedAdapter
 */
public class ReceivedAdapter extends AutoRVAdapter {
    private List<OrderInfo> orderInfos;
    private Context context;

    public ReceivedAdapter(Context context, List<OrderInfo> list) {
        super(context, list);
        this.orderInfos = list;
        this.context=context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_received;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OrderInfo orderInfo = orderInfos.get(position);
        vh.getTextView(R.id.noticeText).setText(orderInfo.getStoreName());
        vh.getTextView(R.id.text_name).setText(orderInfo.getStoreAddress());
        vh.getTextView(R.id.text_mobile).setText(orderInfo.getMobile());
        vh.getTextView(R.id.text_prect).setText(orderInfo.getProject());
        GlideUtils.setImageUrl(orderInfo.getStoreLogo(), vh.getImageView(R.id.iv_photo));
        //0=取消预约,1=预约申请中,2=接受预约,3=预约完成
        int status = orderInfo.getStatus();
        if (status == 1) {
            vh.getTextView(R.id.text_repair).setText("预约申请中");
            vh.getTextView(R.id.text_repair).setTextColor(Color.parseColor("#39CCC0"));
            vh.getTextView(R.id.text_cancel).setVisibility(View.VISIBLE);
        } else if (status == 2) {
            vh.getTextView(R.id.text_repair).setText("进行中");
            vh.getTextView(R.id.text_repair).setTextColor(Color.parseColor("#FFB300"));
            vh.getTextView(R.id.text_cancel).setVisibility(View.GONE);
        } else if (status == 3) {
            vh.getTextView(R.id.text_repair).setText("预约完成");
            vh.getTextView(R.id.text_repair).setTextColor(Color.parseColor("#3F69F4"));
            vh.getTextView(R.id.text_cancel).setVisibility(View.GONE);
        } else {
            vh.getTextView(R.id.text_repair).setText("取消预约");
            vh.getTextView(R.id.text_repair).setTextColor(Color.parseColor("#FF4747"));
            vh.getTextView(R.id.text_cancel).setVisibility(View.GONE);
        }
        if (!Utility.isEmpty(orderInfo.getOrderTime()) && orderInfo.getOrderTime().length() > 5) {
            String end = orderInfo.getOrderTime().substring(0, orderInfo.getOrderTime().length() - 5);
            String start = orderInfo.getOrderTime().substring(orderInfo.getOrderTime().length() - 5, orderInfo.getOrderTime().length());
            vh.getTextView(R.id.text_date).setText(end + "  " + start);
        }

        vh.getTextView(R.id.text_mobile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemTools.callPhone(context, orderInfo.getMobile());
            }
        });

    }

    public void setData(List<OrderInfo> list) {
        this.orderInfos = list;
    }
}
