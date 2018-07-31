package com.ecar.ecarnetfream.login;


import com.ecar.ecarnetfream.login.entity.DeviceLoginInfo;
import com.ecar.ecarnetfream.login.entity.SnNumEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * +----------------------------------------------------------------------
 * |  说明     ： 序列号账号请求
 * +----------------------------------------------------------------------
 * | 创建者   :  kim_tony
 * +----------------------------------------------------------------------
 * | 时　　间 ：2017/9/8 10:12
 * +----------------------------------------------------------------------
 * | 版权所有: 北京市车位管家科技有限公司
 * +----------------------------------------------------------------------
 **/

public interface PubApiService {
    @POST("park/api/v1/park-xpark-configuration/initialize")
    Call<SnNumEntity.response> getLoginInfoCall(@Body String requestBody);
    @POST("park/api/v1/park-xpark-configuration/initialize")
    Observable<DeviceLoginInfo> getLoginInfo(@Body RequestBody requestBody);
}
