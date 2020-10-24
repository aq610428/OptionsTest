package com.jkabe.app.box.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.jkabe.app.box.adapter.FragmentAdapter;
import com.jkabe.app.box.base.BaseFragment;
import com.jkabe.app.box.box.fragement.TabFragment1;
import com.jkabe.app.box.box.fragement.TabFragment2;
import com.jkabe.app.box.box.fragement.TabFragment3;
import com.jkabe.app.box.box.fragement.TabFragment4;
import com.jkabe.app.box.box.fragement.TabFragment5;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/10/20
 * @name:OreLostragment
 */
public class OreLostragment extends BaseFragment implements  ViewPager.OnPageChangeListener {
    private View rootView;
    private TextView text_tab1,text_tab2,text_tab3,text_tab4,text_tab5;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_orelost, container, false);
            initView();
            lazyLoad();
        }
        return rootView;
    }



    private void initView() {
        mViewPager = getView(rootView,R.id.viewPager);
        text_tab1=getView(rootView,R.id.text_tab1);
        text_tab2=getView(rootView,R.id.text_tab2);
        text_tab3=getView(rootView,R.id.text_tab3);
        text_tab4=getView(rootView,R.id.text_tab4);
        text_tab5=getView(rootView,R.id.text_tab5);
        text_tab1.setOnClickListener(new MyOnClickListener(0));
        text_tab2.setOnClickListener(new MyOnClickListener(1));
        text_tab3.setOnClickListener(new MyOnClickListener(2));
        text_tab4.setOnClickListener(new MyOnClickListener(3));
        text_tab5.setOnClickListener(new MyOnClickListener(4));
        mFragmentList.add(new TabFragment1());
        mFragmentList.add(new TabFragment2());
        mFragmentList.add(new TabFragment4());
        mFragmentList.add(new TabFragment3());
        mFragmentList.add(new TabFragment5());
        mFragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOnPageChangeListener(this);
    }


    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            setView(index);
            mViewPager.setCurrentItem(index);
        }
    }

    public void setView(int index) {
        clealView();
        switch (index) {
            case 0:
                text_tab1.setTextColor(Color.parseColor("#FFFFFF"));
                text_tab1.setBackgroundColor(Color.parseColor("#3F63F4"));
                break;
            case 1:
                text_tab2.setTextColor(Color.parseColor("#FFFFFF"));
                text_tab2.setBackgroundColor(Color.parseColor("#3F63F4"));
                break;
            case 2:
                text_tab3.setTextColor(Color.parseColor("#FFFFFF"));
                text_tab3.setBackgroundColor(Color.parseColor("#3F63F4"));
                break;
            case 3:
                text_tab4.setTextColor(Color.parseColor("#FFFFFF"));
                text_tab4.setBackgroundColor(Color.parseColor("#3F63F4"));
                break;
            case 4:
                text_tab5.setTextColor(Color.parseColor("#FFFFFF"));
                text_tab5.setBackgroundColor(Color.parseColor("#3F63F4"));
                break;
        }
    }

    public void clealView() {
        text_tab1.setTextColor(Color.parseColor("#828282"));
        text_tab2.setTextColor(Color.parseColor("#828282"));
        text_tab3.setTextColor(Color.parseColor("#828282"));
        text_tab4.setTextColor(Color.parseColor("#828282"));
        text_tab5.setTextColor(Color.parseColor("#828282"));
        text_tab1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        text_tab2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        text_tab3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        text_tab4.setBackgroundColor(Color.parseColor("#FFFFFF"));
        text_tab5.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }


    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setView(position);
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
