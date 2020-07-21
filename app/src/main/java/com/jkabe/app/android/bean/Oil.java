package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/14
 * @name:Oil
 */
public class Oil implements Serializable {

    /**
     * idling : 0.0
     * pkspeedavg : 27
     * pkidling : 13.5
     * speedavg : 32
     * oils : 42.86
     * avgoil : 11.54
     * pkavgoil : 12.04
     * acce : 0.1
     * dece : 0.2
     * mileage : 371
     * pkacce : 0.5
     * pkdece : 0.7
     *
     * avgoil ： 平均油耗，oils： 总油耗，speedavg：平均速度，mileage：里程，acce：急加速次数，dece：急减速次数
     *
     */

    private String idling;
    private int pkspeedavg;
    private double pkidling;
    private int speedavg;
    private String oils;
    private String avgoil;
    private String pkavgoil;
    private String acce;
    private String dece;
    private int mileage;
    private double pkacce;
    private double pkdece;

    public String getIdling() {
        return idling;
    }

    public void setIdling(String idling) {
        this.idling = idling;
    }

    public int getPkspeedavg() {
        return pkspeedavg;
    }

    public void setPkspeedavg(int pkspeedavg) {
        this.pkspeedavg = pkspeedavg;
    }

    public double getPkidling() {
        return pkidling;
    }

    public void setPkidling(double pkidling) {
        this.pkidling = pkidling;
    }

    public int getSpeedavg() {
        return speedavg;
    }

    public void setSpeedavg(int speedavg) {
        this.speedavg = speedavg;
    }

    public String getOils() {
        return oils;
    }

    public void setOils(String oils) {
        this.oils = oils;
    }

    public String getAvgoil() {
        return avgoil;
    }

    public void setAvgoil(String avgoil) {
        this.avgoil = avgoil;
    }

    public String getPkavgoil() {
        return pkavgoil;
    }

    public void setPkavgoil(String pkavgoil) {
        this.pkavgoil = pkavgoil;
    }

    public String getAcce() {
        return acce;
    }

    public void setAcce(String acce) {
        this.acce = acce;
    }

    public String getDece() {
        return dece;
    }

    public void setDece(String dece) {
        this.dece = dece;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPkacce() {
        return pkacce;
    }

    public void setPkacce(double pkacce) {
        this.pkacce = pkacce;
    }

    public double getPkdece() {
        return pkdece;
    }

    public void setPkdece(double pkdece) {
        this.pkdece = pkdece;
    }
}
