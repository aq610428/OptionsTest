package com.jkabe.app.box.bean;


/**
 * @author: zt
 * @date: 2020/9/9
 * @name:PackageBean
 */
public class PackageBean {

    /**
     * package_period : 30
     * balance :
     * packagesn : 211192
     * package_name : 30M(月叠加包)F
     * package_usage : 30720
     */

    private int package_period;
    private String balance;
    private int packagesn;
    private String package_name;
    private int package_usage;

    public int getPackage_period() {
        return package_period;
    }

    public void setPackage_period(int package_period) {
        this.package_period = package_period;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getPackagesn() {
        return packagesn;
    }

    public void setPackagesn(int packagesn) {
        this.packagesn = packagesn;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getPackage_usage() {
        return package_usage;
    }

    public void setPackage_usage(int package_usage) {
        this.package_usage = package_usage;
    }
}
