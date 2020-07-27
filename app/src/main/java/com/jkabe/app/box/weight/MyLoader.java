package com.jkabe.app.box.weight;

import android.content.Context;
import android.widget.ImageView;
import com.jkabe.app.box.banner.loader.ImageLoader;
import com.jkabe.app.box.glide.GlideUtils;

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
