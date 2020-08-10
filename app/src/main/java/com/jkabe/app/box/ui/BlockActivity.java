package com.jkabe.app.box.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jkabe.app.box.adapter.BlockAdapter;
import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.bean.Block;
import com.jkabe.app.box.util.SaveUtils;
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
    private List<Block> blocks=new ArrayList<>();
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        blocks= SaveUtils.getblocks();
        blockAdapter=new BlockAdapter(this,blocks);
        recyclerView.setAdapter(blockAdapter);
    }
}
