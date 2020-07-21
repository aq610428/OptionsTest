package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/13
 * @name:CarVo
 */
public class CarVo implements Serializable {


    /**
     * locationInfoDescription : {"acc":"速度","address":"位置","lng":"经度","num":"卫星颗数","gms":"信号值","speed":"速度","signaltime":"信号时间","obdtime":"车况时间","locationtype":"定位类型","gpstime":"定位时间","lat":"纬度","direction":"方向","mileage":"总里程"}
     * cartype : 丰田凯美瑞
     * locationInfo : {"lat":"22.625464537268602","lng":"114.04745902087019","gpstime":"2020-07-1316:51:13","signaltime":"2020-07-1316:51:16","speed":0,"direction":137,"mileage":10387000,"acc":0,"num":9,"gms":21,"locationtype":"GPS+北斗","address":"广东省深圳市宝安区民治大道214号;沙元埔大厦内,泊寓沙元埔公寓西130米","obdtime":"2020-07-1316:51:13","descriptionToString":"{\"acc\":\"速度\",\"address\":\"位置\",\"lng\":\"经度\",\"num\":\"卫星颗数\",\"gms\":\"信号值\",\"speed\":\"速度\",\"signaltime\":\"信号时间\",\"obdtime\":\"车况时间\",\"locationtype\":\"定位类型\",\"gpstime\":\"定位时间\",\"lat\":\"纬度\",\"direction\":\"方向\",\"mileage\":\"总里程\"}"}
     * carcard : 粤BNB673
     * simcode : 1440322101889
     */

    private String locationInfoDescription;
    private String cartype;
    private LocationInfoBean locationInfo;
    private String carcard;
    private String simcode;

    public String getLocationInfoDescription() {
        return locationInfoDescription;
    }

    public void setLocationInfoDescription(String locationInfoDescription) {
        this.locationInfoDescription = locationInfoDescription;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public LocationInfoBean getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfoBean locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getSimcode() {
        return simcode;
    }

    public void setSimcode(String simcode) {
        this.simcode = simcode;
    }

    public static class LocationInfoBean implements Serializable{
        /**
         * lat : 22.625464537268602
         * lng : 114.04745902087019
         * gpstime : 2020-07-1316:51:13
         * signaltime : 2020-07-1316:51:16
         * speed : 0
         * direction : 137
         * mileage : 10387000
         * acc : 0
         * num : 9
         * gms : 21
         * locationtype : GPS+北斗
         * address : 广东省深圳市宝安区民治大道214号;沙元埔大厦内,泊寓沙元埔公寓西130米
         * obdtime : 2020-07-1316:51:13
         * descriptionToString : {"acc":"速度","address":"位置","lng":"经度","num":"卫星颗数","gms":"信号值","speed":"速度","signaltime":"信号时间","obdtime":"车况时间","locationtype":"定位类型","gpstime":"定位时间","lat":"纬度","direction":"方向","mileage":"总里程"}
         */

        private String lat;
        private String lng;
        private String gpstime;
        private String signaltime;
        private int speed;
        private int direction;
        private int mileage;
        private int acc;
        private int num;
        private int gms;
        private String locationtype;
        private String address;
        private String obdtime;
        private String descriptionToString;

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

        public int getMileage() {
            return mileage;
        }

        public void setMileage(int mileage) {
            this.mileage = mileage;
        }

        public int getAcc() {
            return acc;
        }

        public void setAcc(int acc) {
            this.acc = acc;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getGms() {
            return gms;
        }

        public void setGms(int gms) {
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

        public String getDescriptionToString() {
            return descriptionToString;
        }

        public void setDescriptionToString(String descriptionToString) {
            this.descriptionToString = descriptionToString;
        }
    }
}
