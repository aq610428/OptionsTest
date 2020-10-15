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
     * aliPayString : alipay_sdk=alipay-sdk-java-3.6.0.ALL&app_id=2021001198636188&biz_content=%7B%22body%22%3A%22%E5%8D%A1%E8%B4%9D%E8%BD%A6%E5%AE%9D%E5%95%86%E5%9F%8E%E8%B4%AD%E4%B9%B0%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%222010151557290206880%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%8D%A1%E8%B4%9D%E8%BD%A6%E5%AE%9D%E8%B4%AD%E4%B9%B0%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.1%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fadmin.jkabe.com%2Fasyn%2FgetAliPayData&sign=0E2WrIHt5dUvWy52VrpU1mas2O%2F5KcKjvPs7PYPk7Tje1iAU36p%2B47pj4cn1cA%2B3MvKh%2BEWaIAolDBtevQkGCo48YadNTDcBtAO02D9GIdnqiR2bbNeeuvmH1bW8AdHdqhjSca1HcfpOJNM2c6OrZSg0FDieDKdQ8kG1i9ajNjcg%2BpQYKx3RmvjtiflJl49ZbjiCzBi5vWO89lAsmUseb8k40zk8737asSD0L2UNDhndpENRcaGBCvu6gyzLg2vGm6oH9xZvZzXyJR5BAop%2BOy3xNv3Y7NbXpMOGss8CQNlDEJVoV6K2mNk3a9DlJsKk6jLGOzB1qAyrPOedIO8AmA%3D%3D&sign_type=RSA2×tamp=2020-10-15+15%3A57%3A29&version=1.0
     * orderid : 5c36a275901d4ef585aaa949b490e9a2
     * payMap : {"appid":"wxdebf49e4963895e1","noncestr":"8458e0772c0e428fb5a8e012544976b0","package":"Sign=WXPay","partnerid":"1559301821","prepayid":"wx1515572925714911cdfa26c80515e10000","sign":"7BF24A075C4AFEEAB798EA1DA1121D03","timestamp":"1602748649"}
     */

    private String aliPayString;
    private String orderid;
    private PayMapBean payMap;

    public String getAliPayString() {
        return aliPayString;
    }

    public void setAliPayString(String aliPayString) {
        this.aliPayString = aliPayString;
    }

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

    public static class PayMapBean implements Serializable{
        /**
         * appid : wxdebf49e4963895e1
         * noncestr : 8458e0772c0e428fb5a8e012544976b0
         * package : Sign=WXPay
         * partnerid : 1559301821
         * prepayid : wx1515572925714911cdfa26c80515e10000
         * sign : 7BF24A075C4AFEEAB798EA1DA1121D03
         * timestamp : 1602748649
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
