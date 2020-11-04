package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/11/4
 * @name:BoxVo
 */
public class BoxVo implements Serializable {

    /**
     * id : ff2e40dec89145709942b0f102f80d2b
     * memberId : b8b04cd5490044489586533aebef43a6
     * orderid : 2011041640430100421
     * amount : 500
     * ordertime : 2020-11-03T16:00:00.000Z
     * endtime : 2021-02-01T16:00:00.000Z
     * lv : 0.21
     * type : 1
     * state : 1
     * profitAmount : 0
     * isRedeem : 1
     * damagesAmount : 0
     * profitNum : 0
     * nextExceDate : 2020-12-03T16:00:00.000Z
     * num : 3
     * stringOrdertime : 2020-11-04 00:00:00
     * stringEndtime : 2021-02-02 00:00:00
     */

    private String id;
    private String memberId;
    private String orderid;
    private String amount;
    private String ordertime;
    private String endtime;
    private String lv;
    private String type;
    private String state;
    private String profitAmount;
    private String isRedeem;
    private String damagesAmount;
    private String profitNum;
    private String nextExceDate;
    private String num;
    private String stringOrdertime;
    private String stringEndtime;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    private String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(String profitAmount) {
        this.profitAmount = profitAmount;
    }

    public String getIsRedeem() {
        return isRedeem;
    }

    public void setIsRedeem(String isRedeem) {
        this.isRedeem = isRedeem;
    }

    public String getDamagesAmount() {
        return damagesAmount;
    }

    public void setDamagesAmount(String damagesAmount) {
        this.damagesAmount = damagesAmount;
    }

    public String getProfitNum() {
        return profitNum;
    }

    public void setProfitNum(String profitNum) {
        this.profitNum = profitNum;
    }

    public String getNextExceDate() {
        return nextExceDate;
    }

    public void setNextExceDate(String nextExceDate) {
        this.nextExceDate = nextExceDate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStringOrdertime() {
        return stringOrdertime;
    }

    public void setStringOrdertime(String stringOrdertime) {
        this.stringOrdertime = stringOrdertime;
    }

    public String getStringEndtime() {
        return stringEndtime;
    }

    public void setStringEndtime(String stringEndtime) {
        this.stringEndtime = stringEndtime;
    }
}
