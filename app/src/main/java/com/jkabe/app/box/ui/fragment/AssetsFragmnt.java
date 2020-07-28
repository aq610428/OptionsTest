package com.jkabe.app.box.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jkabe.app.box.adapter.AssetsAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.CommonalityModel;
import com.jkabe.app.box.config.NetWorkListener;
import com.jkabe.box.R;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import crossoverone.statuslib.StatusUtil;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:资产
 */
public class AssetsFragmnt extends BaseFragment implements NetWorkListener {
    private View rootView;
    private RecyclerView recyclerView;
    private AssetsAdapter assetsAdapter;
    private List<String> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_assets, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        recyclerView = getView(rootView, R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        list.add("USDT");
        list.add("BOX");
        assetsAdapter=new AssetsAdapter(this,list);
        recyclerView.setAdapter(assetsAdapter);
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
}
