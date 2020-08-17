package com.jkabe.app.box.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jkabe.app.box.adapter.BlockAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.Block;
import com.jkabe.app.box.box.InsuranceActivity;
import com.jkabe.app.box.box.MaintenanceActivity;
import com.jkabe.app.box.box.OilActivity;
import com.jkabe.app.box.box.TripActivity;
import com.jkabe.app.box.box.ViolationActivity;
import com.jkabe.app.box.util.SaveUtils;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.box.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/8/10
 * @name:区块数据
 */
public class BlockActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private RecyclerView recyclerView;
    private List<Block> blocks = new ArrayList<>();
    private BlockAdapter blockAdapter;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_block);
    }

    @Override
    protected void initView() {
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("区块数据");
    }

    @Override
    protected void initData() {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        blocks = SaveUtils.getblocks();
        blockAdapter = new BlockAdapter(this, blocks);
        recyclerView.setAdapter(blockAdapter);
        blockAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = blocks.get(position).getName();
                switch (name) {
                    case "行程数据":
                        startActivity(new Intent(BlockActivity.this, TripActivity.class));
                        break;
                    case "维保数据":
                        startActivity(new Intent(BlockActivity.this, MaintenanceActivity.class));
                        break;
                    case "保险数据":
                        startActivity(new Intent(BlockActivity.this, InsuranceActivity.class));
                        break;
                    case "违章数据":
                        startActivity(new Intent(BlockActivity.this, ViolationActivity.class));
                        break;
                    case "加油数据":
                        startActivity(new Intent(BlockActivity.this, OilActivity.class));
                        break;
                    case "过户数据":
                        ToastUtil.showToast("暂未开放");
                        break;
                }
            }
        });
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
}
