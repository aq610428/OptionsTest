package com.jkabe.app.box.box.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.weight.NoDataView;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/10/20
 * @name:TabFragment1
 */
public class TabFragment5 extends BaseFragment {
    private View rootView;
    private NoDataView noDataView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab5, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        noDataView = rootView.findViewById(R.id.noDataView);
        noDataView.textView.setText("暂未开放");
    }


    protected void lazyLoad() {

    }
}
