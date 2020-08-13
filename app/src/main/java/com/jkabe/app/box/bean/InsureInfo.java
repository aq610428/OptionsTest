package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/8/13
 * @name:InsureInfo
 */
public class InsureInfo implements Serializable {

    /**
     * id : 2e9b0060962e4991ab2f4d89874565fc
     * hashcode : e59bc6c1c695c79cdb62bbcaa4b9a2e12b38a4b88
     * blockid : 2
     * carcard : 粤BT2999
     * vincode : LSVNV4BR2KN068569
     * enginecode : 101903
     * isuranceNo : 80510201450019901088
     * isuranceName : 第三者责任险
     * isuranceCompany : 平安保险深圳分公司
     * isuranceDetail : 本产品的第三者责任包含第三者人身伤亡责任、第三者财产损失责任及第三者医疗费用责任等风险保障。
     * isuranceStarttime : 2019-11-29
     * isuranceEndtime : 2020-11-29
     * uploadtime : 2019-12-02 10:40:17
     * integral : 1000
     */

    private String id;
    private String hashcode;
    private int blockid;
    private String carcard;
    private String vincode;
    private String enginecode;
    private String isuranceNo;
    private String isuranceName;
    private String isuranceCompany;
    private String isuranceDetail;
    private String isuranceStarttime;
    private String isuranceEndtime;
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

    public String getVincode() {
        return vincode;
    }

    public void setVincode(String vincode) {
        this.vincode = vincode;
    }

    public String getEnginecode() {
        return enginecode;
    }

    public void setEnginecode(String enginecode) {
        this.enginecode = enginecode;
    }

    public String getIsuranceNo() {
        return isuranceNo;
    }

    public void setIsuranceNo(String isuranceNo) {
        this.isuranceNo = isuranceNo;
    }

    public String getIsuranceName() {
        return isuranceName;
    }

    public void setIsuranceName(String isuranceName) {
        this.isuranceName = isuranceName;
    }

    public String getIsuranceCompany() {
        return isuranceCompany;
    }

    public void setIsuranceCompany(String isuranceCompany) {
        this.isuranceCompany = isuranceCompany;
    }

    public String getIsuranceDetail() {
        return isuranceDetail;
    }

    public void setIsuranceDetail(String isuranceDetail) {
        this.isuranceDetail = isuranceDetail;
    }

    public String getIsuranceStarttime() {
        return isuranceStarttime;
    }

    public void setIsuranceStarttime(String isuranceStarttime) {
        this.isuranceStarttime = isuranceStarttime;
    }

    public String getIsuranceEndtime() {
        return isuranceEndtime;
    }

    public void setIsuranceEndtime(String isuranceEndtime) {
        this.isuranceEndtime = isuranceEndtime;
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
