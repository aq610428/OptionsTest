package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/13
 * @name:Electronic
 */
public class Electronic implements Serializable {

    /**
     * id : 8ce20ea1d0a64edfbc1b940873068219
     * deviceid : 7dbfc1421e764dcdb7ddf5862d93038d
     * name : 15919931559
     * address : 广东省深圳市宝安区民治大道228号;民治小学天桥东62米
     * radius : 200
     * alarmstate : 0
     * createTime : 2020-06-30 17:50:17
     * lng : 114.047069
     * lat : 22.625832
     * descriptionToString : {"address":"围栏中心点","createTime":"创建时间","alarmstate":"报警状态","name":"围栏名称","id":"id","radius":"围栏半径","deviceid":"车辆id"}
     */

    private String id;
    private String deviceid;
    private String name;
    private String address;
    private int radius;
    private int alarmstate;
    private String createTime;
    private double lng;
    private double lat;
    private String descriptionToString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getAlarmstate() {
        return alarmstate;
    }

    public void setAlarmstate(int alarmstate) {
        this.alarmstate = alarmstate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
