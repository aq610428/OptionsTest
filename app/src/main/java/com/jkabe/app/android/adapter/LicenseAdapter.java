package com.jkabe.app.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jkabe.box.R;
import com.jkabe.app.android.bean.CarRulesItemVO;
import com.jkabe.app.android.ui.DrivingLicenseActivity;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/16
 * @name:LicenseAdapter
 */
public class LicenseAdapter extends BaseAdapter {
    private DrivingLicenseActivity activity;
    private List<CarRulesItemVO> infos;


    public LicenseAdapter(DrivingLicenseActivity activity, List<CarRulesItemVO> infos) {
        this.activity = activity;
        this.infos = infos;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (null == convertView) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.car_with_rule_view, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        CarRulesItemVO vo =  infos.get(position);
        String score = vo.getVio_score();
        String fine = vo.getVio_fine();
        String tag =  gotoTag(vo.getHandle_sign());
        vh.addressTv.setText(vo.getVio_city_name() + "市" + vo.getVio_address());
        vh.ruleTv.setText(vo.getVio_action());
        vh.fak.setText("¥" + fine);
        vh.kouf.setText(score);
        vh.stateTv.setText(tag);
        String start = vo.getVio_time().substring(0, vo.getVio_time().length() - 8);
        String end = vo.getVio_time().substring(vo.getVio_time().length() - 8, vo.getVio_time().length());
        vh.searchTime.setText(start + " " + end);
        return convertView;
    }

    private String gotoTag(String tag){
        if(tag.equals("1"))
            return "未处理 未缴费";
        else if(tag.equals("2"))
            return  "已处理 未缴费";
        else
            return "已处理 已缴费";
    }


    static class ViewHolder {
        TextView addressTv;
        TextView ruleTv;
        TextView fak;
        TextView kouf;
        TextView stateTv;
        TextView searchTime;
        public ViewHolder(View v) {
            addressTv = v.findViewById(R.id.car_with_rule_address_id);
            ruleTv = v.findViewById(R.id.car_with_rule_tag_id);
            fak = v.findViewById(R.id.fak_id);
            kouf = v.findViewById(R.id.kouf_id);
            searchTime = v.findViewById(R.id.rule_time_id);
            stateTv = v.findViewById(R.id.rule_state_id);
        }
    }



    public void setData(List<CarRulesItemVO> infos) {
        this.infos = infos;
    }
}
