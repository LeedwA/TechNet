package com.ecar.ecarnetfream.publics.base;


import com.ecar.ecarnetfream.publics.network.HttpUrl;
import com.ecar.ecarnetfream.publics.network.api.ApiService;
import com.ecar.ecarnetwork.http.api.ApiBox;

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
        if(apiService == null){
            this.apiService = ApiBox.getInstance().createService(ApiService.class, HttpUrl.Base_Url);
        }
    }
}
