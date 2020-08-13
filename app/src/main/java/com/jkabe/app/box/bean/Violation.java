package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/8/13
 * @name:Violation
 */
public class Violation implements Serializable {

    /**
     * id : 53cd9e3850a34187a552f698da6752f0
     * hashcode : dec80cd7c68a728da1acd1a4703c0bb80298c4986
     * blockid : 2
     * carcard : 粤BT2999
     * vioCode : 1205
     * vioAction : 遇行人正在通过人行横道时未停车让行的
     * vioTime : 2019-11-26 11:30:45
     * vioAddress : 深圳市南山区西丽留仙大道北侧坪山路口东侧(近大学城南门)
     * vioPrice : 500.0
     * vioScore : 3
     * vioCity : 1
     * vioState : 0
     * vioOrgan :
     * vioHandletime : 2019-11-29 11:30:45
     * vioPerson : 佚名
     * vioElecno :
     * vioHandleno :
     * uploadtime : 2019-12-02 11:24:40
     * integral : 1000
     */

    private String id;
    private String hashcode;
    private int blockid;
    private String carcard;
    private String vioCode;
    private String vioAction;
    private String vioTime;
    private String vioAddress;
    private double vioPrice;
    private int vioScore;
    private String vioCity;
    private int vioState;
    private String vioOrgan;
    private String vioHandletime;
    private String vioPerson;
    private String vioElecno;
    private String vioHandleno;
    private String uploadtime;
    private int integral;

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

    public String getVioCode() {
        return vioCode;
    }

    public void setVioCode(String vioCode) {
        this.vioCode = vioCode;
    }

    public String getVioAction() {
        return vioAction;
    }

    public void setVioAction(String vioAction) {
        this.vioAction = vioAction;
    }

    public String getVioTime() {
        return vioTime;
    }

    public void setVioTime(String vioTime) {
        this.vioTime = vioTime;
    }

    public String getVioAddress() {
        return vioAddress;
    }

    public void setVioAddress(String vioAddress) {
        this.vioAddress = vioAddress;
    }

    public double getVioPrice() {
        return vioPrice;
    }

    public void setVioPrice(double vioPrice) {
        this.vioPrice = vioPrice;
    }

    public int getVioScore() {
        return vioScore;
    }

    public void setVioScore(int vioScore) {
        this.vioScore = vioScore;
    }

    public String getVioCity() {
        return vioCity;
    }

    public void setVioCity(String vioCity) {
        this.vioCity = vioCity;
    }

    public int getVioState() {
        return vioState;
    }

    public void setVioState(int vioState) {
        this.vioState = vioState;
    }

    public String getVioOrgan() {
        return vioOrgan;
    }

    public void setVioOrgan(String vioOrgan) {
        this.vioOrgan = vioOrgan;
    }

    public String getVioHandletime() {
        return vioHandletime;
    }

    public void setVioHandletime(String vioHandletime) {
        this.vioHandletime = vioHandletime;
    }

    public String getVioPerson() {
        return vioPerson;
    }

    public void setVioPerson(String vioPerson) {
        this.vioPerson = vioPerson;
    }

    public String getVioElecno() {
        return vioElecno;
    }

    public void setVioElecno(String vioElecno) {
        this.vioElecno = vioElecno;
    }

    public String getVioHandleno() {
        return vioHandleno;
    }

    public void setVioHandleno(String vioHandleno) {
        this.vioHandleno = vioHandleno;
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
}
