package com.jkabe.app.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jkabe.app.android.box.AssetsActivity;
import com.jkabe.app.android.ui.fragment.AssetsFragmnt;
import com.jkabe.box.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:AssetsAdapter
 */
public class AssetsAdapter extends AutoRVAdapter {
    private AssetsFragmnt assetsFragmnt;
    private List<String> list = new ArrayList<>();

    public AssetsAdapter(AssetsFragmnt assetsFragmnt, List<String> list) {
        super(assetsFragmnt.getContext(), list);
        this.assetsFragmnt = assetsFragmnt;
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_assets_main;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.getTextView(R.id.text_num_bc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(assetsFragmnt.getContext(), AssetsActivity.class);
                assetsFragmnt.startActivity(intent);
            }
        });
    }
}
