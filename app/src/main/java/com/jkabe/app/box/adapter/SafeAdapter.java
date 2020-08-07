package com.jkabe.app.box.adapter;

import android.view.View;
import com.jkabe.box.R;
import com.jkabe.app.box.bean.CarSafeVO;
import com.jkabe.app.box.ui.SafeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class SafeAdapter extends AutoRVAdapter {
    private List<CarSafeVO> inventories = new ArrayList<>();
    private SafeActivity activity;

    public SafeAdapter(SafeActivity activity, List<CarSafeVO> inventories) {
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
        return R.layout.adapter_car_safe_view;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        CarSafeVO inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getTitle());
        vh.getImageView(R.id.text_cheack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inventory.getValue()==0){
                    inventory.setValue(1);
                }else{
                    inventory.setValue(0);
                }
                activity.bind(inventory);
            }
        });

        if (inventory.getValue()==0) {
            vh.getImageView(R.id.text_cheack).setImageResource(R.mipmap.ic_togglebutton_toggle_close);
        }else{
            vh.getImageView(R.id.text_cheack).setImageResource(R.mipmap.ic_togglebutton_toggle_open);
        }


    }


}
