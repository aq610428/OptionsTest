package com.jkabe.app.box.adapter;

import android.content.Context;

import com.jkabe.app.box.bean.Massage;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class CidAdapter extends AutoRVAdapter {
    private List<String> list;

    public CidAdapter(Context context, List<String> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_word;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {

    }

    public void setData(List<String> data) {
        this.list = data;
    }
}
