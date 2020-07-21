package com.jkabe.app.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;

import static com.jkabe.app.android.util.MeasureWidthUtils.getScreenHeight;
import static com.jkabe.app.android.util.MeasureWidthUtils.getScreenWidth;

public class ShotShareUtil {
    /**截屏分享，供外部调用**/
    public static void shotShare(Activity context){
        //截屏
        String path=screenShot(context);
        //分享
        if(!Utility.isEmpty(path)){
            sharewxFriends(context,path);
        }
    }

    /**获取截屏**/
    private static String screenShot(Activity context){
        String imagePath=null;
        Bitmap bitmap= snapShotWithoutStatusBar(context);
        if(bitmap!=null){
            try {
                // 图片文件路径
                imagePath = FileManager.getFilePath()+"share.png";
                LogUtils.e("path", "====imagePath====" + imagePath);
                File file = new File(imagePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                return imagePath;
            } catch (Exception e) {
                LogUtils.e("====screenshot:error====" , e);
            }
        }
        return null;
    }

    /**分享调取系统分享**/
    private static void ShareImage(Context context,String imagePath){
        if (imagePath != null){
            Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
            File file = new File(imagePath);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));// 分享的内容
            intent.setType("image/*");// 分享发送的数据类型
            Intent chooser = Intent.createChooser(intent, "Share screen shot");
            if(intent.resolveActivity(context.getPackageManager()) != null){
                context.startActivity(chooser);
            }
        } else {
            ToastUtil.showToast("先截屏，再分享");
        }
    }

    private static void sharewxFriends(Context context,String imagePath){
            OnekeyShare oks = new OnekeyShare();
            oks.setPlatform(Wechat.NAME);
            oks.setImagePath(imagePath);
            oks.show(context);

    }
    /**
     * 获取当前屏幕截图，不包含状态栏
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top + MeasureWidthUtils.dip2px(activity,46);

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        int lastHeight = height - frame.top;
        try {
            bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, lastHeight  );
        }catch (IllegalArgumentException e){
            bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight  );
        }

        view.destroyDrawingCache();
        return bp;
    }
}
