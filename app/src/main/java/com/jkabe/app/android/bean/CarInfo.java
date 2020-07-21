package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/10
 * @name:CarInfo
 */
public class CarInfo implements Serializable {

    /**
     * id : 7dbfc1421e764dcdb7ddf5862d93038d
     * imeicode : 201902000673
     * simcode : 1440322101889
     * carcard : 粤BNB673
     * factory : 丰田
     * model : 凯美瑞
     * business : 广汽丰田
     * yearmodel : 2015款 2.0E D-4S 6AT 精英版
     * vinno : 12345678900000
     * engineno : 123456
     * enginetype :
     * tripcard :
     * bodycolor :
     * playcarddate :
     * initmileage : 15855.56
     * createTime : 2020-06-30 14:38:42
     * isreal : 2
     * descriptionToString : {"factory":"车品牌名称","engineno":"发动机号","tripcard":"行驶证","business":"厂商名称","yearmodel":"年款名称","bodycolor":"车身颜色","imeicode":"设备号","carcard":"车牌号","simcode":"sim卡号","vinno":"车架号","initmileage":"当前里程","createTime":"创建时间","model":"车型名称","id":"车辆id","playcarddate":"上牌日期","isreal":"是否已认证 0 未认证 1 已认证 2 审核中 3 审核失败","enginetype":"发动机类型"}
     */

    private String id;
    private String imeicode;
    private String simcode;
    private String carcard;
    private String factory;
    private String model;
    private String business;
    private String yearmodel;
    private String vinno;
    private String engineno;
    private String enginetype;
    private String tripcard;
    private String bodycolor;
    private String playcarddate;
    private String initmileage;
    private String createTime;
    private int isreal;
    private String descriptionToString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImeicode() {
        return imeicode;
    }

    public void setImeicode(String imeicode) {
        this.imeicode = imeicode;
    }

    public String getSimcode() {
        return simcode;
    }

    public void setSimcode(String simcode) {
        this.simcode = simcode;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getYearmodel() {
        return yearmodel;
    }

    public void setYearmodel(String yearmodel) {
        this.yearmodel = yearmodel;
    }

    public String getVinno() {
        return vinno;
    }

    public void setVinno(String vinno) {
        this.vinno = vinno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getEnginetype() {
        return enginetype;
    }

    public void setEnginetype(String enginetype) {
        this.enginetype = enginetype;
    }

    public String getTripcard() {
        return tripcard;
    }

    public void setTripcard(String tripcard) {
        this.tripcard = tripcard;
    }

    public String getBodycolor() {
        return bodycolor;
    }

    public void setBodycolor(String bodycolor) {
        this.bodycolor = bodycolor;
    }

    public String getPlaycarddate() {
        return playcarddate;
    }

    public void setPlaycarddate(String playcarddate) {
        this.playcarddate = playcarddate;
    }

    public String getInitmileage() {
        return initmileage;
    }

    public void setInitmileage(String initmileage) {
        this.initmileage = initmileage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsreal() {
        return isreal;
    }

    public void setIsreal(int isreal) {
        this.isreal = isreal;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
