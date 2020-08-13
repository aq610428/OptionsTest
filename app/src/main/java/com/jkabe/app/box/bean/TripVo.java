package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/8/13
 * @name:TripVo
 */
public class TripVo implements Serializable {

    /**
     * id : c658f4fb15fd4c8086bc36523039c5aa
     * hashcode : 7a47f150e7b2c6000d416333ff1fbd693c01c4a1b
     * blockid : 3033763
     * carcard : 湘LNE008
     * imeicode : 972290008826
     * startTime : 2020-08-13 08:21:59
     * startLng : 113.240901
     * startLat : 25.940824
     * startBaiduLng : 113.240901
     * startBaiduLat : 25.940824
     * endLng : 113.415811
     * endLat : 25.97181
     * endBaiduLng : 113.415811
     * endBaiduLat : 25.97181
     * startAddress : 湖南省郴州市资兴市沿江北路;碧水云轩-西门南119米
     * endAddress : 湖南省郴州市资兴市S322(环城东路);中国邮政储蓄银行(兴宁邮政支行)附近24米
     * endTime : 2020-08-13 09:17:50
     * mile : 31050
     * oil : 2690.0
     * avgoil : 0.0
     * linger : 0
     * speedMax : 85
     * speedAvg : 33
     * tripTime : 3300
     * acceCount : 0
     * deceCount : 0
     * sharpturnCount : 0
     * voltage : 12.3
     * uploadTime : 2020-08-13 09:17:52
     * integral : 31
     * partnername : 元征GOLO盒子
     */

    private String id;
    private String hashcode;
    private String blockid;
    private String carcard;
    private String imeicode;
    private String startTime;
    private String startLng;
    private String startLat;
    private String startBaiduLng;
    private String startBaiduLat;
    private String endLng;
    private String endLat;
    private String endBaiduLng;
    private String endBaiduLat;
    private String startAddress;
    private String endAddress;
    private String endTime;
    private int mile;
    private double oil;
    private double avgoil;
    private int linger;
    private int speedMax;
    private int speedAvg;
    private int tripTime;
    private int acceCount;
    private int deceCount;
    private int sharpturnCount;
    private double voltage;
    private String uploadTime;
    private int integral;
    private String partnername;

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

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getImeicode() {
        return imeicode;
    }

    public void setImeicode(String imeicode) {
        this.imeicode = imeicode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartLng() {
        return startLng;
    }

    public void setStartLng(String startLng) {
        this.startLng = startLng;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getStartBaiduLng() {
        return startBaiduLng;
    }

    public void setStartBaiduLng(String startBaiduLng) {
        this.startBaiduLng = startBaiduLng;
    }

    public String getStartBaiduLat() {
        return startBaiduLat;
    }

    public void setStartBaiduLat(String startBaiduLat) {
        this.startBaiduLat = startBaiduLat;
    }

    public String getEndLng() {
        return endLng;
    }

    public void setEndLng(String endLng) {
        this.endLng = endLng;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getEndBaiduLng() {
        return endBaiduLng;
    }

    public void setEndBaiduLng(String endBaiduLng) {
        this.endBaiduLng = endBaiduLng;
    }

    public String getEndBaiduLat() {
        return endBaiduLat;
    }

    public void setEndBaiduLat(String endBaiduLat) {
        this.endBaiduLat = endBaiduLat;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getMile() {
        return mile;
    }

    public void setMile(int mile) {
        this.mile = mile;
    }

    public double getOil() {
        return oil;
    }

    public void setOil(double oil) {
        this.oil = oil;
    }

    public double getAvgoil() {
        return avgoil;
    }

    public void setAvgoil(double avgoil) {
        this.avgoil = avgoil;
    }

    public int getLinger() {
        return linger;
    }

    public void setLinger(int linger) {
        this.linger = linger;
    }

    public int getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(int speedMax) {
        this.speedMax = speedMax;
    }

    public int getSpeedAvg() {
        return speedAvg;
    }

    public void setSpeedAvg(int speedAvg) {
        this.speedAvg = speedAvg;
    }

    public int getTripTime() {
        return tripTime;
    }

    public void setTripTime(int tripTime) {
        this.tripTime = tripTime;
    }

    public int getAcceCount() {
        return acceCount;
    }

    public void setAcceCount(int acceCount) {
        this.acceCount = acceCount;
    }

    public int getDeceCount() {
        return deceCount;
    }

    public void setDeceCount(int deceCount) {
        this.deceCount = deceCount;
    }

    public int getSharpturnCount() {
        return sharpturnCount;
    }

    public void setSharpturnCount(int sharpturnCount) {
        this.sharpturnCount = sharpturnCount;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getPartnername() {
        return partnername;
    }

    public void setPartnername(String partnername) {
        this.partnername = partnername;
    }
}
