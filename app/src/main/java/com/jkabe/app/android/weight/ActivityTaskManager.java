package com.jkabe.app.android.weight;

import android.app.Activity;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by zt on 2019/1/8.
 */
public class ActivityTaskManager {
    private static ActivityTaskManager activityTaskManager = null;
    private static HashMap<String, Activity> activityMap = null;
    private ActivityTaskManager() {
        activityMap = new HashMap<>();
    }
    /**
     * 返回activity管理器的唯一实例对象。
     * @return ActivityTaskManager
     */
    public static synchronized ActivityTaskManager getInstance() {
        if (activityTaskManager == null) {
            activityTaskManager = new ActivityTaskManager();
        }
        return activityTaskManager;
    }
    /**
     * 将一个activity添加到管理器。
     * @param activity
     */
    public static Activity putActivity(String name, Activity activity) {
        return activityMap.put(name, activity);
    }
    /**
     * 得到保存在管理器中的Activity对象。
     * @param name
     * @return Activity
     */
    public Activity getActivity(String name) {
        return activityMap.get(name);
    }
    /**
     * 返回管理器的Activity是否为空。
     * @return 当且当管理器中的Activity对象为空时返回true，否则返回false。
     */
    public boolean isEmpty() {
        return activityMap.isEmpty();
    }
    /**
     * 返回管理器中Activity对象的个数。
     * @return 管理器中Activity对象的个数。
     */
    public int size() {
        return activityMap.size();
    }
    /**
     * 返回管理器中是否包含指定的名字。
     *
     * @param name
     *            要查找的名字。
     * @return 当且仅当包含指定的名字时返回true, 否则返回false。
     */
    public boolean containsName(String name) {
        return activityMap.containsKey(name);
    }
    /**
     * 返回管理器中是否包含指定的Activity。
     * @param activity
     *            要查找的Activity。
     * @return 当且仅当包含指定的Activity对象时返回true, 否则返回false。
     */
    public boolean containsActivity(Activity activity) {
        return activityMap.containsValue(activity);
    }
    /**
     * 关闭所有活动的Activity。
     */
    public static void closeAllActivity() {
        Set<String> activityNames = activityMap.keySet();
        for (String string : activityNames) {
            finisActivity(activityMap.get(string));
        }
        activityMap.clear();
    }
    /**
     * 关闭所有活动的Activity除了指定的一个之外。
     * @param nameSpecified
     *            指定的不关闭的Activity对象的名字。
     */
    public static void closeAllActivityExceptOne(String nameSpecified) {
        Set<String> activityNames = activityMap.keySet();
        Activity activitySpecified = activityMap.get(nameSpecified);
        for (String name : activityNames) {
            if (!name.equals(nameSpecified)) {
                finisActivity(activityMap.get(name));
            }
        }
        activityMap.clear();
        activityMap.put(nameSpecified, activitySpecified);
    }
    /**
     * 移除Activity对象,如果它未结束则结束它。
     * @param name
     *            Activity对象的名字。
     */
    public static void removeActivity(String name) {
        Activity activity = activityMap.remove(name);
        finisActivity(activity);
    }
    /**
     * 结束指定的Activity
     * @param activity
     *            指定的Activity。
     */
    public static void finisActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

}