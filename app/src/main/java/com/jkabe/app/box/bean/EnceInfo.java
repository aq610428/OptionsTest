package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/8/13
 * @name:EnceInfo
 */
public class EnceInfo implements Serializable {

    /**
     * id : 15c7c471e84a4088a25b7368593f12f5
     * hashcode : 0da004d19da3ced0b97fc4bfc25d6b2182c0342e9
     * blockid : 6
     * carcard : 浙B5033M
     * type : 1
     * shopname : 珠海欧亚
     * shopaddress : 浙江省宁波奉化中山东路588号
     * orderTime : 2013-03-03 13:50:46
     * finishTime : 2013-03-03 14:42:52
     * mileage : 33130
     * lastmileage : 0
     * faultRemark : 常规维修
     * repairPlan : 故障灯亮线路检修
     * partsReplace :
     * partsnum :
     * nextTime : null
     * nextMileage : 0
     * nextYearcheakTime : null
     * nextInsuranceTime : null
     * uploadtime : 2020-04-20 16:06:31
     * integral : 100
     * partnername : 笛威红盒子
     */

    private String id;
    private String hashcode;
    private int blockid;
    private String carcard;
    private int type;
    private String shopname;
    private String shopaddress;
    private String orderTime;
    private String finishTime;
    private int mileage;
    private int lastmileage;
    private String faultRemark;
    private String repairPlan;
    private String partsReplace;
    private String partsnum;
    private Object nextTime;
    private int nextMileage;
    private Object nextYearcheakTime;
    private Object nextInsuranceTime;
    private String uploadtime;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getLastmileage() {
        return lastmileage;
    }

    public void setLastmileage(int lastmileage) {
        this.lastmileage = lastmileage;
    }

    public String getFaultRemark() {
        return faultRemark;
    }

    public void setFaultRemark(String faultRemark) {
        this.faultRemark = faultRemark;
    }

    public String getRepairPlan() {
        return repairPlan;
    }

    public void setRepairPlan(String repairPlan) {
        this.repairPlan = repairPlan;
    }

    public String getPartsReplace() {
        return partsReplace;
    }

    public void setPartsReplace(String partsReplace) {
        this.partsReplace = partsReplace;
    }

    public String getPartsnum() {
        return partsnum;
    }

    public void setPartsnum(String partsnum) {
        this.partsnum = partsnum;
    }

    public Object getNextTime() {
        return nextTime;
    }

    public void setNextTime(Object nextTime) {
        this.nextTime = nextTime;
    }

    public int getNextMileage() {
        return nextMileage;
    }

    public void setNextMileage(int nextMileage) {
        this.nextMileage = nextMileage;
    }

    public Object getNextYearcheakTime() {
        return nextYearcheakTime;
    }

    public void setNextYearcheakTime(Object nextYearcheakTime) {
        this.nextYearcheakTime = nextYearcheakTime;
    }

    public Object getNextInsuranceTime() {
        return nextInsuranceTime;
    }

    public void setNextInsuranceTime(Object nextInsuranceTime) {
        this.nextInsuranceTime = nextInsuranceTime;
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

    public String getPartnername() {
        return partnername;
    }

    public void setPartnername(String partnername) {
        this.partnername = partnername;
    }
}
