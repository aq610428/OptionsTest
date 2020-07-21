package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/16
 * @name:HealthProjectVO
 */
public class HealthProjectVO implements Serializable {
    private String name;
    private String value;
    private int stats;// 1检测中，2检测完成

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStats() {
        return stats;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }

}
