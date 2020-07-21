package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/16
 * @name:LatInfo
 */
public class LatInfo implements Serializable {

    /**
     * lng : 114.035573
     * lat : 22.622718
     * baidulng : 114.04719239870911
     * baidulat : 22.62578087660502
     * gpstime : 2020-07-13 16:48:13
     * miles : 10387
     * speed : 0
     * direction : 20
     * lngtype : 1
     * lattype : 1
     * locationtype : 0
     * num : 10
     * gsm : 26
     * descriptionToString : {"gsm":"GSM 的信号强弱,GSM 信号强度最大为 31","lng":"经度","lngtype":"经度类型 1 东经 0 西经","num":"GPS 信息卫星数","speed":"行驶速度","miles":"当前里程","baidulat":"百度纬度","lattype":"纬度类型 1 北纬 0 南纬","locationtype":"定位类型 1 基站定位 0 gps定位","gpstime":"gps时间","baidulng":"百度经度","lat":"纬度","direction":"方向"}
     */

    private String lng;
    private String lat;
    private String baidulng;
    private String baidulat;
    private String gpstime;
    private int miles;
    private int speed;
    private int direction;
    private int lngtype;
    private int lattype;
    private int locationtype;
    private int num;
    private int gsm;
    private String descriptionToString;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBaidulng() {
        return baidulng;
    }

    public void setBaidulng(String baidulng) {
        this.baidulng = baidulng;
    }

    public String getBaidulat() {
        return baidulat;
    }

    public void setBaidulat(String baidulat) {
        this.baidulat = baidulat;
    }

    public String getGpstime() {
        return gpstime;
    }

    public void setGpstime(String gpstime) {
        this.gpstime = gpstime;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getLngtype() {
        return lngtype;
    }

    public void setLngtype(int lngtype) {
        this.lngtype = lngtype;
    }

    public int getLattype() {
        return lattype;
    }

    public void setLattype(int lattype) {
        this.lattype = lattype;
    }

    public int getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(int locationtype) {
        this.locationtype = locationtype;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getGsm() {
        return gsm;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
