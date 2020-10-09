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
import com.jkabe.app.box.weight.NoDataView;
import com.jkabe.box.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/22
 * @name:待提货
 */
public class TakeFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener {
    private View rootView;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<ImageInfo> array = new ArrayList<>();
    private TakeAdapter takeAdapter;
    private NoDataView mNoDataView;


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
        mNoDataView=getView(rootView,R.id.mNoDataView);
        swipeToLoadLayout=getView(rootView,R.id.swipeToLoadLayout);
        swipe_target=getView(rootView,R.id.swipe_target);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(linearLayoutManager);
        mNoDataView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        },2000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    protected void lazyLoad() {

    }
}
