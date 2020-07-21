package com.jkabe.app.android.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jkabe.app.android.base.BaseFragment;
import com.jkabe.app.android.bean.CommonalityModel;
import com.jkabe.app.android.box.IncomeActivity;
import com.jkabe.app.android.config.NetWorkListener;
import com.jkabe.box.R;
import org.json.JSONObject;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:挖矿
 */
public class MiningFragmnt extends BaseFragment implements NetWorkListener, View.OnClickListener {
    private View rootView;
    private TextView text_right;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mining, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        text_right = getView(rootView, R.id.text_right);
        text_right.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_right:
                startActivity(new Intent(getContext(), IncomeActivity.class));
                break;
        }
    }
}
