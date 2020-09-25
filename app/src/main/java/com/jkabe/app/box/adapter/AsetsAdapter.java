package com.jkabe.app.box.adapter;

import android.content.Context;
import com.jkabe.app.box.bean.AssetsBean;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/9
 * @name:MinIngAdapter
 */
public class AsetsAdapter extends AutoRVAdapter {
    private List<AssetsBean> list;

    public AsetsAdapter(Context context, List<AssetsBean> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_asets;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        AssetsBean assetsBean = list.get(position);

        switch (assetsBean.getState()) {
            case 1:
                vh.getTextView(R.id.text_num).setText("邀请激活奖励");
                break;
            case 2:
                vh.getTextView(R.id.text_num).setText("参与挖矿奖励");
                break;
            case 3:
                vh.getTextView(R.id.text_num).setText("团队奖励");
                break;
            case 4:
                if ("1".equals(assetsBean.getCoinTypeId())) {
                    vh.getTextView(R.id.text_num).setText("充值USDT");
                }else{
                    vh.getTextView(R.id.text_num).setText("充值BOX");
                }

                break;
            case 5:
                if ("1".equals(assetsBean.getCoinTypeId())) {
                    vh.getTextView(R.id.text_num).setText("提现USDT");
                }else{
                    vh.getTextView(R.id.text_num).setText("提现BOX");
                }
                break;
            case 6:
                vh.getTextView(R.id.text_num).setText("提激活挖矿扣除");
                break;
        }

        vh.getTextView(R.id.text_user).setText(assetsBean.getBalance() + "");
        String time = assetsBean.getStringCreateTime();
        if (!Utility.isEmpty(time)) {
            vh.getTextView(R.id.text_usd).setText(time.substring(0, 10) + "");
        }
        switch (assetsBean.getStatus()) {
            case 1:
                vh.getTextView(R.id.text_congeal).setText("待审核");
                break;
            case 2:
                vh.getTextView(R.id.text_congeal).setText("交易中");
                break;
            case 3:
                vh.getTextView(R.id.text_congeal).setText("交易成功");
                break;
            case 4:
                vh.getTextView(R.id.text_congeal).setText("交易失败");
                break;
        }

    }

    public void setData(List<AssetsBean> data) {
        this.list = data;
    }
}
