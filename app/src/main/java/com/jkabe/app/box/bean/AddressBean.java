package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/10/12
 * @name:AddressBean
 */
public class AddressBean implements Serializable {

    /**
     * id : 6d9d4b9a1df44a1ba4f47362f06e2a27
     * receivename : 沈杰
     * mobile : 15919936559
     * province : 广东省
     * city : 深圳市
     * area : 南山区
     * address : 众冠时代广场A座1904
     * zipcode :
     * memberid : c773bf27d09d49999ac0dee5fc1b28b6
     * setdefault : 0
     * createTime : 2020-10-10T02:22:42.000Z
     * updateTime : 2020-10-10T02:24:43.000Z
     * stringCreateTime : 2020-10-10 10:22:42
     * descriptionToString : {"area":"区县","zipcode":"邮编","address":"详细地址","province":"省份","receivename":"姓名","city":"城市","createTime":"创建时间","setdefault":"设为默认","mobile":"手机","updateTime":"更新时间","id":"foreignKey","memberid":"会员"}
     * stringUpdateTime : 2020-10-10 10:24:43
     */

    private String id;
    private String receivename;
    private String mobile;
    private String province;
    private String city;
    private String area;
    private String address;
    private String zipcode;
    private String memberid;
    private int setdefault;
    private String createTime;
    private String updateTime;
    private String stringCreateTime;
    private String descriptionToString;
    private String stringUpdateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public int getSetdefault() {
        return setdefault;
    }

    public void setSetdefault(int setdefault) {
        this.setdefault = setdefault;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStringCreateTime() {
        return stringCreateTime;
    }

    public void setStringCreateTime(String stringCreateTime) {
        this.stringCreateTime = stringCreateTime;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }

    public String getStringUpdateTime() {
        return stringUpdateTime;
    }

    public void setStringUpdateTime(String stringUpdateTime) {
        this.stringUpdateTime = stringUpdateTime;
    }
}
