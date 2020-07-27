package com.jkabe.app.box.box;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jkabe.app.box.base.BaseActivity;
import com.jkabe.app.box.util.ClipboardUtils;
import com.jkabe.app.box.util.QRCodeUtil;
import com.jkabe.app.box.util.ToastUtil;
import com.jkabe.box.R;

/**
 * @author: zt
 * @date: 2020/7/21
 * @name:充币
 */
public class RechargeActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_style, text_copy;
    private ImageView iv_code;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    protected void initView() {
        text_copy = getView(R.id.text_copy);
        iv_code = getView(R.id.iv_code);
        text_style = getView(R.id.text_style);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("充币");
        text_copy.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String text = text_style.getText().toString();
        Bitmap mBitmap = QRCodeUtil.createQRCode(text, 600);
        iv_code.setImageBitmap(mBitmap);
        iv_code.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_copy:
                String text = text_style.getText().toString();
                ClipboardUtils.copyText(text);
                ToastUtil.showToast("复制成功");
                break;
        }
    }

}
