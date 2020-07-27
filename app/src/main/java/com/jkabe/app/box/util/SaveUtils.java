package com.jkabe.app.box.util;

import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.UserInfo;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:SaveUtils
 */
public class SaveUtils {
    /******用户基本信息*****/
    public static void saveInfo(UserInfo info) {
        CacheDiskUtils cacheDiskUtils = CacheDiskUtils.getInstance();
        cacheDiskUtils.put("userInfo", (Serializable) info);
    }


    /******用户基本信息*****/
    public static UserInfo getSaveInfo() {
        UserInfo userInfo = (UserInfo) CacheDiskUtils.getInstance().getSerializable("userInfo");
        return userInfo;
    }


    /******用户基本信息*****/
    public static void clealCacheDisk() {
        CacheDiskUtils.getInstance().clear();
    }

    /******用户车辆信息信息*****/
    public static void saveCar(CarInfo carInfo) {
        CacheDiskUtils cacheDiskUtils = CacheDiskUtils.getInstance();
        cacheDiskUtils.put("carInfo", carInfo);
    }

    /******用户车辆信息信息*****/
    public static CarInfo getCar() {
        CarInfo carInfo = (CarInfo) CacheDiskUtils.getInstance().getSerializable("carInfo");
        return carInfo;
    }
}
