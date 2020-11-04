package com.jkabe.app.box.box.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.DefiAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.bean.OreInfo;
import com.jkabe.app.box.bean.TabBean;
import com.jkabe.app.box.box.DefiActivity;
import com.jkabe.app.box.config.Api;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.app.box.config.okHttpModel;
import com.jkabe.app.box.util.Constants;
import com.jkabe.app.box.util.JsonParse;
import com.jkabe.app.box.util.Md5Util;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.TypefaceUtil;
import com.jkabe.app.box.util.Utility;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/10/20
 * @name:TabFragment1
 */
public class TabFragment3 extends BaseFragment implements OnRefreshListener, NetWorkListener {
    private View rootView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private TextView text_tab1, text_tab2, text_tab3;
    private DefiAdapter defiAdapter;
    private List<TabBean.ItemsBean> beans = new ArrayList<>();
    private TabBean tabBean;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        swipe_target = rootView.findViewById(R.id.mListView);
        swipeToLoadLayout = rootView.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipe_target.setNestedScrollingEnabled(false);
        text_tab1 = getView(rootView, R.id.text_tab1);
        text_tab2 = getView(rootView, R.id.text_tab2);
        text_tab3 = getView(rootView, R.id.text_tab3);
        TypefaceUtil.setTextType(getActivity(), "DINOT-Bold.ttf", text_tab1);
        TypefaceUtil.setTextType(getActivity(), "DINOT-Bold.ttf", text_tab2);
        TypefaceUtil.setTextType(getActivity(), "DINOT-Bold.ttf", text_tab3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(layoutManager);
    }


    protected void lazyLoad() {
        query();
    }

    @Override
    public void onRefresh() {
        query();
    }


    public void query() {
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", SaveUtils.getSaveInfo().getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.PAY_CHANCE_BOX, params, Api.PAY_CHANCE_BOX_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.PAY_CHANCE_BOX_ID:
                        tabBean = JsonParse.getTabBeanJSON(object);
                        if (tabBean != null) {
                            setAdapter();
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }


    private void setAdapter() {
        text_tab1.setText(tabBean.getManage_amount() + "");
        text_tab2.setText(tabBean.getManage_num() + "");
        text_tab3.setText(tabBean.getProfit_amount() + "");
        beans = tabBean.getItems();
        if (beans != null && beans.size() > 0) {
            defiAdapter = new DefiAdapter(getContext(), beans);
            swipe_target.setAdapter(defiAdapter);
            defiAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), DefiActivity.class);
                    intent.putExtra("tabBean", tabBean);
                    intent.putExtra("type", beans.get(position).getType()+"");
                    startActivity(intent);
                }
            });
        }


    }


    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }
}
