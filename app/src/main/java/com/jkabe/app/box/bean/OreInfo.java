package com.jkabe.app.box.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/8/18
 * @name:OreInfo
 */
public class OreInfo implements Serializable {

    /**
     * fromcname : BOX
     * img : http://img.jkabe.com/coins/box.png
     * fromcid : 255
     * count_pianyi : 6
     * kaipan_price : 0.1500000000
     * trans_xiaoshu : 5
     * price_min : 0.001000000000000000
     * order_min : 10.000000000000000000
     * buy_fee : 0.0025000000
     * tingpan_time : 0
     * datatype : 1
     * id : 29
     * min_trans : 5.0000000000000000000
     * order_max : 999999.000000000000000000
     * trans_type : 1
     * kaipan_time : 0
     * createtime : 1597038933
     * min_order_price : 0.0000000000
     * price_pianyi : 0
     * tocname : USDT
     * istingpan : 1
     * tocid : 5
     * name : BOX/USDT
     * amount_xiaoshu : 4
     * updatetime : 1597039162
     * istrans : 1
     * quotation : {"amount":456209.8804,"high":0.2989,"vol":130843.40240622,"low":0.2681,"count":419,"ask":1,"coinname":"BOX_USDT","bid":1,"close":"0.286900000000000000","open":"0.293200000000000000"}
     * price_max : 0.000000000000000000
     * cny_price : 6.9314
     * status : 1
     * sell_fee : 0.0025000000
     */

    private String fromcname;
    private String img;
    private String fromcid;
    private String count_pianyi;
    private String kaipan_price;
    private String trans_xiaoshu;
    private String price_min;
    private String order_min;
    private String buy_fee;
    private String tingpan_time;
    private String datatype;
    private String id;
    private String min_trans;
    private String order_max;
    private String trans_type;
    private String kaipan_time;
    private String createtime;
    private String min_order_price;
    private String price_pianyi;
    private String tocname;
    private String istingpan;
    private String tocid;
    private String name;
    private String amount_xiaoshu;
    private String updatetime;
    private String istrans;
    private QuotationBean quotation;
    private String price_max;
    private String cny_price;
    private String status;
    private String sell_fee;

    public String getFromcname() {
        return fromcname;
    }

    public void setFromcname(String fromcname) {
        this.fromcname = fromcname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFromcid() {
        return fromcid;
    }

    public void setFromcid(String fromcid) {
        this.fromcid = fromcid;
    }

    public String getCount_pianyi() {
        return count_pianyi;
    }

    public void setCount_pianyi(String count_pianyi) {
        this.count_pianyi = count_pianyi;
    }

    public String getKaipan_price() {
        return kaipan_price;
    }

    public void setKaipan_price(String kaipan_price) {
        this.kaipan_price = kaipan_price;
    }

    public String getTrans_xiaoshu() {
        return trans_xiaoshu;
    }

    public void setTrans_xiaoshu(String trans_xiaoshu) {
        this.trans_xiaoshu = trans_xiaoshu;
    }

    public String getPrice_min() {
        return price_min;
    }

    public void setPrice_min(String price_min) {
        this.price_min = price_min;
    }

    public String getOrder_min() {
        return order_min;
    }

    public void setOrder_min(String order_min) {
        this.order_min = order_min;
    }

    public String getBuy_fee() {
        return buy_fee;
    }

    public void setBuy_fee(String buy_fee) {
        this.buy_fee = buy_fee;
    }

    public String getTingpan_time() {
        return tingpan_time;
    }

    public void setTingpan_time(String tingpan_time) {
        this.tingpan_time = tingpan_time;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMin_trans() {
        return min_trans;
    }

    public void setMin_trans(String min_trans) {
        this.min_trans = min_trans;
    }

    public String getOrder_max() {
        return order_max;
    }

    public void setOrder_max(String order_max) {
        this.order_max = order_max;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getKaipan_time() {
        return kaipan_time;
    }

    public void setKaipan_time(String kaipan_time) {
        this.kaipan_time = kaipan_time;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getMin_order_price() {
        return min_order_price;
    }

    public void setMin_order_price(String min_order_price) {
        this.min_order_price = min_order_price;
    }

    public String getPrice_pianyi() {
        return price_pianyi;
    }

    public void setPrice_pianyi(String price_pianyi) {
        this.price_pianyi = price_pianyi;
    }

    public String getTocname() {
        return tocname;
    }

    public void setTocname(String tocname) {
        this.tocname = tocname;
    }

    public String getIstingpan() {
        return istingpan;
    }

    public void setIstingpan(String istingpan) {
        this.istingpan = istingpan;
    }

    public String getTocid() {
        return tocid;
    }

    public void setTocid(String tocid) {
        this.tocid = tocid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount_xiaoshu() {
        return amount_xiaoshu;
    }

    public void setAmount_xiaoshu(String amount_xiaoshu) {
        this.amount_xiaoshu = amount_xiaoshu;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getIstrans() {
        return istrans;
    }

    public void setIstrans(String istrans) {
        this.istrans = istrans;
    }

    public QuotationBean getQuotation() {
        return quotation;
    }

    public void setQuotation(QuotationBean quotation) {
        this.quotation = quotation;
    }

    public String getPrice_max() {
        return price_max;
    }

    public void setPrice_max(String price_max) {
        this.price_max = price_max;
    }

    public String getCny_price() {
        return cny_price;
    }

    public void setCny_price(String cny_price) {
        this.cny_price = cny_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSell_fee() {
        return sell_fee;
    }

    public void setSell_fee(String sell_fee) {
        this.sell_fee = sell_fee;
    }

    public static class QuotationBean {
        /**
         * amount : 456209.8804
         * high : 0.2989
         * vol : 130843.40240622
         * low : 0.2681
         * count : 419
         * ask : 1
         * coinname : BOX_USDT
         * bid : 1
         * close : 0.286900000000000000
         * open : 0.293200000000000000
         */

        private double amount;
        private double high;
        private double vol;
        private double low;
        private int count;
        private int ask;
        private String coinname;
        private int bid;
        private String close;
        private String open;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getAsk() {
            return ask;
        }

        public void setAsk(int ask) {
            this.ask = ask;
        }

        public String getCoinname() {
            return coinname;
        }

        public void setCoinname(String coinname) {
            this.coinname = coinname;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getClose() {
            return close;
        }

        public void setClose(String close) {
            this.close = close;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }
    }
}
