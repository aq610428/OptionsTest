package com.jkabe.app.android.adapter;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jkabe.box.R;
import com.jkabe.app.android.bean.HealthProjectVO;
import com.jkabe.app.android.ui.MedicalActivity;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/16
 * @name:HealthAdapter
 */
public class HealthAdapter extends AutoRVAdapter {
    private List<HealthProjectVO> list;
    private MedicalActivity activity;

    public HealthAdapter(MedicalActivity activity, List<HealthProjectVO> list) {
        super(activity, list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_health;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        HealthProjectVO vo = list.get(position);
        vh.getTextView(R.id.title_text_tv).setText(vo.getName());
        TextView text_success = vh.getTextView(R.id.text_success);
        ProgressBar progressBar = vh.getProgressBar(R.id.mProgressBar);
        if (vo.getStats() == 0) {
            text_success.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } else if (vo.getStats() == 1) {
            text_success.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            text_success.setVisibility(View.VISIBLE);
            if ("正常".equals(vo.getValue())) {
                text_success.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_success, 0, 0, 0);
                text_success.setText("正常");
                vh.getTextView(R.id.text_abnormal).setVisibility(View.GONE);
            } else {
                text_success.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_fail, 0, 0, 0);
                text_success.setText("异常");
                vh.getTextView(R.id.text_abnormal).setVisibility(View.VISIBLE);
                vh.getTextView(R.id.text_abnormal).setText(vo.getValue());
            }

        }
    }

    public void startLaunch(int index) {
        list.get(index).setStats(1);
        if (index > 0) {
            list.get(index - 1).setStats(2);
        }
        notifyDataSetChanged();
    }

    public void endLaunch() {
        list.get(list.size() - 2).setStats(2);
        list.get(list.size() - 1).setStats(2);
        notifyDataSetChanged();
    }
}
