package com.ecar.ecarnetwork.http.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class TagLibUtil {
    private final static String TAG = "TagLibUtil";

    public static void showToast(Context context, String str) {
        if (ConstantsLib.DEBUG)
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示debug 数据
     *
     * @param str
     */
    public static void showLogDebug(String str) {
        if (ConstantsLib.DEBUG)
            Log.d(TAG, str);
    }

    /**
     * 显示debug 数据
     *
     * @param str
     */
    public static void showLogDebug(Class context, String str) {
        if (ConstantsLib.DEBUG)
            Log.d(TAG, "<" + context.getName().toString() + ">--" + str);
    }

    public static void showLogError(String str) {
        if (ConstantsLib.DEBUG)
            Log.e(TAG, str);
    }

    /**
     * 显示debug 数据
     *
     * @param str
     */
    public static void showLogError(Class context, String str) {
        if (ConstantsLib.DEBUG)
            Log.e(TAG, "<" + context.getName().toString() + ">--" + str);
    }

    public static void showLogDebug(String tag, String content) {
        if (ConstantsLib.DEBUG)
            Log.d(tag, content);
    }

}
