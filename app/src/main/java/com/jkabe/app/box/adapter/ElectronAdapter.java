package com.jkabe.app.box.adapter;

import com.jkabe.box.R;
import com.jkabe.app.box.bean.Electronic;
import com.jkabe.app.box.ui.ElectronicActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class ElectronAdapter extends AutoRVAdapter {
    private List<Electronic> inventories = new ArrayList<>();
    private ElectronicActivity activity;

    public ElectronAdapter(ElectronicActivity activity, List<Electronic> inventories) {
        super(activity, inventories);
        this.activity = activity;
        this.inventories = inventories;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_electron;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Electronic electronic = inventories.get(position);
        vh.getTextView(R.id.text_name).setText("名称:" + electronic.getName());
        vh.getTextView(R.id.text_dis).setText("半径" + electronic.getRadius() + "米");
        vh.getTextView(R.id.text_address).setText(electronic.getAddress() + "");

        int alarmstate = electronic.getAlarmstate();
        switch (alarmstate){
            case 0:
                vh.getTextView(R.id.text_stats).setText("进出围栏警报已关闭");
                break;
            case 1:
                vh.getTextView(R.id.text_stats).setText("进围栏警报已开启");
                break;
            case 2:
                vh.getTextView(R.id.text_stats).setText("出围栏警报已开启");
                break;
            case 3:
                vh.getTextView(R.id.text_stats).setText("进出围栏警报已开启");
                break;

        }

    }


    public void setData(List<Electronic> electronics) {
        this.inventories = electronics;
    }
}
