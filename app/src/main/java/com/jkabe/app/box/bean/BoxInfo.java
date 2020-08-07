package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/30
 * @name:BoxInfo
 */
public class BoxInfo implements Serializable {

    /**
     * id : f56b39c965854e539a13d14d0f1cc327
     * memberId : b8b04cd5490044489586533aebef43a6
     * usdt : 3.0
     * box : 21.0
     * boxPrice : 1.0
     * usdtPrice : 7.0
     * sumdate : 2020-07-30
     * descriptionToString : {"createTime":"创建时间","price":"BOX价格","num":"挖矿期数","usdt":"USDT额度","memberName":"会员名称","box":"BOX额度","updateTime":"更新时间","id":"foreignKey","sumdate":"挖矿日期","memberId":"会员"}
     */

    private String id;
    private String memberId;
    private double usdt;
    private double box;
    private double boxPrice;
    private double usdtPrice;
    private String sumdate;
    private String descriptionToString;

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

    public double getUsdt() {
        return usdt;
    }

    public void setUsdt(double usdt) {
        this.usdt = usdt;
    }

    public double getBox() {
        return box;
    }

    public void setBox(double box) {
        this.box = box;
    }

    public double getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(double boxPrice) {
        this.boxPrice = boxPrice;
    }

    public double getUsdtPrice() {
        return usdtPrice;
    }

    public void setUsdtPrice(double usdtPrice) {
        this.usdtPrice = usdtPrice;
    }

    public String getSumdate() {
        return sumdate;
    }

    public void setSumdate(String sumdate) {
        this.sumdate = sumdate;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
