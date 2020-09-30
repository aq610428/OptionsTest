package com.jkabe.app.box.util;

import com.jkabe.app.box.bean.Block;
import com.jkabe.app.box.bean.CarInfo;
import com.jkabe.app.box.bean.UserInfo;
import com.jkabe.box.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


    /******清除缓存*****/
    public static void clealCacheDisk() {
        CacheDiskUtils cacheDiskUtils = CacheDiskUtils.getInstance();
        cacheDiskUtils.remove("userInfo");
        cacheDiskUtils.remove("carInfo");
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


    public static List<Block> getblocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block("行程数据", R.mipmap.icon_trip_usd));
        blocks.add(new Block("维保数据", R.mipmap.icon_maint_usd));
        blocks.add(new Block("保险数据", R.mipmap.icon_insurance_usd));
        blocks.add(new Block("违章数据", R.mipmap.icon_break_usd));
        blocks.add(new Block("加油数据", R.mipmap.icon_oil_usd));
        blocks.add(new Block("过户数据", R.mipmap.icon_use_usd));
        return blocks;

    }


    public static List<Block> getArray() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block("添加地址", R.mipmap.icon_address_me));
        blocks.add(new Block("交易密码", R.mipmap.icon_pass_usd1));
        blocks.add(new Block("联系我们", R.mipmap.icon_contoct));
        blocks.add(new Block("设备解除", R.mipmap.icon_unbind));
        blocks.add(new Block("关于我们", R.mipmap.icon_about_new));
        blocks.add(new Block("消息中心", R.mipmap.icon_goad));
        blocks.add(new Block("流量查询", R.mipmap.icon_pass_usd));
        blocks.add(new Block("退出登录", R.mipmap.icon_out));
        return blocks;
    }
}
