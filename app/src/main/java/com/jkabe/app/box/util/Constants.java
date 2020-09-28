package com.jkabe.app.box.util;


import com.jkabe.app.box.bean.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static String SUCESSCODE = "EXECUTE_SUCCESS";
    public static final String PARTNERID = "761ea3b34cc74aa58ac74d5e60048997";//区分动画
    public static final String SECREKEY = "f6af9cb871dd4237a4b89b8f5bbf8456";//区分动画
    public static String TYPE = "15";//区分权限
    public static String ADVERTTYPE = "advertType";//区分权限
    public static String ADVER = "1";//区分权限
    public static final String oilUrl = "http://oil.card.jkabe.com/";//车油惠
    public static final String CITY = "city";//车油惠
    public static final String LAT = "lat";//车油惠
    public static final String LON = "lon";//车油惠
    public static final String OIL = "oil";//车油惠
    public static final String TOKEN = "token";//车油惠
    public static final String MOBILE = "mobile";//车油惠\
    public static final String PASSWORD = "password";//车油惠

    public static List<ImageInfo> getDate() {
        List<ImageInfo> banners = new ArrayList<>();
        banners.add(new ImageInfo("今日新品", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600407927693&di=2eafa4b9894d39ba794e03ac4b18f3b3&imgtype=0&src=http%3A%2F%2Fppic.meituba.com%3A84%2Fuploads%2Fallimg%2F2016%2F10%2F11%2F163_1215.jpg"));
        banners.add(new ImageInfo("肉禽蛋", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600407945533&di=d91ec13f310c82bc98fe8ece5f5d7479&imgtype=0&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1487096634%2C8694877%26fm%3D214%26gp%3D0.jpg"));
        banners.add(new ImageInfo("水产海鲜", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600407927691&di=eb36a2b720f45d3d2a635eebdeeec924&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fb%2F54938ee1bb87a.jpg"));
        banners.add(new ImageInfo("水果", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600407927690&di=cbebe40a44c6a34b9f0362cef4ac8496&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fd%2F5485436435fe6.jpg"));
        return banners;
    }

}
