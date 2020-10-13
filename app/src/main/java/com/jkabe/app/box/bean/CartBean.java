package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/10/12
 * @name:CartBean
 */
public class CartBean implements Serializable {

    /**
     * id : 1e10dfd8aa5c477dbf61405cb96e5db8
     * goodid : 27e09d163a4c4a51988feb0e328a0c3a
     * title : 居安保车载灭火器
     * smallImg : http://img.yuanjiawise.com/mallimages/7abe667867a34a45bc142c24aaccb24ecp.jpg
     * goodNumber : 2
     * sellPrice : 1.0
     * memberid : c773bf27d09d49999ac0dee5fc1b28b6
     * createTime : 2020-10-10T03:37:39.000Z
     * state : 0
     * stringCreateTime : 2020-10-10 11:37:39
     * descriptionToString : {"smallImg":"商品图片","createTime":"创建时间","sellPrice":"售卖价格","updateTime":"更新时间","id":"foreignKey","goodid":"商品id","state":"商品状态 0=正常,1=失效","title":"商品标题","goodNumber":"商品件数","memberid":"会员"}
     */

    private String id;
    private String goodid;
    private String title;
    private String smallImg;
    private int goodNumber;
    private double sellPrice;
    private String memberid;
    private String createTime;
    private int state;
    private String stringCreateTime;
    private String descriptionToString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
}
