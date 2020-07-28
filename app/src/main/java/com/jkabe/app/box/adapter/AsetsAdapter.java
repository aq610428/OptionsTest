package com.jkabe.app.box.adapter;

import android.content.Context;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class AsetsAdapter extends AutoRVAdapter {
    private List<String> list;

    public AsetsAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_asets;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {

    }
}
