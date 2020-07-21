package com.jkabe.app.android.util;

/**
 * App 基本信息
 */
public class AppInfo {
	 static String userId = "default"; //用户Id
	 static String companyName; //公司名
	 static String folderName; //文件夹的名
	 static String packAgeName;//应用包名
	 static String meterRule;//应用公尺
	 
	 
	public static String getMeterRule() {
		return meterRule;
	}
	public static void setMeterRule(String meterRule) {
		AppInfo.meterRule = meterRule;
	}
	public static String getPackAgeName() {
		return "com.kabei.jkb.android";
	}
	public static void setPackAgeName(String packAgeName) {
		AppInfo.packAgeName = packAgeName;
	}
	public static String getUserId() {
		return userId;
	}
	public static void setUserId(String userId) {
		AppInfo.userId = userId;
	}
	public static String getCompanyName() {
		return "jkb";
	}
	public static void setCompanyName(String companyName) {
		AppInfo.companyName = companyName;
	}
	public static String getFolderName() {
		return "kb";
	}
	public static void setFolderName(String folderName) {
		AppInfo.folderName = folderName;
	}
	
	public static void setAppAttribute(String userId, String floderName, String companyName, String packAgeName) {
		setUserId(userId);
		setFolderName(floderName);
		setCompanyName(companyName);
		setPackAgeName(packAgeName);
	}
	public static void setAppAttribute(String floderName, String companyName, String packAgeName) {
		setFolderName(floderName);
		setCompanyName(companyName);
		setPackAgeName(packAgeName);
	}
}
