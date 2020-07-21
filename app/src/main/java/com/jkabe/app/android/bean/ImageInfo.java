package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/8
 * @name:ImageInfo
 */
public class ImageInfo implements Serializable {

    /**
     * id : 884bfa09704e4e82a41ae2dbe927a188
     * storeId : c8593d12166d4960b56d7f5d80ccd9c7
     * storeName : 深圳南山科技有限公司[西丽]
     * photoFile : http://img.jkabe.com/storelogo/b3500b4045da4134924d9521f132283a.jpg
     * title : 大堂
     * sort : 2
     * status : 2
     * createTime : 2020-06-06T02:42:46.000Z
     * updateTime : null
     * stringCreateTime : 2020-06-06 10:42:46
     * stringUpdateTime :
     */

    private String id;
    private String storeId;
    private String storeName;
    private String photoFile;
    private String title;
    private int sort;
    private int status;
    private String createTime;
    private Object updateTime;
    private String stringCreateTime;
    private String stringUpdateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getStringCreateTime() {
        return stringCreateTime;
    }

    public void setStringCreateTime(String stringCreateTime) {
        this.stringCreateTime = stringCreateTime;
    }

    public String getStringUpdateTime() {
        return stringUpdateTime;
    }

    public void setStringUpdateTime(String stringUpdateTime) {
        this.stringUpdateTime = stringUpdateTime;
    }
}
