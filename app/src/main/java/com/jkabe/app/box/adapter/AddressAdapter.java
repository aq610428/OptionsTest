package com.jkabe.app.box.adapter;

import android.content.Context;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class AddressAdapter extends AutoRVAdapter {
    List<String> list;

    public AddressAdapter(Context context, List<String> list) {
        super(context, list);
        this.list=list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_address_info;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
