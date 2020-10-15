package com.jkabe.app.box.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/10/13
 * @name:OrderBean
 */
public class OrderBean implements Serializable {

    /**
     * id : 1c4877dc41904010b233f663f241f0da
     * memberid : c773bf27d09d49999ac0dee5fc1b28b6
     * orderid : 2010131127110355897
     * goodNum : 5
     * goodMoney : 3695.0
     * receiveName : 申公豹
     * receiveAddress : 广东省深圳市南山区众冠时代广场A座1904
     * receiveMobile : 15919936559
     * message :
     * orderStatus : 1
     * payStatus : 1
     * payType : 0
     * postage : 0.0
     * payAmount : 0.0
     * ordertime : 2020-10-13T03:27:12.000Z
     * integral : 0
     * items : [{"id":"032f64d9a91f45afb28eda1b8b526d4c","orderid":"d640ee6476474002b0e152bdffe3b6e5","goodid":"2072930579c9442ba2c9f6a51ef2cfaf","title":"金铂316炒锅","smallImg":"http://img.jkabe.com/mallimages/26ae796883784fd68004906633e165b8cp.jpg","goodNumber":4,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"0600eb8eea194557aabd727984d0293d","orderid":"831a8086eec54e27b0d3bd0761f87670","goodid":"27e09d163a4c4a51988feb0e328a0c3a","title":"居安保车载灭火器","smallImg":"http://img.yuanjiawise.com/mallimages/7abe667867a34a45bc142c24aaccb24ecp.jpg","goodNumber":2,"originalPrice":0,"sellPrice":1,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"0f86162c167a4fcfbdc4372b3a209ba3","orderid":"cc4f2cccee78486ca0f288acb9d16c85","goodid":"2072930579c9442ba2c9f6a51ef2cfaf","title":"金铂316炒锅","smallImg":"http://img.jkabe.com/mallimages/26ae796883784fd68004906633e165b8cp.jpg","goodNumber":4,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"2297457e8c0e45e8be2abfd9a98252d3","orderid":"bc37c62a4a4e48bc91a4c257ed69d834","goodid":"403436392f2e48b98c323bf8a9718519","title":"金铂316煎炒锅","smallImg":"http://img.jkabe.com/mallimages/1962e2025dcc4216a718d19b89e80564cp.jpg","goodNumber":1,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"2a178cad6ef641bb9eda639c47b60350","orderid":"988f6d9a3af2453f93ec8b7a761e91ff","goodid":"2072930579c9442ba2c9f6a51ef2cfaf","title":"金铂316炒锅","smallImg":"http://img.jkabe.com/mallimages/26ae796883784fd68004906633e165b8cp.jpg","goodNumber":4,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"4612929eab514400a3a23c81ad840efd","orderid":"e35774b8d53647c687983bf2a8bc8473","goodid":"403436392f2e48b98c323bf8a9718519","title":"金铂316煎炒锅","smallImg":"http://img.jkabe.com/mallimages/1962e2025dcc4216a718d19b89e80564cp.jpg","goodNumber":1,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"4e6e770275b5472d90dd07da0853490d","orderid":"1c4877dc41904010b233f663f241f0da","goodid":"2072930579c9442ba2c9f6a51ef2cfaf","title":"金铂316炒锅","smallImg":"http://img.jkabe.com/mallimages/26ae796883784fd68004906633e165b8cp.jpg","goodNumber":4,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"59ce14351a204a459a6ae4a68821b2cb","orderid":"1c4877dc41904010b233f663f241f0da","goodid":"403436392f2e48b98c323bf8a9718519","title":"金铂316煎炒锅","smallImg":"http://img.jkabe.com/mallimages/1962e2025dcc4216a718d19b89e80564cp.jpg","goodNumber":1,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"847e59655b6f4c8d941c81e332c06b43","orderid":"988f6d9a3af2453f93ec8b7a761e91ff","goodid":"403436392f2e48b98c323bf8a9718519","title":"金铂316煎炒锅","smallImg":"http://img.jkabe.com/mallimages/1962e2025dcc4216a718d19b89e80564cp.jpg","goodNumber":1,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"a837a752f09f403cb4f1256d632d5152","orderid":"d640ee6476474002b0e152bdffe3b6e5","goodid":"403436392f2e48b98c323bf8a9718519","title":"金铂316煎炒锅","smallImg":"http://img.jkabe.com/mallimages/1962e2025dcc4216a718d19b89e80564cp.jpg","goodNumber":1,"originalPrice":0,"sellPrice":739,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"}]
     * descriptionToString : {"deletetime":"会员删除时间","orderid":"订单号","receiveAddress":"收货地址","orderStatus":"订单状态 1=待支付,2=已支付待发货,3=已发货,4=已确认收货,5=订单取消,6=退款申请,7=退款成功,8=订单已完成,9=退货申请,10=退货成功","expressorder":"快递单号","paytime":"支付时间","updateTime":"更新时间","message":"买家留言","ordertime":"下单时间","expresscompany":"快递公司","postage":"运费","receiveName":"收货人","payType":"支付方式 1=微信,2=支付宝,3=银行卡","payAmount":"支付金额","createTime":"创建时间","integral":"积分","goodMoney":"商品金额","confirmtime":"确认收货时间","id":"商品金额","sendtime":"发货时间","payStatus":"支付状态 1=待支付,2=已支付","memberid":"会员","goodNum":"商品件数","receiveMobile":"联系手机"}
     */

    private String id;
    private String memberid;
    private String orderid;
    private int goodNum;
    private double goodMoney;
    private String receiveName;
    private String receiveAddress;
    private String receiveMobile;
    private String message;
    private int orderStatus;
    private int payStatus;
    private int payType;
    private double postage;
    private double payAmount;
    private String ordertime;
    private int integral;
    private String descriptionToString;
    private List<ItemsBean> items;

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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public double getGoodMoney() {
        return goodMoney;
    }

    public void setGoodMoney(double goodMoney) {
        this.goodMoney = goodMoney;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public double getPostage() {
        return postage;
    }

    public void setPostage(double postage) {
        this.postage = postage;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Serializable{
        /**
         * id : 032f64d9a91f45afb28eda1b8b526d4c
         * orderid : d640ee6476474002b0e152bdffe3b6e5
         * goodid : 2072930579c9442ba2c9f6a51ef2cfaf
         * title : 金铂316炒锅
         * smallImg : http://img.jkabe.com/mallimages/26ae796883784fd68004906633e165b8cp.jpg
         * goodNumber : 4
         * originalPrice : 0.0
         * sellPrice : 739.0
         * integral : 0
         * descriptionToString : {"smallImg":"商品图片","originalPrice":"原始价格","orderid":"订单id","expressorder":"快递单号","sellPrice":"售卖价格","updateTime":"更新时间","goodid":"商品id","title":"商品标题","expresscompany":"快递公司","goodNumber":"商品件数","createTime":"创建时间","integral":"积分","id":"foreignKey"}
         */

        private String id;
        private String orderid;
        private String goodid;
        private String title;
        private String smallImg;
        private int goodNumber;
        private double originalPrice;
        private double sellPrice;
        private int integral;
        private String descriptionToString;
        private String expressorder;
        private String expresscompany;

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        private int orderStatus;

        public String getExpresscompanyname() {
            return expresscompanyname;
        }

        public void setExpresscompanyname(String expresscompanyname) {
            this.expresscompanyname = expresscompanyname;
        }

        private String expresscompanyname;

        public String getExpressorder() {
            return expressorder;
        }

        public void setExpressorder(String expressorder) {
            this.expressorder = expressorder;
        }

        public String getExpresscompany() {
            return expresscompany;
        }

        public void setExpresscompany(String expresscompany) {
            this.expresscompany = expresscompany;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
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

        public double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public double getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getDescriptionToString() {
            return descriptionToString;
        }

        public void setDescriptionToString(String descriptionToString) {
            this.descriptionToString = descriptionToString;
        }
    }
}
