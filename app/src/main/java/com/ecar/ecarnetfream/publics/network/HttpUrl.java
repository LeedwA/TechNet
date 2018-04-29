package com.ecar.ecarnetfream.publics.network;


import com.ecar.ecarnetfream.publics.util.DataFormatUtil;

public class HttpUrl {
    public static final int REQUEST_TYPE_GET = 0;// get请求
    public static final int REQUEST_TYPE_POST = 1;// post请求

    public static final int GET_DEVELOP = 0;// 开发环境
    public static final int GET_TEST = 1;// 测试环境
    public static final int GET_OFFICIAL = 2;// 正式环境

    public static final int URL_TYPE = GET_OFFICIAL;// 选择环境

    public static String Url_Common;//一般url
    public static String Url_DwonUp; //上传下载url
    public static String Base_Url;
    public static String Base_Url_upClient;
    public static String Base_Url_downClient;

    static {
        getUrl(URL_TYPE);
    }

    /**
     * 配置服务器请求地址
     */

    private static void getUrl(int index) {
        StringBuilder sb = new StringBuilder();
        switch (index) {
            case GET_DEVELOP:// 开发环境

                Url_Common = "http://szchmtech.3322.org:9009";
                Url_DwonUp = "http://szchmtech.3322.org:9009";


                Base_Url = DataFormatUtil.addText(sb, Url_Common, "/parkCloud/system/data?");
                Base_Url_upClient = DataFormatUtil.addText(sb, Url_DwonUp, "/parkCloud/system/upClient?");
                Base_Url_downClient = DataFormatUtil.addText(sb, Url_DwonUp, "/parkCloud/system/image?");


                break;

            case GET_TEST: // 测试环境
                Url_Common = "http://szchmtech.3322.org:6081";
                Url_DwonUp = "http://szchmtech.3322.org:6081";

                Base_Url = DataFormatUtil.addText(sb, Url_Common, "/std/data?");
                Base_Url_upClient = DataFormatUtil.addText(sb, Url_DwonUp, "/std/upClient?");
                Base_Url_downClient = DataFormatUtil.addText(sb, Url_DwonUp, "/std/image?");

                break;

            case GET_OFFICIAL:// 正式环境
                Url_Common = "http://sjspark.com:8080";
                Url_DwonUp = "http://sjspark.com:8080";

                Base_Url = DataFormatUtil.addText(sb, Url_Common, "/system/data/");//  /system/data?

                Base_Url_upClient = DataFormatUtil.addText(sb, Url_DwonUp, "/system/upClient/");//  /system/upClient?
                Base_Url_downClient = DataFormatUtil.addText(sb, Url_DwonUp, "/system/image");//  /system/image?


                break;
        }
    }

}
