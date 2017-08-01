package com.ecar.ecarnetfream.publics.network;/*
 *===============================================
 *
 * 文件名:${type_name}
 *
 * 描述: 
 *
 * 作者:
 *
 * 版权所有:深圳市亿车科技有限公司
 *
 * 创建日期: ${date} ${time}
 *
 * 修改人:   金征
 *
 * 修改时间:  ${date} ${time} 
 *
 * 修改备注: 
 *
 * 版本:      v1.0 
 *
 *===============================================
 */

import android.os.Environment;

import com.ecar.ecarnetfream.login.entity.ResLogin;
import com.ecar.ecarnetfream.publics.network.api.ApiService;
import com.ecar.ecarnetfream.publics.util.TagUtil;
import com.ecar.ecarnetwork.bean.ResBase;
import com.ecar.ecarnetwork.http.api.ApiBox;
import com.ecar.ecarnetwork.http.exception.UserException;
import com.ecar.ecarnetwork.util.major.Major;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Datacenter {

    public static Datacenter datacenter;
    private static ApiService apiService;

    private Datacenter() {
    }

    public static synchronized Datacenter get() {
        if (datacenter == null) {
            datacenter = new Datacenter();
        }
        if (apiService == null) {
            apiService = ApiBox.getInstance().createService(ApiService.class, "http://115.159.123.33:9000");
        }
        return datacenter;
    }

    public Observable<ResLogin> login(String mobileno, String pwd) {

//        HashMap<String, String> tMap = new HashMap<>();
//        tMap.put("account", "18923729010");
//        tMap.put("deviceId", "90DB4A6EF8A019917099234374C0CEA4");
//        tMap.put("password", "123456");
//        tMap.put("sign", "8E8032844B9E66C73F128F95EB3830F2DE61FF29");

        /**
         * 代理类调用方法获取Observable
         */
//        Observable<ResLogin> observable = apiService.login(tMap);//ApiBox.getInstance().createService(ApiService.class, HttpUrl.Base_Url).login(reMap);
//        ApiBox.getInstance().cancleAllRequest();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson((new ResLogin()).build()));
        Observable<ResLogin> observable = apiService.createSubject(body);//ApiBox.getInstance().createService(ApiService.class, HttpUrl.Base_Url).login(reMap);
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResLogin> sign(String name, String pass) {
        return null;
    }

    public Observable<ResBase> uploadPic(String path) {
        // 添加参数到集合
        HashMap<String, String> tMap = Major.getDefaultMap();
        tMap.put("img_type", "png");

        /**
         * 加密treeMap
         */

        Map<String, RequestBody> partMap = new HashMap<>();
        //post 请求体
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/parking/camera1/1467362758050.png");
        if (file.exists()) {
            TagUtil.showLogError("file exists");
        } else {
            throw new UserException("文件不存在");
        }
        /**
         * 单个文件 （已成功）
         */
        RequestBody fileOne = RequestBody.create(MediaType.parse("image/png"), file);//"这里是模拟文件的内容"
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("content", "1467362758050.png", fileOne);
        return apiService.uploadPic(HttpUrl.Base_Url_upClient, tMap, filePart);
    }
}
