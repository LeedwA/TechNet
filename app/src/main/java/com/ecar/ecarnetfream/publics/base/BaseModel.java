package com.ecar.ecarnetfream.publics.base;


import com.ecar.ecarnetfream.login.AES;
import com.ecar.ecarnetfream.login.PubApiService;
import com.ecar.ecarnetfream.login.entity.DeviceLoginInfo;
import com.ecar.ecarnetfream.login.entity.SnNumEntity;
import com.ecar.ecarnetfream.publics.network.HttpUrl;
import com.ecar.ecarnetfream.publics.network.api.ApiService;
import com.ecar.ecarnetwork.http.api.ApiBox;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/6/23 0023 下午 15:00
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/6/23 0023 下午 15:00
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public abstract class BaseModel {

    protected static ApiService apiService;

    public BaseModel() {
        if (apiService == null) {
            this.apiService = ApiBox.getInstance().createService(ApiService.class, HttpUrl.Base_Url);
        }
    }


    public Observable<DeviceLoginInfo> getLoginInfoBySn(String sn) {
        String enStr =
//                Aes.encrypt("cwgj123456",str);
                AES.encode("cwgj123456789022", new Gson().toJson(
                        new SnNumEntity(sn)));
        SnNumEntity.request outPark = new SnNumEntity.request(enStr);

        Observable<DeviceLoginInfo> observable = ApiBox.getInstance().createService(PubApiService.class, "http://47.98.202.249:8000/").
                getLoginInfo(getRequestBodyNonBase(outPark));//ApiBox.getInstance().createService(ApiService.class, HttpUrl.Base_Url).login(reMap);
        return getNewObservableNoneBase(observable);

    }

    //获取网络请求ob   获取BaseResponsEntity
    protected <T> Observable<T> getNewObservableNoneBase(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    //获取requestbody
    protected <T> RequestBody getRequestBodyNonBase(T t) {
        return RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(t));
    }
}

