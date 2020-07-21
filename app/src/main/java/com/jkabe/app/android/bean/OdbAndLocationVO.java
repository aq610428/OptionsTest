package com.jkabe.app.android.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/7/17
 * @name:OdbAndLocationVO
 */
public class OdbAndLocationVO implements Serializable {
    private String carcard;

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public LocationVO getLocationdata() {
        return locationdata;
    }

    public void setLocationdata(LocationVO locationdata) {
        this.locationdata = locationdata;
    }

    public OdbVO getObddata() {
        return obddata;
    }

    public void setObddata(OdbVO obddata) {
        this.obddata = obddata;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    private LocationVO locationdata;
    private OdbVO obddata;
    private String cartype;

}
