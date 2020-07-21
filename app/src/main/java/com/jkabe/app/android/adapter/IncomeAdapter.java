package com.jkabe.app.android.adapter;

import android.content.Context;
import android.view.View;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class IncomeAdapter extends AutoRVAdapter{
    private List<String> list;

    public IncomeAdapter(Context context, List<?> list) {
        super(context, list);
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_income;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        if (position==0){
            vh.getTextView(R.id.text_date).setVisibility(View.VISIBLE);
        }else{
            vh.getTextView(R.id.text_date).setVisibility(View.GONE);
        }
    }
}
