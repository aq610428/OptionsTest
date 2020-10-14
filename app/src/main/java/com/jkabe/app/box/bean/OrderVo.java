package com.jkabe.app.box.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/10/14
 * @name:OrderVo
 */
public class OrderVo implements Serializable {

    /**
     * gooditems : [{"id":"ba03c192b9ac49958f119aa919c94676","orderid":"45c01549ca9a4341bdc1b19f09502faa","goodid":"9d6310307bbe425991c31ae545c6f529","title":"保迪斯麦饭石不粘汤锅","smallImg":"http://img.jkabe.com/mallimages/e7cc22c919cf455fb038094bf0a830c3cp.jpg","goodNumber":1,"originalPrice":0,"sellPrice":529,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"},{"id":"cf01a1f44b0a400f84c781a55dfe411d","orderid":"45c01549ca9a4341bdc1b19f09502faa","goodid":"2db3a42206384583a3a1894358d22cdf","title":"罗曼妮麦饭石涮烤一体锅","smallImg":"http://img.jkabe.com/mallimages/597d25906d314b31b81dd770a190de7ccp.jpg","goodNumber":1,"originalPrice":0,"sellPrice":798,"integral":0,"descriptionToString":"{\"smallImg\":\"商品图片\",\"originalPrice\":\"原始价格\",\"orderid\":\"订单id\",\"expressorder\":\"快递单号\",\"sellPrice\":\"售卖价格\",\"updateTime\":\"更新时间\",\"goodid\":\"商品id\",\"title\":\"商品标题\",\"expresscompany\":\"快递公司\",\"goodNumber\":\"商品件数\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"id\":\"foreignKey\"}"}]
     * orderinfo : {"id":"45c01549ca9a4341bdc1b19f09502faa","memberid":"c773bf27d09d49999ac0dee5fc1b28b6","orderid":"2010140941214551874","goodNum":2,"goodMoney":1327,"receiveName":"申公豹","receiveAddress":"广东省深圳市南山区众冠时代广场A座1904","receiveMobile":"15919936559","message":"","orderStatus":5,"payStatus":1,"payType":0,"postage":0,"payAmount":0,"ordertime":"2020-10-13T06:38:16.000Z","integral":0,"descriptionToString":"{\"deletetime\":\"会员删除时间\",\"orderid\":\"订单号\",\"receiveAddress\":\"收货地址\",\"orderStatus\":\"订单状态 1=待支付,2=已支付待发货,3=已发货,4=已确认收货,5=订单取消,6=退款申请,7=退款成功,8=订单已完成,9=退货申请,10=退货成功\",\"expressorder\":\"快递单号\",\"paytime\":\"支付时间\",\"updateTime\":\"更新时间\",\"message\":\"买家留言\",\"ordertime\":\"下单时间\",\"expresscompany\":\"快递公司\",\"postage\":\"运费\",\"receiveName\":\"收货人\",\"payType\":\"支付方式 1=微信,2=支付宝,3=银行卡\",\"payAmount\":\"支付金额\",\"createTime\":\"创建时间\",\"integral\":\"积分\",\"goodMoney\":\"商品金额\",\"confirmtime\":\"确认收货时间\",\"id\":\"商品金额\",\"sendtime\":\"发货时间\",\"payStatus\":\"支付状态 1=待支付,2=已支付\",\"memberid\":\"会员\",\"goodNum\":\"商品件数\",\"receiveMobile\":\"联系手机\"}"}
     */

    private OrderinfoBean orderinfo;
    private List<GooditemsBean> gooditems;

    public OrderinfoBean getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(OrderinfoBean orderinfo) {
        this.orderinfo = orderinfo;
    }

    public List<GooditemsBean> getGooditems() {
        return gooditems;
    }

    public void setGooditems(List<GooditemsBean> gooditems) {
        this.gooditems = gooditems;
    }

    public static class OrderinfoBean implements Serializable{
        public String getExpressorder() {
            return expressorder;
        }

        public void setExpressorder(String expressorder) {
            this.expressorder = expressorder;
        }

        /**
         * id : 45c01549ca9a4341bdc1b19f09502faa
         * memberid : c773bf27d09d49999ac0dee5fc1b28b6
         * orderid : 2010140941214551874
         * goodNum : 2
         * goodMoney : 1327.0
         * receiveName : 申公豹
         * receiveAddress : 广东省深圳市南山区众冠时代广场A座1904
         * receiveMobile : 15919936559
         * message :
         * orderStatus : 5
         * payStatus : 1
         * payType : 0
         * postage : 0.0
         * payAmount : 0.0
         * ordertime : 2020-10-13T06:38:16.000Z
         * integral : 0
         * descriptionToString : {"deletetime":"会员删除时间","orderid":"订单号","receiveAddress":"收货地址","orderStatus":"订单状态 1=待支付,2=已支付待发货,3=已发货,4=已确认收货,5=订单取消,6=退款申请,7=退款成功,8=订单已完成,9=退货申请,10=退货成功","expressorder":"快递单号","paytime":"支付时间","updateTime":"更新时间","message":"买家留言","ordertime":"下单时间","expresscompany":"快递公司","postage":"运费","receiveName":"收货人","payType":"支付方式 1=微信,2=支付宝,3=银行卡","payAmount":"支付金额","createTime":"创建时间","integral":"积分","goodMoney":"商品金额","confirmtime":"确认收货时间","id":"商品金额","sendtime":"发货时间","payStatus":"支付状态 1=待支付,2=已支付","memberid":"会员","goodNum":"商品件数","receiveMobile":"联系手机"}
         */
        private String expressorder;
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
        private String expresscompany;


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
    }

    public static class GooditemsBean implements Serializable{
        /**
         * id : ba03c192b9ac49958f119aa919c94676
         * orderid : 45c01549ca9a4341bdc1b19f09502faa
         * goodid : 9d6310307bbe425991c31ae545c6f529
         * title : 保迪斯麦饭石不粘汤锅
         * smallImg : http://img.jkabe.com/mallimages/e7cc22c919cf455fb038094bf0a830c3cp.jpg
         * goodNumber : 1
         * originalPrice : 0.0
         * sellPrice : 529.0
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
