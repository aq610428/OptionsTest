package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/15
 * @name:Travrt
 */
public class Travrt implements Serializable {

    /**
     * startlng : 114.035506
     * startlat : 22.622717
     * startbaidulng : 114.04712499
     * startbaidulat : 22.625780999
     * startaddress : 广东省深圳市宝安区民治大道224号;沙元埔大厦内,泊寓沙元埔公寓西165米
     * endlng : 114.035833
     * endlat : 22.622405
     * endbaidulng : 114.04745400
     * endbaidulat : 22.625464999
     * stoptime : 551407
     * endaddress : 广东省深圳市宝安区民治大道214号;沙元埔大厦内,泊寓沙元埔公寓西131米
     * starttime : 2020-07-13 16:47:59
     * endtime : 2020-07-13 16:51:13
     * triptime : 192
     * tripoil : 143
     * tripmileage : 135
     * maxspeed : 12
     * accecount : 0
     * dececount : 0
     * idletime : 93
     * sharpturncount : 0
     * descriptionToString : {"startlat":"行程开始纬度","startbaidulng":"行程开始百度经度","endbaidulat":"行程结束百度纬度","accecount":"急加速次数","maxspeed":"最高速度 （单位：KM/H）","startaddress":"行程开始地址","endtime":"行程结束时间","starttime":"行程开始时间","startbaidulat":"行程开始百度纬度","stoptime":"停留时间(单位:秒)","triptime":"行程时长(单位:秒)","endlat":"行程结束纬度","endaddress":"行程结束地址","dececount":"急减速次数","tripoil":"行程耗油量 （单位：ml","tripmileage":"行程里程 （单位：m）","idletime":"怠速的时间 单位：秒","sharpturncount":"急转弯次数","endlng":"行程结束经度","endbaidulng":"行程结束百度经度","startlng":"行程开始经度"}
     */

    private String startlng;
    private String startlat;
    private String startbaidulng;
    private String startbaidulat;
    private String startaddress;
    private String endlng;
    private String endlat;
    private String endbaidulng;
    private String endbaidulat;
    private int stoptime;
    private String endaddress;
    private String starttime;
    private String endtime;
    private String triptime;
    private String tripoil;
    private String tripmileage;
    private String maxspeed;
    private int accecount;
    private int dececount;
    private int idletime;
    private int sharpturncount;
    private String descriptionToString;

    public String getStartlng() {
        return startlng;
    }

    public void setStartlng(String startlng) {
        this.startlng = startlng;
    }

    public String getStartlat() {
        return startlat;
    }

    public void setStartlat(String startlat) {
        this.startlat = startlat;
    }

    public String getStartbaidulng() {
        return startbaidulng;
    }

    public void setStartbaidulng(String startbaidulng) {
        this.startbaidulng = startbaidulng;
    }

    public String getStartbaidulat() {
        return startbaidulat;
    }

    public void setStartbaidulat(String startbaidulat) {
        this.startbaidulat = startbaidulat;
    }

    public String getStartaddress() {
        return startaddress;
    }

    public void setStartaddress(String startaddress) {
        this.startaddress = startaddress;
    }

    public String getEndlng() {
        return endlng;
    }

    public void setEndlng(String endlng) {
        this.endlng = endlng;
    }

    public String getEndlat() {
        return endlat;
    }

    public void setEndlat(String endlat) {
        this.endlat = endlat;
    }

    public String getEndbaidulng() {
        return endbaidulng;
    }

    public void setEndbaidulng(String endbaidulng) {
        this.endbaidulng = endbaidulng;
    }

    public String getEndbaidulat() {
        return endbaidulat;
    }

    public void setEndbaidulat(String endbaidulat) {
        this.endbaidulat = endbaidulat;
    }

    public int getStoptime() {
        return stoptime;
    }

    public void setStoptime(int stoptime) {
        this.stoptime = stoptime;
    }

    public String getEndaddress() {
        return endaddress;
    }

    public void setEndaddress(String endaddress) {
        this.endaddress = endaddress;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTriptime() {
        return triptime;
    }

    public void setTriptime(String triptime) {
        this.triptime = triptime;
    }

    public String getTripoil() {
        return tripoil;
    }

    public void setTripoil(String tripoil) {
        this.tripoil = tripoil;
    }

    public String getTripmileage() {
        return tripmileage;
    }

    public void setTripmileage(String tripmileage) {
        this.tripmileage = tripmileage;
    }

    public String getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(String maxspeed) {
        this.maxspeed = maxspeed;
    }

    public int getAccecount() {
        return accecount;
    }

    public void setAccecount(int accecount) {
        this.accecount = accecount;
    }

    public int getDececount() {
        return dececount;
    }

    public void setDececount(int dececount) {
        this.dececount = dececount;
    }

    public int getIdletime() {
        return idletime;
    }

    public void setIdletime(int idletime) {
        this.idletime = idletime;
    }

    public int getSharpturncount() {
        return sharpturncount;
    }

    public void setSharpturncount(int sharpturncount) {
        this.sharpturncount = sharpturncount;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
