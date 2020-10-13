package com.jkabe.app.box.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/10/12
 * @name:GoodBean
 */
public class GoodBean implements Serializable {

    /**
     * id : 27e09d163a4c4a51988feb0e328a0c3a
     * serialNumber : 20062009233
     * title : 居安保车载灭火器
     * details : <p><img alt="" src="http://admin.51obd.com/attached/image/20200603/20200603173254_138.jpg"/><span style="font-family: &quot;sans serif&quot;, tahoma, verdana, helvetica; font-size: 12px;"></span></p>
     * originalPrice : 198.0
     * sellPrice : 1.0
     * originalCost : 47.0
     * integral : 20800
     * brandName : 滇化
     * categoryAname : 车主生活
     * categoryBname : 安全自驾
     * categoryCname : 灭火器
     * sort : 4
     * recommend : 1
     * isFlag : 1
     * goodsImageList : [{"id":"be76fbf2a7be47308898020e17eebfb2","goodid":"27e09d163a4c4a51988feb0e328a0c3a","imgTitle":"","goodImg":"http://img.yuanjiawise.com/mallimages/7abe667867a34a45bc142c24aaccb24ecp.jpg","sort":1},{"id":"b94ec91f2b50466aa2d512dba2e6bbf0","goodid":"27e09d163a4c4a51988feb0e328a0c3a","imgTitle":"","goodImg":"http://img.yuanjiawise.com/mallimages/de4006b382a644d6ad5c01bcaf07f0dfcp.jpg","sort":2},{"id":"1e604e9c311844f59959d4431df9adeb","goodid":"27e09d163a4c4a51988feb0e328a0c3a","imgTitle":"","goodImg":"http://img.yuanjiawise.com/mallimages/d8fb92e2e078492095a67985125b96f1cp.jpg","sort":3}]
     * goodsSpecList : [{"id":"23fc06e8d08e40398ad9e9973730dbe5","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"商品毛重","value":"0.78kg","sort":1,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"e4cbb4cf4d094245a0365cd23362bb2a","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"商品产地","value":"中国大陆","sort":2,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"c457091f4c3e4c14b4b0a95e4c44ccb9","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"充装","value":"干粉","sort":3,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"75ed2530799a46f585f7e372a4c10de8","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"灭火级别","value":"0.5A，8B","sort":4,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"a5e7cd209d56471982aa706785d6c4d2","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"点射功能","value":"支持","sort":5,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"8065fb5dc72e44d7a56666c1ee307016","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"类型","value":"手提式","sort":6,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"7f31c042d72e48648a00615ade8475d4","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"使用环境","value":"家用，私家车用，商用车用，其它","sort":7,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"17593e9556f9443f92d6cd3e97a90839","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"容量","value":"1公斤以下","sort":8,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"},{"id":"f3a9446505bb4bc9b4f827b9c6ae6e50","goodid":"27e09d163a4c4a51988feb0e328a0c3a","key":"压力表","value":"没有","sort":9,"descriptionToString":"{\"goodsId\":\"商品\",\"id\":\"foreignKey\",\"sort\":\"排序\",\"goodsName\":\"商品名称\",\"value\":\"规格值\",\"key\":\"规格键\"}"}]
     * descriptionToString : {"brandName":"品牌名称","serialNumber":"商品编号","smallImg":"商品图片","originalPrice":"原始价格","sellPrice":"售卖价格","recommend":"是否推荐 1=置为推荐,2=不推荐","sort":"排序","title":"商品标题","categoryB":"二级品类名称","categoryC":"三级品类名称","isFlag":"库存状态 1=有货,2=无货","createTime":"创建时间","integral":"积分","brandId":"品牌","categoryA":"一级品类名称","originalCost":"成本价","details":"商品详情","id":"foreignKey","keyword":"搜索关键字"}
     */

    private String id;
    private String serialNumber;
    private String title;
    private String details;
    private double originalPrice;
    private double sellPrice;
    private double originalCost;
    private int integral;
    private String brandName;

    public String getFollowid() {
        return followid;
    }

    public void setFollowid(String followid) {
        this.followid = followid;
    }

    private String followid;

    private int goodNumber;
    private String categoryAname;
    private String categoryBname;
    private String categoryCname;
    private int sort;
    private int recommend;
    private int isFlag;
    private String descriptionToString;
    private List<GoodsImageListBean> goodsImageList;
    private List<GoodsSpecListBean> goodsSpecList;

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(double originalCost) {
        this.originalCost = originalCost;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryAname() {
        return categoryAname;
    }

    public void setCategoryAname(String categoryAname) {
        this.categoryAname = categoryAname;
    }

    public String getCategoryBname() {
        return categoryBname;
    }

    public void setCategoryBname(String categoryBname) {
        this.categoryBname = categoryBname;
    }

    public String getCategoryCname() {
        return categoryCname;
    }

    public void setCategoryCname(String categoryCname) {
        this.categoryCname = categoryCname;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(int isFlag) {
        this.isFlag = isFlag;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }

    public List<GoodsImageListBean> getGoodsImageList() {
        return goodsImageList;
    }

    public void setGoodsImageList(List<GoodsImageListBean> goodsImageList) {
        this.goodsImageList = goodsImageList;
    }

    public List<GoodsSpecListBean> getGoodsSpecList() {
        return goodsSpecList;
    }

    public void setGoodsSpecList(List<GoodsSpecListBean> goodsSpecList) {
        this.goodsSpecList = goodsSpecList;
    }

    public static class GoodsImageListBean implements Serializable{
        /**
         * id : be76fbf2a7be47308898020e17eebfb2
         * goodid : 27e09d163a4c4a51988feb0e328a0c3a
         * imgTitle :
         * goodImg : http://img.yuanjiawise.com/mallimages/7abe667867a34a45bc142c24aaccb24ecp.jpg
         * sort : 1
         */

        private String id;
        private String goodid;
        private String imgTitle;
        private String goodImg;
        private int sort;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodid() {
            return goodid;
        }

        public void setGoodid(String goodid) {
            this.goodid = goodid;
        }

        public String getImgTitle() {
            return imgTitle;
        }

        public void setImgTitle(String imgTitle) {
            this.imgTitle = imgTitle;
        }

        public String getGoodImg() {
            return goodImg;
        }

        public void setGoodImg(String goodImg) {
            this.goodImg = goodImg;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }

    public static class GoodsSpecListBean implements Serializable{
        /**
         * id : 23fc06e8d08e40398ad9e9973730dbe5
         * goodid : 27e09d163a4c4a51988feb0e328a0c3a
         * key : 商品毛重
         * value : 0.78kg
         * sort : 1
         * descriptionToString : {"goodsId":"商品","id":"foreignKey","sort":"排序","goodsName":"商品名称","value":"规格值","key":"规格键"}
         */

        private String id;
        private String goodid;
        private String key;
        private String value;
        private int sort;
        private String descriptionToString;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodid() {
            return goodid;
        }

        public void setGoodid(String goodid) {
            this.goodid = goodid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getDescriptionToString() {
            return descriptionToString;
        }

        public void setDescriptionToString(String descriptionToString) {
            this.descriptionToString = descriptionToString;
        }
    }
}
