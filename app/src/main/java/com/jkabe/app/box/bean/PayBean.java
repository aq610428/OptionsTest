package com.jkabe.app.box.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/10/9
 * @name:PayBean
 */
public class PayBean implements Serializable{


    /**
     * orderid : 2010131059421055649
     * payMap : {"appid":"wxdebf49e4963895e1","noncestr":"4d0054b806a44a1db1f1c7a59e908c05","package":"Sign=WXPay","partnerid":"1559301821","prepayid":"wx131059429905259a1e8a13c12a78a10000","sign":"16C8E3DE12A741EB42EBADE521EC21EE","timestamp":"1602557983"}
     */

    private String orderid;
    private PayMapBean payMap;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public PayMapBean getPayMap() {
        return payMap;
    }

    public void setPayMap(PayMapBean payMap) {
        this.payMap = payMap;
    }

    public static class PayMapBean implements Serializable {
        /**
         * appid : wxdebf49e4963895e1
         * noncestr : 4d0054b806a44a1db1f1c7a59e908c05
         * package : Sign=WXPay
         * partnerid : 1559301821
         * prepayid : wx131059429905259a1e8a13c12a78a10000
         * sign : 16C8E3DE12A741EB42EBADE521EC21EE
         * timestamp : 1602557983
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;
        private String timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
