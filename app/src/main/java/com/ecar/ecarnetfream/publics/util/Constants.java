package com.ecar.ecarnetfream.publics.util;

public class Constants {
	/**
	 * 当前城市
	 */
	public static final  String DEFULT_CITY ="深圳"; //当前城市

	/**
	 * 是否已获取html
	 */
	public static boolean GET_HTML_URL = false;

	public static final String net_price = "timeprice";
	/**
	 * 3G模式读取文件缓存时间限制
	 */
	public static final int CONFIG_CACHE_MOBILE_TIMEOUT = 60000 * 60 * 10; // 10小时

	/**
	 * 网络请求参数
	 */
	public static final String CHECKSIGN = "\"ngis\":\"[a-z0-9A-Z]{32}\"";
	/**
	 * wifi模式读取文件缓存时间限制
	 */
	public static final int CONFIG_CACHE_WIFI_TIMEOUT = 60000 * 60 * 5; // 5小时
	/**
	 * 服务器时间戳
	 */
	public static long SERVER_TIME_STAMP = 0;
	/**
	 * 登录时间戳
	 */
	public static long LOGIN_TIME_STAMP = 0;
	/**
	 * 
	 */
	public static final String HTTPS_FLAG = "https://";
	/**
	 * 是否申请次日续时标志 true 已需申请 false 未申请
	 */
	/**
	 * 支付宝合作伙伴密钥管理
	 */
	public final static String ALIPAY_KEY = "ae8b8m53qkhqv6dbitievnixhpvxgr3f";
	/**
	 * 校验值
	 */
	public final static String SIGNKEY = "110100 110011 1100110 110110 1100100 110000 110100 1100110 110010 110000 110111 110000 1100100 1100010 110110 111000 1100100 110001 110010 110101 110100 111000 110110 110011 1100110 110011 111001 110001 111000 110110 110110 111001";//
	// 正式环境
	// public final static String SIGNKEY =
	// "110000 1100001 110010 1100100 111001 111000 110000 110010 1100101 1100110 110101 111000 1100010 1100110 110000 110010 1100101 110101 110001 1100011 1100001 1100011 110011 110110 110010 111000 110000 1100011 110110 110000 1100101 110110";//
	// 测试环境

	/**
	 * appKey
	 */
	public final static String APP_KEY = "110001 110000 110001 110010 111000 110000 111001 110001 111000";// 正式环境
	// public final static String APP_KEY =
	// "110001 110000 110001 110011 111000 110100 110001 110010 110100";// 测试环境


	/**
	 * REQUEST_KEY
	 */
	public static final String REQUEST_KEY = "1000100 110011 110000 110010 111001 1000011 110111 110011 110100 110000 110110 110010 110010 110001 1000010 110000 110010 110000 110010 110110 1000010 110110 111000 110100 1000010 1000010 110000 110000 110101 110111 111001 1000011";
	public static final String DATABASE_NAME = "park_db";
	public static final String SP_NAME = "park_sp";  //共享文件名


	public static final String SP_CITY = "city";// 保存第一次登录的城市名
	public static final String SP_PROVINCE = "province";// 保存定位的省份
	public static final String SP_FIRST_LATITUDE = "latitude";// 保存第一次登录的城市纬度
	public static final String SP_FIRST_LONGITUDE = "longitude";// 保存第一次登录的城市经度
	/**
	 * 常用名词
	 */
	public static final int PAGE_INDEX = 1;
	public static final int PAGE_SIZE = 20;


	/**
	 * Log 日志开关 发布版本设为false
	 */
	public static final boolean DEBUG = true;//BuildConfig.LOG_DEBUG;//
	/**
	 * 新版本是否强制进入引导页 true 则强制进入
	 */
	public static final boolean goGuide = false;
}
