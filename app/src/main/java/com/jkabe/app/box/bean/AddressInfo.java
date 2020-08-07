package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/30
 * @name:AddressInfo
 */
public class AddressInfo implements Serializable {

    /**
     * id : 5801d326cdeb471cb72ed1406245c5a8
     * coinTypeId : 1
     * coinTypeName : USDT
     * address : 0xa2fb523adfea1d000c1f487586264d3b39ca0001
     * memberId : b8b04cd5490044489586533aebef43a6
     * memberName : 18588246540
     * createTime : 2020-07-30T03:30:58.000Z
     * descriptionToString : {"address":"钱包地址","createTime":"创建时间","coinTypeId":"币种类型","memberName":"会员名称","id":"foreignKey","coinTypeName":"币种类型名称","memberId":"会员"}
     * stringCreateTime : 2020-07-30 11:30:58
     */

    private String id;
    private String coinTypeId;
    private String coinTypeName;
    private String address;
    private String memberId;
    private String memberName;
    private String createTime;
    private String descriptionToString;
    private String stringCreateTime;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
