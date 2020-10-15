package com.jkabe.app.box.util;


import com.jkabe.app.box.bean.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String APPID = "2021001198636188";
    public static final String RSA_PRIVATE = "G/m92T9Vvo5fAaeNL+ACcQ==";
    public static final String APP_ID = "wxdebf49e4963895e1";
    public static String SUCESSCODE = "EXECUTE_SUCCESS";
    public static final String PARTNERID = "761ea3b34cc74aa58ac74d5e60048997";//区分动画
    public static final String SECREKEY = "f6af9cb871dd4237a4b89b8f5bbf8456";//区分动画
    public static String TYPE = "15";//区分权限
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
        return banners;
    }

}
