package com.jkabe.app.box.adapter;

import android.content.Intent;
import android.view.View;
import com.jkabe.box.R;
import com.jkabe.app.box.bean.Travrt;
import com.jkabe.app.box.ui.LocationTravelActivity;
import com.jkabe.app.box.ui.TravelActivity;
import com.jkabe.app.box.util.SystemTools;
import com.jkabe.app.box.util.Utility;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class TraverAdapter extends AutoRVAdapter {
    private List<Travrt> inventories = new ArrayList<>();
    private TravelActivity activity;

    public TraverAdapter(TravelActivity activity, List<Travrt> inventories) {
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
        return R.layout.item_traver;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Travrt travrt = inventories.get(position);
        String start = travrt.getStarttime();
        String end = travrt.getEndtime();
        if (!Utility.isEmpty(start) && !Utility.isEmpty(end)) {
            String begin = start.substring(start.length() - 8, start.length());
            String end1 = end.substring(end.length() - 8, end.length());
            vh.getTextView(R.id.text_date).setText(begin + "至" + end1);
        }
        int prss=SystemTools.getPress();
        vh.getTextView(R.id.text_press).setText(prss + "分");
        vh.getTextView(R.id.text_press_chat).setText("击败"+prss+"%的车友");
        vh.getTextView(R.id.text_start_address).setText(travrt.getStartaddress());
        vh.getTextView(R.id.text_end_address).setText(travrt.getEndaddress());
        String one = SystemTools.mathKmOne(Integer.valueOf(travrt.getTripmileage())) +"KM";//"KM\n里程"
        String two = SystemTools.mathKmTwo(Integer.valueOf(travrt.getTripoil()))+"L"  ;//+ "L\n油耗"
        vh.getTextView(R.id.text_l).setText(two);
        vh.getTextView(R.id.text_hour).setText(SystemTools.mathMinute(Integer.valueOf(travrt.getIdletime())));
        vh.getTextView(R.id.text_km).setText(one);
        vh.getTextView(R.id.text_tab1).setText(travrt.getAccecount() + "次");
        vh.getTextView(R.id.text_tab2).setText(travrt.getDececount() + "次");
        vh.getTextView(R.id.text_tab3).setText(travrt.getSharpturncount() + "次");
        String four = SystemTools.mathKmOne(Integer.valueOf(travrt.getTripmileage()) * 3600 / Integer.valueOf(travrt.getTriptime())) + "KM/H";
        vh.getTextView(R.id.text_tab4).setText(four + "");

        vh.getRelativeLayout(R.id.rl_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LocationTravelActivity.class);
                intent.putExtra("travrt", travrt);
                activity.startActivity(intent);
            }
        });

    }


    public void setData(List<Travrt> travrts) {
        this.inventories = travrts;
    }
}
