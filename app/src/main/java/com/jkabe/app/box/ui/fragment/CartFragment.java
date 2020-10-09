package com.jkabe.app.box.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jkabe.app.box.adapter.CartAdapter;
import com.jkabe.app.box.adapter.WareAdapter1;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.ui.ConfirmActivity;
import com.jkabe.app.box.ui.MainActivity;
import com.jkabe.app.box.util.BigDecimalUtils;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/9/30
 * @name:CartFragment
 */
public class CartFragment extends BaseFragment implements NetWorkListener, View.OnClickListener {
    private View rootView;
    private TextView text_bold, text_choose, text_edit, text_balance, text_delete,text_total;
    private RecyclerView iv_list, recyclerView;
    private CartAdapter adapter;
    private List<ImageInfo> list = new ArrayList<>();
    private List<ImageInfo> beans = new ArrayList<>();
    private WareAdapter1 wareAdapter1;
    private LinearLayout ll_total;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_cart, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
    }


    private void initView() {
        text_total = getView(rootView, R.id.text_total);
        text_delete = getView(rootView, R.id.text_delete);
        ll_total = getView(rootView, R.id.ll_total);
        text_balance = getView(rootView, R.id.text_balance);
        text_edit = getView(rootView, R.id.text_edit);
        recyclerView = getView(rootView, R.id.recyclerView);
        text_choose = getView(rootView, R.id.text_choose);
        iv_list = getView(rootView, R.id.iv_list);
        text_bold = getView(rootView, R.id.text_bold);
        text_choose.setOnClickListener(this);
        text_edit.setOnClickListener(this);
        text_balance.setOnClickListener(this);
        text_delete.setOnClickListener(this);

        TypefaceUtil.setTextType(getActivity(),"ttp.TTF",text_bold);
        TypefaceUtil.setTextType(getActivity(),"ttp.TTF",text_total);

        GridLayoutManager manager1 = new GridLayoutManager(getContext(), 2);
        iv_list.setLayoutManager(manager1);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        list = Constants.getDate();
        beans = Constants.getDate1();
        adapter = new CartAdapter(this, beans);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        wareAdapter1 = new WareAdapter1(getContext(), list);
        iv_list.setHasFixedSize(true);
        iv_list.setAdapter(wareAdapter1);


        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            TextView text_num = mainActivity.mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.text_num);
            text_num.setVisibility(View.VISIBLE);
            text_num.setText(beans.size() + "");
        }
    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_MODEL_BIND_ID:

                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(Exception e) {

    }


    private boolean isChoose;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_choose:
                if (!isChoose) {
                    isChoose = true;
                    text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose_un, 0, 0, 0);
                    text_choose.setText("取消");
                    chooseAll();
                } else {
                    isChoose = false;
                    text_choose.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_choose, 0, 0, 0);
                    text_choose.setText("全选");
                    cancelAll();
                }
                break;
            case R.id.text_edit:
                setView();
                break;
            case R.id.text_balance:
                startActivity(new Intent(getContext(), ConfirmActivity.class));
                break;
            case R.id.text_delete:

                break;

        }
    }

    private void setView() {
        String text = text_edit.getText().toString();
        if ("编辑".equals(text)) {
            ll_total.setVisibility(View.GONE);
            text_balance.setVisibility(View.GONE);
            text_delete.setVisibility(View.VISIBLE);
            text_edit.setText("完成");
        } else {
            ll_total.setVisibility(View.VISIBLE);
            text_delete.setVisibility(View.GONE);
            text_balance.setVisibility(View.VISIBLE);
            text_edit.setText("编辑");
        }

    }


    /*****全选*****/
    public void chooseAll() {
        BigDecimal total=BigDecimal.ZERO;
        if (adapter != null) {
            for (int i = 0; i < beans.size(); i++) {
                adapter.map.put(i, beans.get(i));
                total= BigDecimalUtils.add(total,new BigDecimal("28.8"));
            }
            adapter.notifyItemRangeChanged(0, beans.size());
            text_total.setText("￥"+total.toPlainString());
        }
    }


    /*****取消全选*****/
    public void cancelAll() {
        if (adapter != null) {
            adapter.map.clear();
            adapter.notifyItemRangeChanged(0, beans.size());
            text_total.setText("￥0");
        }
    }

    /*****合计*****/
    public void updateView() {
        BigDecimal total=BigDecimal.ZERO;
        if (adapter != null&&adapter.map!=null) {
            for(Map.Entry<Integer, ImageInfo> entry : adapter.map.entrySet()){
                total= BigDecimalUtils.add(total,new BigDecimal("28.8"));
            }
            text_total.setText("￥"+total.toPlainString());
        }
    }
}
