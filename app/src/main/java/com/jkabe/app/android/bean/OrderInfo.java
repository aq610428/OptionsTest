package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/10
 * @name:OrderInfo
 */
public class OrderInfo implements Serializable {

    /**
     * id : 17a53850d1d04eef9cd58d07d703d315
     * storeId : 17d86041656c4c66b3cbe7901416e631
     * storeName : 卡贝集团
     * orderTime : 2020-07-04 11:21
     * project : 保养
     * carcard : 鄂NA4275
     * mobile : 15919931559
     * status : 4
     * memberId : 31fea859a3cd4e988e9ca030f562d39d
     * createTime : 2020-06-29T03:23:46.000Z
     * updateTime : 2020-06-29T03:24:34.000Z
     * storeMemberId : 5e999c88269b4c02b5486a9dd7344878
     * billId : c8abd24e49d444919ceef219c5f3f2ca
     * storeAddress : 广东省深圳市南区区西丽街道
     * storePhone : 18682260854
     * storeLogo : http://img.jkabe.com/storelogo/80aaaf9d363d4c1494ccfb70e3710073.jpg
     * integralFlag : 1
     * descriptionToString : {"orderTime":"预约时间","createTime":"创建时间","carcard":"车牌","mobile":"联系手机","project":"预约项目","storeName":"门店名称","updateTime":"更新时间","id":"id","storeId":"门店","status":"预约状态 0=取消预约,1=预约申请中,2=接受预约,3=预约完成","memberId":"用户id"}
     * stringCreateTime : 2020-06-29 11:23:46
     * stringUpdateTime : 2020-06-29 11:24:34
     */

    private String id;
    private String storeId;
    private String storeName;
    private String orderTime;
    private String project;
    private String carcard;
    private String mobile;
    private int status;
    private String memberId;
    private String createTime;
    private String updateTime;
    private String storeMemberId;
    private String billId;
    private String storeAddress;
    private String storePhone;
    private String storeLogo;
    private int integralFlag;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getStoreMemberId() {
        return storeMemberId;
    }

    public void setStoreMemberId(String storeMemberId) {
        this.storeMemberId = storeMemberId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public int getIntegralFlag() {
        return integralFlag;
    }

    public void setIntegralFlag(int integralFlag) {
        this.integralFlag = integralFlag;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }

    public String getStringCreateTime() {
        return stringCreateTime;
    }

    public void setStringCreateTime(String stringCreateTime) {
        this.stringCreateTime = stringCreateTime;
    }

    public String getStringUpdateTime() {
        return stringUpdateTime;
    }

    public void setStringUpdateTime(String stringUpdateTime) {
        this.stringUpdateTime = stringUpdateTime;
    }
}
