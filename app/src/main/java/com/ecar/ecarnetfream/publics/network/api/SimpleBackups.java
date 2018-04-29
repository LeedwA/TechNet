package com.ecar.ecarnetfream.publics.network.api;



import com.ecar.ecarnetfream.login.entity.ResLogin;
import com.ecar.ecarnetwork.bean.ResBase;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * ===============================================
 * <p>
 * 类描述: retrofit 网络请求示例类
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/6/22 0022 上午 10:29
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/6/22 0022 上午 10:29
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public interface SimpleBackups {

    /**
     * 1.Http 请求方法:GET、 POST、PUT、DELETE、PATCH 、HEAD、OPTIONS---都可以用HTTP方法替代
     */

    /**
     * method 表示请的方法，不区分大小写
     * path表示路径
     * hasBody表示是否有请求体
     */
    @HTTP(method = "get", path = "blog/{id}", hasBody = false)
    Observable<ResBase> getFirstBlog(@Path("id") int id);


    @POST("这里连同host会被参数url替换") //url 直接替换 主机+地址
    Observable<ResBase> getFirstBlog(@Url String url, @Path("id") int id);

    /**
     * 获取登录的信息
     *
     * @param map 集合
     * @return Observable
     */
    @GET("data") //data--地址追加到主机后面
    Observable<ResLogin> login(@QueryMap TreeMap<String, String> map);

    /**
     * 获取附近停车场的信息
     *
     * @param map 集合
     * @return Observable
     */
    @GET("/data")  //这里将替换原地址
    Observable<ResLogin> getParkList(@QueryMap TreeMap<String, String> map);


    /**
     * 2.标记类，2.1 表单请求 FormUrlEncoded、Multipart 2.2标记：Streaming 文件比较大时使用
     */

    /**
     * 公司目前
     * @param map
     * @param multiPart
     * @return
     */
    @Multipart
    @POST("/fs/upClient")//地址替换原地址 = host+/fs/upClient
    Observable<ResBase> uploadPic(@QueryMap TreeMap<String, String> map, @PartMap Map<String, RequestBody> multiPart);

    @Multipart
    @POST("/fs/upClient")
    Observable<ResBase> uploadPic(@QueryMap TreeMap<String, String> map, @Part MultipartBody.Part file);

    /**
     * 3.参数类：
     * 作用于请求头:Headers
     * 作用于请求体：Header 、Body、Feild 、FeildMap、Part、PartMap、Path、Query、 QueryMap、Url
     */
    @GET()
    Observable<ResponseBody> testBody(@Body ResBase res);


    @GET("/headers?showAll=true")
    @Headers({"CustomHeader1: customHeaderValue1", "CustomHeader2: customHeaderValue2"})
    Observable<ResponseBody> testHeader(@Header("CustomHeader3") String customHeaderValue3);

    Observable<ResponseBody> foo(@Query("ids[]") List<Integer> ids);
    //结果：ids[]=0&ids[]=1&ids[]=2
}



