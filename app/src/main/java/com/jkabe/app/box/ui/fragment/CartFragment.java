package com.jkabe.app.box.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.box.R;
import org.json.JSONObject;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/9/30
 * @name:CartFragment
 */
public class CartFragment extends BaseFragment implements NetWorkListener {
    private View rootView;

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
}
