package com.jkabe.app.box.adapter;

import android.view.View;
import com.jkabe.app.box.bean.ActiveBean;
import com.jkabe.app.box.ui.ActiveActivity1;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/27
 * @name:AddressAdapter
 */
public class ActiiveAdapter1 extends AutoRVAdapter {
    List<ActiveBean> list;
    ActiveActivity1 activity1;

    public ActiiveAdapter1(ActiveActivity1 activity1, List<ActiveBean> list) {
        super(activity1, list);
        this.list = list;
        this.activity1 = activity1;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_active1;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ActiveBean addressBean = list.get(position);
       vh.getTextView(R.id.text_exchange).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               activity1.query();
           }
       });
    }

}
