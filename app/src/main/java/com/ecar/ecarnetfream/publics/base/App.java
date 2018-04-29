package com.ecar.ecarnetfream.publics.base;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;


import com.ecar.ecarnetfream.publics.util.Constants;
import com.ecar.ecarnetwork.http.api.ApiBox;

import java.util.LinkedList;
import java.util.List;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/6/1 0001 上午 9:35
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/6/1 0001 上午 9:35
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class App extends Application{

    private static App mApp;
    private List<Activity> activityList = new LinkedList<Activity>();
    private List<Activity> payActivityList = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        ApiBox.Builder builder = new ApiBox.Builder();
        builder.application(this).debug(Constants.DEBUG).reqKey(Constants.REQUEST_KEY).appId(
//                "110100 110011 1100110 110110 1100100 110000 110100 1100110 110010 110000 110111 110000 1100100 1100010 110110 111000 1100100 110001 110010 110101 110100 111000 110110 110011 1100110 110011 111001 110001 111000 110110 110110 111001"
                 ""
        ).build();

    }

    public static App getInstance() {
        return mApp;
    }
    public static Resources getAppResources() {
        return mApp.getResources();
    }


    /**
     * 添加Activity 到容器中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if(activity!=null){
            activityList.add(activity);
        }
    }

    /**
     * 移除
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            if(activityList.contains(activity)) {
                activityList.remove(activity);
            }
        }
    }

    /**
     * 添加支付activity到容器
     */
    public void addPayActivity(Activity activity) {
        if (activity != null) {
            payActivityList.add(activity);
        }
    }

    /**
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if(activityList.contains(activity)) {
                activityList.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * 遍历所有Activity 并finish
     */
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     * 遍历所有Activity 并quit
     */
    public void quit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 遍历所有Activity 并quit
     */
    public void quitPayActivity() {
        for (Activity activity : payActivityList) {
            activity.finish();
        }
    }


    /**
     * 获取Activity 数量
     *
     * @param
     */
    public int getActivityCount() {
        if(activityList !=null){
            return activityList.size();
        }
        return 0;
    }

    /*
     * 功能:结束指定类名的Activity。 其位置栈上面的其他activity
     */
    public void finishBeforeActivity(Class<?> cls) {
        int index = -1;
        int size = activityList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (activityList.get(i).getClass().equals(cls)) {
//				index = activityList.indexOf(activity);
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = size - 1; i > index; i--) {
                finishActivity(activityList.get(i));
            }
        }

    }

    /*
     * 功能:找到最靠近栈顶的Activity
     */
    public Activity getTopActivity(Class<?> cls) {
        int index = -1;
        int size = activityList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (activityList.get(i).getClass().equals(cls)) {
//				index = .indexOf(activity);
                index = i;
                return activityList.get(i);
            }
        }
        return null;
    }


    /**
     * 功能:设置当前Activity（堆栈中最后一个压入的）
     */
    public Activity setCurrentActivity(Activity activity) {
        boolean isContains = activityList.contains(activity);
        if (isContains) {
            activityList.remove(activity);
        }
        activityList.add(activity);
        return activity;
    }



}
