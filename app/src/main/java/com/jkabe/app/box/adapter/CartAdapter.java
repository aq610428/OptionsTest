package com.jkabe.app.box.adapter;

import android.view.View;
import android.widget.TextView;
import com.jkabe.app.box.bean.CartBean;
import com.jkabe.app.box.glide.GlideUtils;
import com.jkabe.app.box.ui.fragment.CartFragment;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.box.R;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/9/18
 * @name:ConfimAdapter
 */
public class CartAdapter extends AutoRVAdapter {
    public List<CartBean> list;
    public Map<Integer, CartBean> map;
    private CartFragment fragment;

    public CartAdapter(CartFragment fragment, List<CartBean> list) {
        super(fragment.getContext(), list);
        this.list = list;
        map = new LinkedHashMap<>();
        this.fragment=fragment;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_cart_bg;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        CartBean bean=list.get(position);
        GlideUtils.CreateImageRound(bean.getSmallImg(), vh.getImageView(R.id.iv_logo), 5);
        vh.getTextView(R.id.text_name).setText(bean.getTitle());
        vh.getTextView(R.id.text_number).setText(bean.getGoodNumber()+"");
        vh.getTextView(R.id.text_price).setText(bean.getSellPrice()+"");



        TextView text_choose = vh.getTextView(R.id.text_choose);
        if (map.get(position) != null) {
            text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose_un, 0, 0, 0);
        } else {
            text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose, 0, 0, 0);
        }
        TextView text_number = vh.getTextView(R.id.text_number);
        text_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map.get(position) != null) {
                    map.remove(position);
                    text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose, 0, 0, 0);
                } else {
                    map.put(position, list.get(position));
                    text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose_un, 0, 0, 0);
                }
                fragment.updateView();
            }
        });
        vh.getLinearLayout(R.id.ll_redue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = text_number.getText().toString();
                BigDecimal num = BigDecimalUtils.sub(new BigDecimal(text), BigDecimal.ONE);
                if (num.doubleValue() < 1) {
                    text_number.setText("1");
                    bean.setGoodNumber(1);
                } else {
                    text_number.setText(num.toPlainString());
                    bean.setGoodNumber(num.intValue());
                    fragment.updateView();
                }
            }
        });

        vh.getLinearLayout(R.id.ll_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = text_number.getText().toString();
                BigDecimal num = BigDecimalUtils.add(new BigDecimal(text), BigDecimal.ONE);
                text_number.setText(num.toPlainString());
                bean.setGoodNumber(num.intValue());
                fragment.updateView();
            }
        });
    }

    public void setData(List<CartBean> beanList) {
        this.list=beanList;
    }
}
