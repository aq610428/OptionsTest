package com.jkabe.app.box.box.fragement;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jkabe.app.box.adapter.TakeAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.bean.ImageInfo;
import com.jkabe.box.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/22
 * @name:CompletedFragment
 */
public class CompletedFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener {
    private View rootView;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<ImageInfo> array = new ArrayList<>();
    private TakeAdapter takeAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_completed, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        swipe_target = getView(rootView, R.id.swipe_target);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(linearLayoutManager);

        array.add(new ImageInfo("https://img.alicdn.com/imgextra/i1/2423612906/O1CN01ZdaDVp1XKzaVMxYOA_!!2423612906.jpg"));
        array.add(new ImageInfo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01Z36kSU1XKzaYaBtHb_!!2423612906.jpg"));
        array.add(new ImageInfo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01z935sk1XKzaYvBd0d_!!2423612906.jpg"));
        array.add(new ImageInfo("https://img.alicdn.com/imgextra/i2/2423612906/O1CN01d8H5WT1XKzaYvAtHU_!!2423612906.jpg"));
        takeAdapter = new TakeAdapter(getContext(), array);
        swipe_target.setAdapter(takeAdapter);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    protected void lazyLoad() {

    }
}
