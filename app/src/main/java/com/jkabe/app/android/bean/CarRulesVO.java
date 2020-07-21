package com.jkabe.app.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/7/16
 * @name:CarRulesVO
 */
public class CarRulesVO implements Serializable {
    private String  vio_total;
    private String score_total;
    private String code;
    private String find_total;

    private List<CarRulesItemVO> lists;
    public String getVio_total() {
        return vio_total;
    }

    public void setVio_total(String vio_total) {
        this.vio_total = vio_total;
    }

    public String getScore_total() {
        return score_total;
    }

    public void setScore_total(String score_total) {
        this.score_total = score_total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFind_total() {
        return find_total;
    }

    public void setFind_total(String find_total) {
        this.find_total = find_total;
    }

    public List<CarRulesItemVO> getLists() {
        return lists;
    }

    public void setLists(List<CarRulesItemVO> lists) {
        this.lists = lists;
    }


}
