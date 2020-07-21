package com.jkabe.app.android.bean;

/**
 * @author: zt
 * @date: 2020/7/13
 * @name:Battery
 */
public class Battery {

    public Battery(String descriptionToString) {
        this.descriptionToString=descriptionToString;
    }

    public String getGpstime() {
        return gpstime;
    }

    public void setGpstime(String gpstime) {
        this.gpstime = gpstime;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }

    private String gpstime; //":"2020-07-12 21:53:57",
    private float voltage; //":12.7,
    private String descriptionToString; //":"{"gpstime":"gps时间","voltage":"电压"}"

}
