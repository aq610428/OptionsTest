package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/9/8
 * @name:icadBean
 */
public class icadBean implements Serializable {

    /**
     * data : {"status_desc":"正常","open_time":"2018-08-03","month_done_usage":0,"active_time":"2020-06-01","done_usage":0,"surplus_usage":102400,"iccid":"89860432011890771216","expire_date":"2021-05-31","balance":0,"sim":"1440322071216","surplus_period":265,"status":1,"amount_usage":102400}
     * state : 1
     */

    private DataBean data;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean implements Serializable{
        /**
         * status_desc : 正常
         * open_time : 2018-08-03
         * month_done_usage : 0.0
         * active_time : 2020-06-01
         * done_usage : 0.0
         * surplus_usage : 102400.0
         * iccid : 89860432011890771216
         * expire_date : 2021-05-31
         * balance : 0.0
         * sim : 1440322071216
         * surplus_period : 265
         * status : 1
         * amount_usage : 102400.0
         */

        private String status_desc;
        private String open_time;
        private double month_done_usage;
        private String active_time;
        private double done_usage;
        private double surplus_usage;
        private String iccid;
        private String expire_date;
        private double balance;
        private String sim;
        private int surplus_period;
        private int status;
        private double amount_usage;

        public String getStatus_desc() {
            return status_desc;
        }

        public void setStatus_desc(String status_desc) {
            this.status_desc = status_desc;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public double getMonth_done_usage() {
            return month_done_usage;
        }

        public void setMonth_done_usage(double month_done_usage) {
            this.month_done_usage = month_done_usage;
        }

        public String getActive_time() {
            return active_time;
        }

        public void setActive_time(String active_time) {
            this.active_time = active_time;
        }

        public double getDone_usage() {
            return done_usage;
        }

        public void setDone_usage(double done_usage) {
            this.done_usage = done_usage;
        }

        public double getSurplus_usage() {
            return surplus_usage;
        }

        public void setSurplus_usage(double surplus_usage) {
            this.surplus_usage = surplus_usage;
        }

        public String getIccid() {
            return iccid;
        }

        public void setIccid(String iccid) {
            this.iccid = iccid;
        }

        public String getExpire_date() {
            return expire_date;
        }

        public void setExpire_date(String expire_date) {
            this.expire_date = expire_date;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getSim() {
            return sim;
        }

        public void setSim(String sim) {
            this.sim = sim;
        }

        public int getSurplus_period() {
            return surplus_period;
        }

        public void setSurplus_period(int surplus_period) {
            this.surplus_period = surplus_period;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getAmount_usage() {
            return amount_usage;
        }

        public void setAmount_usage(double amount_usage) {
            this.amount_usage = amount_usage;
        }
    }
}
