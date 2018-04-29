package com.ecar.ecarnetwork.interfaces.security;

import android.content.Context;

/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * 创建人:   happy
 * <p/>
 * 创建时间: 2016/7/26 0026 下午 17:49
 * <p/>
 * 修改人:   happy
 * <p/>
 * 修改时间: 2016/7/26 0026 下午 17:49
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public interface IInvalid {
    /**
     * 强制重新登录
     * @param msg
     */
    void reLogin(Context iContext, String msg);
}
