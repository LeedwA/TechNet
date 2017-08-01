package com.ecar.ecarnetwork.http.exception;

import android.text.TextUtils;

import com.ecar.ecarnetwork.bean.ResBase;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常(针对所有的Respone校验)：
 * 1.强制重新登录
 * 2.校验码错误
 */
public class InvalidException extends BaseException {

    public static final String FLAG_ERROR_RELOGIN = "2000";
    public static final String FLAG_ERROR_RESPONCE_CHECK = "2001";

    static{
    }

    public InvalidException(String code, String msg, ResBase resObj) {
        super(code, msg, resObj);
    }

    @Override
    public String getMsg() {
        return "InvalidException";
    }
}
