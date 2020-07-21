package com.jkabe.app.android.weight;

import android.content.Context;
import android.widget.ImageView;
import com.jkabe.app.android.banner.loader.ImageLoader;
import com.jkabe.app.android.glide.GlideUtils;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:MyLoader
 */
public class MyLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideUtils.CreateImageRound(path.toString(),imageView,5);
    }
}
