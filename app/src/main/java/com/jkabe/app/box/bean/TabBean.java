package com.jkabe.app.box.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/11/4
 * @name:TabBean
 */
public class TabBean implements Serializable {

    /**
     * manage_amount : 19000
     * profit_amount : 40
     * manage_num : 15
     * items : [{"min":"500","max":"100000","zq":"36个月","lv":"500%","type":5},{"min":"500","max":"100000","zq":"24个月","lv":"240%","type":4},{"min":"500","max":"100000","zq":"12个月","lv":"108%","type":3},{"min":"500","max":"100000","zq":"6个月","lv":"48%","type":2},{"min":"500","max":"100000","zq":"3个月","lv":"21%","type":1}]
     */

    private int manage_amount;// 买入总额
    private int profit_amount;//累计收益
    private int manage_num;//参与人数
    private List<ItemsBean> items;

    public int getManage_amount() {
        return manage_amount;
    }

    public void setManage_amount(int manage_amount) {
        this.manage_amount = manage_amount;
    }

    public int getProfit_amount() {
        return profit_amount;
    }

    public void setProfit_amount(int profit_amount) {
        this.profit_amount = profit_amount;
    }

    public int getManage_num() {
        return manage_num;
    }

    public void setManage_num(int manage_num) {
        this.manage_num = manage_num;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Serializable{
        /**
         * min : 500
         * max : 100000
         * zq : 36个月
         * lv : 500%
         * type : 5
         */

        private String min;
        private String max;
        private String zq;
        private String lv;
        private int type;

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getZq() {
            return zq;
        }

        public void setZq(String zq) {
            this.zq = zq;
        }

        public String getLv() {
            return lv;
        }

        public void setLv(String lv) {
            this.lv = lv;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
