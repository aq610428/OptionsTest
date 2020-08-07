package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/30
 * @name:AssetsBean
 */
public class AssetsBean implements Serializable {

    /**
     * id : 78151b2057c34e12ac848cc11272a769
     * memberId : b8b04cd5490044489586533aebef43a6
     * memberName : 18588246540
     * coinTypeId : 2
     * coinTypeName : BOX
     * state : 2
     * outAddress :
     * inAddress :
     * hashCode :
     * balance : 21.0
     * accountBalance : 0.0
     * serviceFee : 0.0
     * status : 3
     * completeTime : 2020-07-30T08:29:00.000Z
     * txId :
     * createTime : 2020-07-28T08:35:01.000Z
     * updateTime : 2020-07-30T08:29:04.000Z
     * descriptionToString : {"serviceFee":"服务费","memberName":"会员名称","completeTime":"到账时间","txId":"转账id","updateTime":"更新时间","inAddress":"到账钱包地址","outAddress":"转入钱包地址","balance":"交易额","createTime":"创建时间","coinTypeId":"币种类型","hashCode":"哈希值","id":"id","state":"交易类型 1=邀请激活奖励,2=参与挖矿奖励,3=团队奖励,4=充值,5=提现","accountBalance":"到账额度","coinTypeName":"币种类型名称","memberId":"会员","status":"交易状态 1=待审核,2=交易中,3=交易成功,4=交易失败"}
     * stringCreateTime : 2020-07-28 16:35:01
     * stringUpdateTime : 2020-07-30 16:29:04
     * stringCompleteTime : 2020-07-30 16:29:00
     */

    private String id;
    private String memberId;
    private String memberName;
    private String coinTypeId;
    private String coinTypeName;
    private int state;
    private String outAddress;
    private String inAddress;
    private String hashCode;
    private double balance;
    private double accountBalance;
    private double serviceFee;
    private int status;
    private String completeTime;
    private String txId;
    private String createTime;
    private String updateTime;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;
    private String stringCompleteTime;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOutAddress() {
        return outAddress;
    }

    public void setOutAddress(String outAddress) {
        this.outAddress = outAddress;
    }

    public String getInAddress() {
        return inAddress;
    }

    public void setInAddress(String inAddress) {
        this.inAddress = inAddress;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
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

    public String getStringCompleteTime() {
        return stringCompleteTime;
    }

    public void setStringCompleteTime(String stringCompleteTime) {
        this.stringCompleteTime = stringCompleteTime;
    }
}
