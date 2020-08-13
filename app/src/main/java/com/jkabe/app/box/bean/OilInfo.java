package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/8/13
 * @name:OilInfo
 */
public class OilInfo implements Serializable {

    /**
     * id : 922abb741fd04128aad6623edb708145
     * hashcode : 3c5a4c1071b388893de46847a7d28101c44734c29
     * blockid : 3
     * carcard : 粤BT2999
     * oilStationName : 中国石油加油站(留仙站)
     * oilStationAddress : 深圳市南山区西丽留仙大道北侧坪山路口东侧(近大学城南门)
     * oilType : 汽油
     * oilModel : #92
     * oilSize : 30.0
     * oilTime : 2019-11-29 13:09:30
     * uploadtime : 2019-12-02 11:25:43
     * integral : 1000
     */

    private String id;
    private String hashcode;
    private int blockid;
    private String carcard;
    private String oilStationName;
    private String oilStationAddress;
    private String oilType;
    private String oilModel;
    private double oilSize;
    private String oilTime;
    private String uploadtime;
    private int integral;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public int getBlockid() {
        return blockid;
    }

    public void setBlockid(int blockid) {
        this.blockid = blockid;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getOilStationName() {
        return oilStationName;
    }

    public void setOilStationName(String oilStationName) {
        this.oilStationName = oilStationName;
    }

    public String getOilStationAddress() {
        return oilStationAddress;
    }

    public void setOilStationAddress(String oilStationAddress) {
        this.oilStationAddress = oilStationAddress;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public String getOilModel() {
        return oilModel;
    }

    public void setOilModel(String oilModel) {
        this.oilModel = oilModel;
    }

    public double getOilSize() {
        return oilSize;
    }

    public void setOilSize(double oilSize) {
        this.oilSize = oilSize;
    }

    public String getOilTime() {
        return oilTime;
    }

    public void setOilTime(String oilTime) {
        this.oilTime = oilTime;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
