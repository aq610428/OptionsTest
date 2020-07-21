package com.jkabe.app.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/6
 * @name:LeftVo
 */
public class LeftVo implements Serializable {


    /**
     * tag : 热门推荐
     * items : [{"color":"1890EF","name":"惠保养","sort":2,"tagLogo":"http://img.jkabe.com/advertfile/0e1ae9d0eee74f7f9ef20fa08277c624.png","title":"惠保养","url":""},{"color":"1890EF","name":"惠购车","sort":3,"tagLogo":"http://img.jkabe.com/advertfile/561d4f3eb0bc478dbe3c209eeeb33ac0.png","title":"惠购车","url":"http://m.51hgche.cn/"},{"color":"1890EF","name":"惠保险","sort":3,"tagLogo":"http://img.jkabe.com/advertfile/0b64546ff0af4fa3b19199fc7ced46bf.png","title":"惠保险","url":""},{"color":"1890EF","name":"车油惠","sort":4,"tagLogo":"http://img.jkabe.com/advertfile/fe35d244d9b2428a8f2829fedee17592.png","title":"车油惠","url":"http://oil.card.jkabe.com"}]
     */

    private String tag;
    private List<ItemsBean> items;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Serializable{
        /**
         * color : 1890EF
         * name : 惠保养
         * sort : 2
         * tagLogo : http://img.jkabe.com/advertfile/0e1ae9d0eee74f7f9ef20fa08277c624.png
         * title : 惠保养
         * url :
         */

        private String color;
        private String name;
        private int sort;
        private String tagLogo;
        private String title;
        private String url;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTagLogo() {
            return tagLogo;
        }

        public void setTagLogo(String tagLogo) {
            this.tagLogo = tagLogo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
