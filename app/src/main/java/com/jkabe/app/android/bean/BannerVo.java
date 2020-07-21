package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/6
 * @name:Bannear
 */
public class BannerVo implements Serializable {

    /**
     * id : 71ecbcc438554db0b8644735d5622481
     * advertName : 首页banner
     * filepath : http://img.jkabe.com/advertfile/3bab77e48bc646bc941958a42b496464.png
     * url : www.jkabe.com
     * remark : 首页
     * mallcolid : 0
     * inwardType : 0
     * inwardVal :
     * advertState : 1
     */

    private String id;
    private String advertName;
    private String filepath;
    private String url;
    private String remark;
    private int mallcolid;
    private int inwardType;
    private String inwardVal;
    private int advertState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getMallcolid() {
        return mallcolid;
    }

    public void setMallcolid(int mallcolid) {
        this.mallcolid = mallcolid;
    }

    public int getInwardType() {
        return inwardType;
    }

    public void setInwardType(int inwardType) {
        this.inwardType = inwardType;
    }

    public String getInwardVal() {
        return inwardVal;
    }

    public void setInwardVal(String inwardVal) {
        this.inwardVal = inwardVal;
    }

    public int getAdvertState() {
        return advertState;
    }

    public void setAdvertState(int advertState) {
        this.advertState = advertState;
    }
}
