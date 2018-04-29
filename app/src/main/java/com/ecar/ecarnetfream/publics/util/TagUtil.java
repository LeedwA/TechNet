package com.ecar.ecarnetfream.publics.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class TagUtil {
	private final static String TAG = "tag";

	/**
	 * 弹出提示框
	 * 
	 * @param context
	 * @param str
	 */
//	public static void showToast(Context context, String str) {
//		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
//	}

//	/**
//	 * 弹出提示框
//	 * @param str
//	 */
//	public static void showToast(String str) {
//		Toast.makeText(ParkApplication.getInstance(), str, Toast.LENGTH_SHORT)
//				.show();
////		ToastCustom.getInstance().showToast(str);
//	}
	/**
	 * 弹出提示框
	 * @param str
	 */
	public static void showToast(Context context,String str) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT)
				.show();
//		ToastCustom.getInstance().showToast(str);
	}

//	public static void showToast(int resid) {
//		Toast.makeText(ParkApplication.getInstance(), resid, Toast.LENGTH_SHORT)
//				.show();
//	}

	/**
	 * 显示debug 数据
	 * 
	 * @param str
	 */
	public static void showLogDebug(String str) {
		if (Constants.DEBUG)
			Log.d(TAG, str);
	}

	/**
	 * 显示debug 数据
	 * 
	 * @param str
	 */
	public static void showLogDebug(Class context, String str) {
		if (Constants.DEBUG)
			Log.d(TAG, "<" + context.getName().toString() + ">--" + str);
	}

	public static void showLogError(String str) {
		if (Constants.DEBUG)
			Log.e(TAG, str);
	}

	/**
	 * 显示debug 数据
	 * 
	 * @param str
	 */
	public static void showLogError(Class context, String str) {
		if (Constants.DEBUG)
			Log.e(TAG, "<" + context.getName().toString() + ">--" + str);
	}

	public static void showLogDebug(String tag, String content) {
		if (Constants.DEBUG)
			Log.d(tag, content);
	}


}
