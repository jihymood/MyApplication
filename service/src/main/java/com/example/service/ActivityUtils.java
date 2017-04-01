package com.example.service;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjh on 2017/2/17 0017 13:41
 * Email：huangjihy@163.com
 */
public class ActivityUtils {
    public static List<Activity> activityList = new ArrayList<>();

    public ActivityUtils() {
    }

    /**
     * 添加到Activity容器中
     */
    public static void addActivity(Activity activity) {
//        if (!activityList.contains(activity)) {
            activityList.add(activity);
//        }
    }

    /**
     * 移除活动
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 遍历所有的Activity并finish
     */
    public static void finishActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishSingleActivity(Activity activity) {
        if (activity != null) {
            if (activityList.contains(activity)) {
                activityList.remove(activity);
            }
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity 在遍历一个列表的时候不能执行删除操作，所有我们先记住要删除的对象，遍历之后才去删除。
     */
    public static void finishSingleActivityByClass(Class<?> cls) {
        Activity tempActivity = null;
        for (Activity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                tempActivity = activity;
            }
        }

        finishSingleActivity(tempActivity);
    }


}
