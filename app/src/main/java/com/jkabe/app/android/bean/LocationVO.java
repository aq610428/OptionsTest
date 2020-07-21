package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/17
 * @name:LocationVO
 */
public class LocationVO implements Serializable {
    private String lat;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getGpstime() {
        return gpstime;
    }

    public void setGpstime(String gpstime) {
        this.gpstime = gpstime;
    }

    public String getSignaltime() {
        return signaltime;
    }

    public void setSignaltime(String signaltime) {
        this.signaltime = signaltime;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getAcc() {
        return acc;
    }

    public void setAcc(Integer acc) {
        this.acc = acc;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGms() {
        return gms;
    }

    public void setGms(Integer gms) {
        this.gms = gms;
    }

    public String getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(String locationtype) {
        this.locationtype = locationtype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getObdtime() {
        return obdtime;
    }

    public void setObdtime(String obdtime) {
        this.obdtime = obdtime;
    }

    private String lng;
    private String gpstime;
    private String signaltime;
    private Integer speed;
    private Integer direction;
    private Integer mileage;
    private Integer acc;
    private Integer num;
    private Integer gms;
    private String locationtype;
    private String address;
    private String obdtime;


}
