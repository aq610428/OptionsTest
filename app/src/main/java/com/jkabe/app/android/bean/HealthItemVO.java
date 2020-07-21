package com.jkabe.app.android.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/7/16
 * @name:HealthItemVO
 */
public class HealthItemVO implements Serializable {
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
        this.items = items;
    }

    private Integer score;
    private Map<String,String> items;
}
