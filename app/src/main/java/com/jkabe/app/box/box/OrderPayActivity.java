package com.jkabe.app.box.box;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.jkabe.app.box.adapter.FragmentAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.box.fragement.AllFragment;
import com.jkabe.app.box.box.fragement.CompletedFragment;
import com.jkabe.app.box.box.fragement.CompletedFragment1;
import com.jkabe.app.box.box.fragement.PayFragment;
import com.jkabe.app.box.box.fragement.TakeFragment;
import com.jkabe.app.box.weight.PreferenceUtils;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/9/22
 * @name:OrderActivity
 */
public class OrderPayActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private TextView title_text_tv, title_left_btn;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;
    private LinearLayout rl_tab1, rl_tab2, rl_tab3, rl_tab4,rl_tab5;
    private TextView text_tab1, text_tab2, text_tab3, text_tab4,text_tab5;
    private TextView text_line1, text_line2, text_line3, text_line4,text_line5;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order_list);
        PreferenceUtils.setPrefString(this,"pay","1002");
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("订单管理");

        mViewPager = getView(R.id.viewPager);
        rl_tab1 = getView(R.id.rl_tab1);
        rl_tab2 = getView(R.id.rl_tab2);
        rl_tab3 = getView(R.id.rl_tab3);
        rl_tab4 = getView(R.id.rl_tab4);
        rl_tab5 = getView(R.id.rl_tab5);

        text_tab1 = getView(R.id.text_tab1);
        text_tab2 = getView(R.id.text_tab2);
        text_tab3 = getView(R.id.text_tab3);
        text_tab4 = getView(R.id.text_tab4);
        text_tab5 = getView(R.id.text_tab5);

        text_line1 = getView(R.id.text_line1);
        text_line2 = getView(R.id.text_line2);
        text_line3 = getView(R.id.text_line3);
        text_line4 = getView(R.id.text_line4);
        text_line5 = getView(R.id.text_line5);
    }

    @Override
    protected void initData() {
        rl_tab1.setOnClickListener(new MyOnClickListener(0));
        rl_tab2.setOnClickListener(new MyOnClickListener(1));
        rl_tab3.setOnClickListener(new MyOnClickListener(2));
        rl_tab4.setOnClickListener(new MyOnClickListener(3));
        rl_tab5.setOnClickListener(new MyOnClickListener(4));
        mFragmentList.add(new AllFragment());
        mFragmentList.add(new PayFragment());
        mFragmentList.add(new TakeFragment());
        mFragmentList.add(new CompletedFragment());
        mFragmentList.add(new CompletedFragment1());
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOnPageChangeListener(this);


        int position=getIntent().getIntExtra("position",0);
        setView(position);
        mViewPager.setCurrentItem(position);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
        }
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
                text_tab1.setTextColor(Color.parseColor("#3F63F4"));
                break;
            case 1:
                text_line2.setVisibility(View.VISIBLE);
                text_tab2.setTextColor(Color.parseColor("#3F63F4"));
                break;
            case 2:
                text_line3.setVisibility(View.VISIBLE);
                text_tab3.setTextColor(Color.parseColor("#3F63F4"));
                break;
            case 3:
                text_line4.setVisibility(View.VISIBLE);
                text_tab4.setTextColor(Color.parseColor("#3F63F4"));
                break;
            case 4:
                text_line5.setVisibility(View.VISIBLE);
                text_tab5.setTextColor(Color.parseColor("#3F63F4"));
                break;
        }
    }

    public void clealView() {
        text_tab1.setTextColor(Color.parseColor("#333333"));
        text_tab2.setTextColor(Color.parseColor("#333333"));
        text_tab3.setTextColor(Color.parseColor("#333333"));
        text_tab4.setTextColor(Color.parseColor("#333333"));
        text_tab5.setTextColor(Color.parseColor("#333333"));
        text_line1.setVisibility(View.INVISIBLE);
        text_line2.setVisibility(View.INVISIBLE);
        text_line3.setVisibility(View.INVISIBLE);
        text_line4.setVisibility(View.INVISIBLE);
        text_line5.setVisibility(View.INVISIBLE);
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
