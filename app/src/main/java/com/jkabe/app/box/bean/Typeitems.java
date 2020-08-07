package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/30
 * @name:Typeitems
 */
public class Typeitems implements Serializable {

    /**
     * id : 6bcbda90e5d14d7fa3c7d26e7ea7f94f
     * coinTypeId : 1
     * coinTypeName : USDT
     * minBalance : 10.0
     * maxBalance : 200.0
     * serviceFee : 2.0
     * descriptionToString : {"serviceFee":"手续费","minBalance":"最小额度","coinTypeId":"币种类型","maxBalance":"最大额度","id":"foreignKey","coinTypeName":"币种类型名称"}
     */

    private String id;
    private String coinTypeId;
    private String coinTypeName;
    private double minBalance;
    private double maxBalance;
    private double serviceFee;
    private String descriptionToString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoinTypeId() {
        return coinTypeId;
    }

    public void setCoinTypeId(String coinTypeId) {
        this.coinTypeId = coinTypeId;
    }

    public String getCoinTypeName() {
        return coinTypeName;
    }

    public void setCoinTypeName(String coinTypeName) {
        this.coinTypeName = coinTypeName;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getMaxBalance() {
        return maxBalance;
    }

    public void setMaxBalance(double maxBalance) {
        this.maxBalance = maxBalance;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
