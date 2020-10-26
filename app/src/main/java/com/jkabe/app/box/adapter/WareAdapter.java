package com.jkabe.app.box.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jkabe.app.box.bean.GoodBean;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.util.MeasureWidthUtils;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.box.R;

import java.util.List;

import static com.jkabe.app.box.util.Constants.scale;


/**
 * @author: zt
 * @date: 2020/9/15
 * @name:IndexListAdapter
 */
public class WareAdapter extends AutoRVAdapter {
    List<GoodBean> list;
    private Context context;

    public WareAdapter(Context context, List<GoodBean> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_list_index;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        GoodBean bean = list.get(position);
        ImageView iv_logo = vh.getImageView(R.id.iv_logo);
        if (bean.getGoodsImageList() != null && bean.getGoodsImageList().size() > 0) {
            String imgurl = bean.getGoodsImageList().get(0).getGoodImg();
            GlideUtils.CreateImageRound(imgurl, iv_logo, 5);
        }
        vh.getTextView(R.id.text_name).setText(bean.getTitle());
        vh.getTextView(R.id.text_date).setText(bean.getCategoryAname() + "、" + bean.getCategoryBname() + "、" + bean.getCategoryCname());
        vh.getTextView(R.id.text_price).setText(bean.getSellPrice() + "");

        TypefaceUtil.setTextType(context, "DINOT-Bold.ttf", vh.getTextView(R.id.text_name));
        TypefaceUtil.setTextType(context, "DINOT-Bold.ttf", vh.getTextView(R.id.text_price));

    }

    public void setData(List<GoodBean> list) {
        this.list = list;
    }
}
