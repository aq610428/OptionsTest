package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/30
 * @name:Usdinfo
 */
public class Usdinfo implements Serializable {

    /**
     * id :
     * coinTypeId : 1
     * coinTypeName : USDT
     * memberId : b8b04cd5490044489586533aebef43a6
     * memberName : null
     * userable : 0.0
     * freeze : 0.0
     * createTime : null
     * updateTime : null
     * descriptionToString : {"freeze":"冻结额度","createTime":"创建时间","coinTypeId":"币种类型","userable":"可用额度","memberName":"会员名称","updateTime":"更新时间","id":"foreignKey","coinTypeName":"币种类型名称","memberId":"会员"}
     * stringCreateTime :
     * stringUpdateTime :
     */

    private String id;
    private String coinTypeId;
    private String coinTypeName;
    private String memberId;
    private Object memberName;
    private double userable;
    private double freeze;
    private Object createTime;
    private Object updateTime;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Object getMemberName() {
        return memberName;
    }

    public void setMemberName(Object memberName) {
        this.memberName = memberName;
    }

    public double getUserable() {
        return userable;
    }

    public void setUserable(double userable) {
        this.userable = userable;
    }

    public double getFreeze() {
        return freeze;
    }

    public void setFreeze(double freeze) {
        this.freeze = freeze;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
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
}
