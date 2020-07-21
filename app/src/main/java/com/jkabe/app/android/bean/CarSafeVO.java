package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/15
 * @name:CarSafeVO
 */
public class CarSafeVO implements Serializable {

    /**
     * title : 出围栏报警
     * value : 1
     * key : outarea
     */

    private String title;
    private int value;
    private String key;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
