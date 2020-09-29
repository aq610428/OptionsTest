package com.jkabe.app.box.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.jkabe.app.box.adapter.FragmentAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.base.BaseApplication;
import com.jkabe.app.box.box.fragement.PoliceFragment;
import com.jkabe.app.box.box.fragement.SystemFragment;
import com.jkabe.app.box.box.fragement.SystemFragment1;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/10
 * @name:OrderActivity
 */
public class MsgActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;
    private LinearLayout rl_tab1, rl_tab2,rl_tab3;
    private TextView text_tab1, text_tab2,text_tab3;
    private TextView text_line1, text_line2,text_line3;
    private TextView title_text_tv, title_left_btn;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_msg);
        BaseApplication.activityTaskManager.putActivity("OrderActivity", this);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("消息列表");
        mViewPager = getView(R.id.viewPager);
        rl_tab1 = getView(R.id.rl_tab1);
        rl_tab2 = getView(R.id.rl_tab2);
        rl_tab3= getView(R.id.rl_tab3);
        text_tab1 = getView(R.id.text_tab1);
        text_tab2 = getView(R.id.text_tab2);
        text_tab3 = getView(R.id.text_tab3);
        text_line1 = getView(R.id.text_line1);
        text_line2 = getView(R.id.text_line2);
        text_line3 = getView(R.id.text_line3);
    }

    @Override
    protected void initData() {
        rl_tab1.setOnClickListener(new MyOnClickListener(0));
        rl_tab2.setOnClickListener(new MyOnClickListener(1));
        rl_tab3.setOnClickListener(new MyOnClickListener(2));
        mFragmentList.add(new PoliceFragment());
        mFragmentList.add(new SystemFragment());
        mFragmentList.add(new SystemFragment1());
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
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
                text_line1.setVisibility(View.VISIBLE);
                text_tab1.setTextColor(Color.parseColor("#3F80F4"));
                break;
            case 1:
                text_line2.setVisibility(View.VISIBLE);
                text_tab2.setTextColor(Color.parseColor("#3F80F4"));
                break;
            case 2:
                text_line3.setVisibility(View.VISIBLE);
                text_tab3.setTextColor(Color.parseColor("#3F80F4"));
                break;
        }
    }

    public void clealView() {
        text_tab1.setTextColor(Color.parseColor("#333333"));
        text_tab2.setTextColor(Color.parseColor("#333333"));
        text_tab3.setTextColor(Color.parseColor("#333333"));
        text_line1.setVisibility(View.INVISIBLE);
        text_line2.setVisibility(View.INVISIBLE);
        text_line3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        finish();
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
