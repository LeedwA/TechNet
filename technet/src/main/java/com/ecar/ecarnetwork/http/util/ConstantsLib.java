package com.ecar.ecarnetwork.http.util;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 */
public class ConstantsLib {

    public static String RESPONES_HEADERNAME = "cwgj_type";//返回的header
    public static String RESPONES_HEADERVALUE = "node-service";//返回的header 值


    /**
     * REQUEST_KEY
     */
    public static String REQUEST_KEY;

    /**
     * APP_ID
     */
    public static String APP_ID = "";

    /**
     * Log 日志开关 发布版本设为false
     */
    public static boolean DEBUG = false;
    public static boolean VeriNgis = true;
    public static boolean IS_UNCHECK = false;


    public static String HEADER_TOKEN = "";//头部token

    //code集合
    public static Map<String, String> serverCodeMap=new HashMap<>();

}
