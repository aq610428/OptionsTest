package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/15
 * @name:EarlyInfo
 */
public class EarlyInfo implements Serializable {

    /**
     * id : 70c58048f6044945897e3515fb9d20e5
     * memberid : 31fea859a3cd4e988e9ca030f562d39d
     * title : 熄火提醒
     * content : 车辆[粤BNB673] 已熄火停车提醒.
     * status : 0
     * type : 1
     * createTime : 2020-07-13T08:51:19.000Z
     * updateTime : null
     * lng : 114.04745400
     * lat : 22.625464999
     * carcard : 粤BNB673
     * address : 广东省深圳市宝安区民治大道214号;沙元埔大厦内,泊寓沙元埔公寓西131米
     * gpstime : 2020-07-13T08:51:13.000Z
     * descriptionToString : {"address":"定位地址","lng":"经度","carcard":"车牌","updateTime":"更新时间","title":"标题","type":"消息类型 0=系统消息,1=报警消息","content":"内容","createTime":"创建时间","id":"主键","gpstime":"定位时间","lat":"纬度","memberid":"用户id","status":"读取状态 0=未读,1=已读"}
     * stringCreateTime : 2020-07-13 16:51:19
     * stringUpdateTime :
     * stringgpstime : 2020-07-13 16:51:13
     */

    private String id;
    private String memberid;
    private String title;
    private String content;
    private int status;
    private int type;
    private String createTime;
    private Object updateTime;
    private String lng;
    private String lat;
    private String carcard;
    private String address;
    private String gpstime;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;
    private String stringgpstime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGpstime() {
        return gpstime;
    }

    public void setGpstime(String gpstime) {
        this.gpstime = gpstime;
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

    public String getStringgpstime() {
        return stringgpstime;
    }

    public void setStringgpstime(String stringgpstime) {
        this.stringgpstime = stringgpstime;
    }
}
